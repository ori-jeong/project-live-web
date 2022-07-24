package com.onlive.front.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.onlive.common.config.MessageSourceConfig;



@Configuration
public class AuthFailureHandler implements AuthenticationFailureHandler{
    @Autowired
    private MessageSourceConfig messageSource;
    
    private final String DEFAULT_FAILURE_URL = "/login?error=true";
    
    //로그인 실패 오류 코드를 받아 화면에 전송해 코드를 받아 재로그인 요청 처리   
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        String loginFailMsg = "";
        System.out.println("실패 : "+exception); 
        
        if(exception instanceof UsernameNotFoundException) {
            loginFailMsg = messageSource.getMessage("message.error.login.fail.notexist");
        }else if(exception instanceof BadCredentialsException) {
            //비밀번호 불일치
            loginFailMsg = messageSource.getMessage("message.error.login.fail.wrong");
        }else if(exception instanceof DisabledException) {
            //계정 비활성화
            loginFailMsg = messageSource.getMessage("message.error.login.fail.disabled");
        }else {
            //에러
            loginFailMsg = messageSource.getMessage("message.error.login.fail.non");
        }

        request.setAttribute("loginFailMsg", loginFailMsg);
        request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
    }
}
