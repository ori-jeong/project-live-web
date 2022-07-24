package com.onlive.common.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onlive.common.jpa.UserRepository;
import com.onlive.common.mapper.AdminMapper;
import com.onlive.common.mapper.MyPageMapper;
import com.onlive.common.vo.AddressVo;
import com.onlive.common.vo.OrderVo;
import com.onlive.common.vo.SalesVo;
import com.onlive.common.vo.UserVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MyPageMapper myPageMapper;
    private final UserRepository userRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    //private AuthenticationManager authenticationManager;
    //private CustomOAuth2UserService oAuth2User;
    
    private final AdminMapper adminMapper;
    
    public List<OrderVo.OrderStatusCountVo> getUserOrderStatusCount(OrderVo order){
        return myPageMapper.getUserOrderStatusCount(order);
    }
    
    public List<OrderVo> getUserOrderInfoList(OrderVo order){
        return myPageMapper.getUserOrderInfoList(order);
    }
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public int cancelOrderProcess(SalesVo salesVo){
        
        //주문 취소 코드 생성
        LocalDateTime  nowDate = LocalDateTime .now();
        DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("yyMMdd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmmss");
        String formatedNow = nowDate.format(dayFormat);
        int orderCount = adminMapper.getCancelOrderCount(formatedNow);
        String day = nowDate.format(dayFormat);
        String time = nowDate.format(timeFormat);
        String cancelOrderId = day+time+orderCount;
        
        //주문취소 상태코드 넣기
        salesVo.setOrderStatus("40"); 
        
        OrderVo.CancelOrderVo cancelOrderVo = new OrderVo.CancelOrderVo();
        OrderVo.CancelOrderVo svo = myPageMapper.getCancelTotalPrice(salesVo);
        cancelOrderVo.setCancelOrderId(cancelOrderId);
        cancelOrderVo.setOrderId(svo.getOrderId());
        cancelOrderVo.setCancelOrderPrice(svo.getCancelOrderPrice());
        
        myPageMapper.insertCancelOrderUser(cancelOrderVo);
        return myPageMapper.cancelOrderProcess(salesVo);
    }
    public int confirmationProcess(OrderVo order){
        return myPageMapper.confirmationProcess(order);
    }
    public List<UserVo> getAddress(String userId) {
        return myPageMapper.getAddress(userId);
    }
    
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public int setAddress(AddressVo addrVo){
        if(addrVo.getAddrIndex()!=0) {
            //해당 번호 배송주소 삭제
            myPageMapper.deleteByAddress(addrVo);
        }
        //유저 기본배송지 설정
        if(addrVo.getAddrYn().equals("Y")) {
            myPageMapper.updateAddrNo(addrVo);
        }
        //해당 번호 배송 주소 새로 등록
        return myPageMapper.setAddress(addrVo);
    }
    
    public int deleteAddress(AddressVo addrVo) {
        return myPageMapper.deleteByAddress(addrVo);
    }
    public AddressVo getSelectAddress(AddressVo addrVo) {
        return myPageMapper.getSelectAddress(addrVo);
    }
    
    public int countByNickCheck(String userNickname) {
        return userRepository.countByNickCheck(userNickname);
    }   
    public UserVo findbyUserInfo(String UserId) {
        return userRepository.findbyUserInfo(UserId);
    }

    public int setUserDetails(UserVo user) {
        int result = myPageMapper.setUserDetails(user);
        //UserVo uservo =  findbyUserInfo(user.getUserId());
        return result;
    }
    public int comparePw(UserVo user) {
        int result = 0;
        String userPw = myPageMapper.comparePw(user);
        if(encoder.matches(user.getUserPw(),userPw)) {
            result =1;
        }
        return result;
    }
    public int setUserPw(UserVo user) {
        user.setUserPw(encoder.encode(user.getUserPw()));
        return myPageMapper.setUserPw(user);
    }
    public AddressVo getBasicAddress(String userId) {
        return myPageMapper.getBasicAddress(userId);
    }

}
