package com.onlive.front.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                
        HttpSession session = request.getSession();
        if (session != null) {
            // 로그인 인증을 위해 Spring Security가 요청을 가로챈 경우=> 사용자가 원래 요청했던 uri 정보를 저장한 객체
            RequestCache requestCache = new HttpSessionRequestCache();
            SavedRequest savedRequest = requestCache.getRequest(request, response);
            String url ="/";
            // 유저가 로그인 버튼을 클릭해 이전 경로가 있으면 가져와 사용auth
//            String prevPage= (String) session.getAttribute("prevPage"); //이전 경로 가져오기             
//            session.removeAttribute("prevPage");    //session에 있는 이전경로 삭제=>메모리 누수 방지
//                        
//            
//            if (savedRequest != null) {
//                url = savedRequest.getRedirectUrl();
//                requestCache.removeRequest(request, response);
//            }else if(prevPage!=null) {
//                url = prevPage;
//            }
            
            String link = (String) session.getAttribute("link");   
            if(link!=null) {
                session.removeAttribute("link");
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out=response.getWriter();
                out.println("<script>opener.location.reload();" + 
                            "opener.location.href=\"; self.close();</script>");
                out.flush();
            }else {
                redirectStrategy.sendRedirect(request, response, url);
            }
        } else {
            redirectStrategy.sendRedirect(request, response, "/index");
        }
    }

}
