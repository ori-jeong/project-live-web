package com.onlive.front.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onlive.common.config.MessageSourceConfig;
import com.onlive.common.service.MyPageService;
import com.onlive.common.vo.AddressVo;
import com.onlive.common.vo.CommonApiResponseVo;
import com.onlive.common.vo.OrderVo;
import com.onlive.common.vo.SalesVo;
import com.onlive.common.vo.UserVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor  //final 또는 @Notnull이 붙은 필드의 생성자를 자동 생성 (final -> @Autowired)
public class MyPageController {
    private final MyPageService myPageService;
    private final MessageSourceConfig messageSource;
    
    // @AuthenticationPrincipal : 로그인한 사용자의 정보를 파라메터로 받고 싶을 때 Principal 객체로 받아 사용
    // @AuthenticationPrincipal UserVo user,@AuthenticationPrincipal OAuth2User principal
    /* 마이페이지 */ 
    @RequestMapping
    public ModelAndView getMypage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mypage/mypage_nav"); 
        return mv;
    }
     
    /* 상품 구매 내역 페이지 */
    @RequestMapping("/order_list")
    public ModelAndView getOrderList(@AuthenticationPrincipal UserVo user,@RequestParam(required = false) String before
            ,@RequestParam(required = false) String status) {
        // @AuthenticationPrincipal : 로그인한 사용자의 정보를 파라메터로 받고 싶을 때 Principal 객체로 받아 사용
        ModelAndView mv = new ModelAndView();
        OrderVo order = new OrderVo();
        order.setUserId(user.getUserId());
        if(before == null) {
            order.setOrderDate("1");
        }else {
            order.setOrderDate(before);
        }

        if(status != null) {
            if(status == "20" ||status == "21") {
                order.setPaymentStatus(status);
            }else {
                order.setOrderStatus(status);
            }
        }
        order.setUserId(user.getUserId());
        mv.addObject("status", myPageService.getUserOrderStatusCount(order));
        mv.addObject("orders", myPageService.getUserOrderInfoList(order));
        mv.setViewName("/mypage/order_list");        
        return mv;
    }
    /* 주문 상품 취소 */
    @RequestMapping("/order_list/cancel_order_process")
    @ResponseBody
    public  CommonApiResponseVo<String> cancelOrderProcess(SalesVo salesVo){
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        int result = myPageService.cancelOrderProcess(salesVo);
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.mypage.orderList.cancelOrder"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }
    /* 주문 상품 구매 확정 */
    @RequestMapping("/order_list/confirmation_process")
    @ResponseBody
    public  CommonApiResponseVo<String> confirmationProcess(OrderVo order){
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        int result = myPageService.confirmationProcess(order);
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.mypage.orderList.confirmation"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }
    /* 배송지 목록 페이지 */
    @RequestMapping("/address_list")
    public ModelAndView getAddressList(@AuthenticationPrincipal UserVo user) {
        ModelAndView mv = new ModelAndView();
        //String userId=findUserId(Authentication,principal);
        mv.addObject("addr",myPageService.getAddress(user.getUserId()));
        mv.setViewName("/mypage/address_list");        
        return mv;
    }

    /* 배송지 등록 및 수정 */
    @RequestMapping("/setAddress")
    @ResponseBody
    public CommonApiResponseVo<String> setAddress(AddressVo addrVo,@AuthenticationPrincipal UserVo user){
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        addrVo.setUserId(user.getUserId());
        System.out.println(addrVo);
        int result = myPageService.setAddress(addrVo);
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.mypage.user.address"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }
    /* 배송지 삭제*/
    @RequestMapping("/deleteAddress")
    @ResponseBody
    public CommonApiResponseVo<String> deleteAddress(AddressVo addrVo,@AuthenticationPrincipal UserVo user){
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        addrVo.setUserId(user.getUserId());
        int result = myPageService.deleteAddress(addrVo);
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.mypage.user.address.del"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }
    /* 회원정보 페이지 */
    @RequestMapping("/userModify")
    public ModelAndView getUserModify(@AuthenticationPrincipal UserVo user) {
        ModelAndView mv = new ModelAndView();
        UserVo userInfo= new UserVo();
        userInfo = myPageService.findbyUserInfo(user.getUserId());       
        mv.addObject("userInfo",userInfo);
        mv.setViewName("/mypage/user_modify");
        return mv;
    }

    /* 닉네임 중복 확인 */
    @RequestMapping("/nickCheck")
    @ResponseBody
    public CommonApiResponseVo<String> proNickCheck(String userNickname){
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        int result = myPageService.countByNickCheck(userNickname);
        //int result = myPageService.nickCheck(userNickname);
        response.setResult(true);
        if(result == 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.mypage.user.nick"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error.mypage.nick"));
        }
        return response;        
    }
    
    /* 회원정보 수정 */
    @RequestMapping("/userModify/complete")
    @ResponseBody
    public CommonApiResponseVo<String> proUserModify(UserVo userVo,@AuthenticationPrincipal UserVo user){
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        //String userId=findUserId(Authentication,principal);
        userVo.setUserId(user.getUserId());
        //정보 수정
        int result = myPageService.setUserDetails(userVo);
        if(result != 0) {
            //수정된 정보 불러오기
            userVo = myPageService.findbyUserInfo(user.getUserId());
            if(user !=null) {
                user.setUserNickname(userVo.getUserNickname());
                user.setUserPhone(userVo.getUserPhone());
            }
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.mypage.user.info"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }
    
    /* 비밀번호 변경 페이지 */
    @RequestMapping("/pwModify")
    public ModelAndView getPwModify() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mypage/password_modify");        
        return mv;
    }

    /* 비밀번호 변경 */
    @RequestMapping("/pwModify/complete")
    @ResponseBody
    public CommonApiResponseVo<String> proPwModify(String nowUserPw, String newPw,@AuthenticationPrincipal UserVo user){
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        UserVo userVo = new UserVo();
        userVo.setUserId(user.getUserId());
        userVo.setUserPw(nowUserPw);
        //현재 비밀번호 확인
        int result = myPageService.comparePw(userVo);
        if(result !=0) {
            //비밀번호 수정
            userVo.setUserPw(newPw);
            result = myPageService.setUserPw(userVo);
            if(result !=0) {
                response.setResult(true);
                response.setMessage(messageSource.getMessage("massage.mypage.user.pw"));
            }else {
                response.setResult(false);
                response.setMessage(messageSource.getMessage("message.error"));
            }
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error.mypage.pw"));
        }
        return response;
    }

    /* 주소 팝업창 */
    @RequestMapping("/addrPop")
    public ModelAndView getPopUp(AddressVo addrVo,Integer index,@AuthenticationPrincipal UserVo user) {
        ModelAndView mv = new ModelAndView();
        addrVo.setAddrIndex(index);
        addrVo.setUserId(user.getUserId());
        mv.addObject("addr",myPageService.getSelectAddress(addrVo));
        mv.setViewName("/pop/address_pop");        
        return mv;
    }
}
