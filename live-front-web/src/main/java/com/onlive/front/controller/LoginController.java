package com.onlive.front.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onlive.common.config.MessageSourceConfig;
import com.onlive.common.service.CodeMessageService;
import com.onlive.common.service.LoginService;
import com.onlive.common.vo.CommonApiResponseVo;
import com.onlive.common.vo.SellerVo;
import com.onlive.common.vo.SendCodeVo;
import com.onlive.common.vo.UserVo;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequiredArgsConstructor
public class LoginController {
    
    private final LoginService loginService;
    private final CodeMessageService codeMessageService;
    private final MessageSourceConfig messageSource;
    
    //로그인 페이지 접속
    @RequestMapping("/login")
    public ModelAndView getSignIn(HttpServletRequest request,HttpServletResponse response,Authentication authentication) {
        ModelAndView mv = new ModelAndView();
        //Referer로 이전접속경로를 가져와 로그인 후 이전 페이지로 이동시킨다
        //String referrer = request.getHeader("Referer");
        //request.getSession().setAttribute("prevPage", referrer);
        if(authentication!=null) {
            mv.setViewName("redirect:/index");
        }else {
            mv.setViewName("/login/sign_in");
        }        
        return mv;
    }
    
    
    @RequestMapping("/signup")
    public ModelAndView getSignUp(HttpServletRequest request) {
        //회원가입 페이지
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/login/sign_up");
        return mv;
    }
    @RequestMapping("/signup/complete")
    @ResponseBody
    public CommonApiResponseVo<String> proSignUp(UserVo user) {  
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        int result = loginService.insertUser(user);
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.signUp"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }

    @RequestMapping("/signup/sendCode")
    @ResponseBody
    public CommonApiResponseVo<String> sendCode(SendCodeVo sendCodeVo) {
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        //입력한 번호 체크
        int phoneResult=loginService.searchPhone(sendCodeVo.getSendTo());
        if(phoneResult == 0) {
            //인증코드 생성
            String authCode = codeMessageService.getAuthCode();
            sendCodeVo.setAuthCode(authCode);  
            int result =codeMessageService.sendCodePhone(sendCodeVo);  
            if(result != 0) {
                response.setResult(true);
               response.setMessage(messageSource.getMessage("message.send.code"));
            }else {
                response.setResult(false);
                response.setMessage(messageSource.getMessage("message.error"));
            }
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error.phone"));
        }

        return response;
    }
    
   @RequestMapping("/signup/searchAuthCode")
   @ResponseBody
   public CommonApiResponseVo<String> searchAuthCode(SendCodeVo sendCodeVo) {
       //인증코드 비교
       CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
       int result =codeMessageService.searchAuthCode(sendCodeVo); 
       if(result != 0) {
           response.setResult(true);
       }else {
           response.setResult(false);
           response.setMessage(messageSource.getMessage("message.error.check.code"));
       }
       return response;
   }   
    
    @RequestMapping("/signup/searchId")
    @ResponseBody
    public CommonApiResponseVo<String> searchId(String userId) {
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        //아이디 중복검사 및 찾기
        int result = loginService.searchId(userId);
        System.out.println("결과 : "+result);
        if(result == 0) {
            response.setResult(true);
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error.check.id"));
        }
        return response;
    }
    @RequestMapping("/find/id")
    public ModelAndView getFindId(HttpServletRequest request) {
        //아이디 찾기 페이지 접속
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/login/find_id");
        return mv;
    }  
    
    @RequestMapping("/find/id/userCheck")
    @ResponseBody
    public CommonApiResponseVo<Map<String,Object>> userIdCheck(UserVo user) {
        //아이디 찾기 - 인증코드
        CommonApiResponseVo<Map<String,Object>> response = new CommonApiResponseVo<>();
        int result = loginService.findIdInt(user);   
        if(result!=0) {
            response.setResult(true);
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error.find.id"));
        }
        return response;
    }
    @RequestMapping("/find/id/complete")
    public ModelAndView getFindIdResult(UserVo user){
        //아이디 찾기 결과페이지
        ModelAndView mv = new ModelAndView();
        Map<String,Object> result = loginService.findId(user);
        mv.setViewName("/login/find_id_result");
        mv.addObject("userId",result.get("userId"));
        mv.addObject("userEmail",result.get("userEmail"));
        return mv;
    }
    @RequestMapping("/find/pw")
    public ModelAndView getFindPw(HttpServletRequest request) {
        //비밀번호 인증코드 페이지
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/login/find_pw");
        return mv;
    }
    @RequestMapping("/find/pw/sendCode")
    @ResponseBody
    public CommonApiResponseVo<String> sendCodeEmail(SendCodeVo sendCodeVo) throws MessagingException {
        System.out.println(sendCodeVo);
        //코드 이메일 전송
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        //이메일 존재 여부
        int emailResult=loginService.searchId(sendCodeVo.getSendTo());
        if(emailResult != 0){
            //코드생성-넣기
            String authCode = codeMessageService.getAuthCode();
            sendCodeVo.setAuthCode(authCode);
            int result =codeMessageService.sendCodeEmail(sendCodeVo);  
            if(result != 0) {
                response.setResult(true);
               response.setMessage(messageSource.getMessage("message.send.code"));
            }else {
                response.setResult(false);
                response.setMessage(messageSource.getMessage("message.error"));
            }
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error.find.id"));
        }
        return response;
    }
    @RequestMapping("/find/pw/authCodeCheck")
    @ResponseBody
    public CommonApiResponseVo<String> pwAuthCodeCheck(SendCodeVo sendCodeVo){
        //비밀번호 인증코드 비교 확인
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        int result = codeMessageService.searchAuthCode(sendCodeVo);        
        if(result!=0) {
            response.setResult(true);
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error.check.code"));
        }
        return response;
    }
    @RequestMapping("/find/pw/reset")
    public ModelAndView getFindPwResult(UserVo user) {
        //비밀번호 재설정 페이지
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/login/find_pw_reset");
        mv.addObject("userId", user.getUserId());
        return mv;
    }
    @RequestMapping("/find/pw/complete")
    @ResponseBody
    public CommonApiResponseVo<String> resetPw(UserVo user){
        //비밀번호 설정
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        //이전 비밀번호와 비교
        int checkPw = loginService.checkPw(user);
        if(checkPw == 0) {
            //비밀번호 변경
            int result = loginService.resetPw(user);
            if(result!=0) {                
                response.setResult(true);
                response.setMessage(messageSource.getMessage("message.reset.pw"));
            }else {
                response.setResult(false);
                response.setMessage(messageSource.getMessage("message.error"));
            }
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error.reset.pw"));
        }

        return response;
    }
    @RequestMapping("/seller_insert")
    public ModelAndView getSellerInsert(ModelAndView mv) {
        //판매자 등록 페이지
        mv.setViewName("/login/seller_insert");
        return mv;
    }
    /* 판매자 이름 중복 확인 */
    @RequestMapping("/selNameCheck")
    @ResponseBody
    public CommonApiResponseVo<String> proNickCheck(String selName){
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        int result = loginService.countBySelNameCheck(selName);
        //int result = myPageService.nickCheck(userNickname);
        response.setResult(true);
        if(result == 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.seller.name"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error.seller.name"));
        }
        return response;        
    }
    /* 판매자 등록하기 */
    @RequestMapping("/proSellerInsert")
    @ResponseBody
    public CommonApiResponseVo<String> proSellerInsert(@AuthenticationPrincipal UserVo user,SellerVo selVo){
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        selVo.setSelId(user.getUserId());
        int result = loginService.insertSellerInfo(selVo);
        response.setResult(true);
        if(result == 1) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.seller.insert"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error.seller.insert"));
        }
        return response;        
    }
}
