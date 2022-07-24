package com.onlive.common.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onlive.common.mapper.ShopMapper;
import com.onlive.common.vo.CartVo;
import com.onlive.common.vo.OrderVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopMapper shopMapper;
    
    /* 장바구니 등록 */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public int setCartList(CartVo cart) {
        //db에 해당 상품이 없으면 insert, 있으면 수량 update
        int result = 0;
        for(CartVo.CartPdVo cp : cart.getCartPdVo()) {
            cp.setPsIndex(cart.getPsIndex());
            int value = shopMapper.searchCartList(cp);
            if(value ==0) { //insert
                Map<String,Object> listCart = new HashMap<>();
                listCart.put("userId",  cart.getUserId());
                listCart.put("psIndex", cart.getPsIndex());
                listCart.put("pdId",    cp.getPdId());
                listCart.put("pdCount", cp.getPdCount());
                result = shopMapper.setCart(listCart);
            }else{ //update
                result = updateCartCount(cp);
            }
        }
        //return shopMapper.setCartList(cart);
        return result;
    }
    /* 장바구니 가져오기 */
    public List<CartVo> getCartList(String userId) {
        return shopMapper.getCartList(userId);
    }
    /* 장바구니 수량 수정 */
    public int updateCartCount(CartVo.CartPdVo cart) {
        return shopMapper.updateCartCount(cart);
    }
    /* 장바구니 삭제 */
    public int deleteCart(CartVo.CartPdVo cart) {
        return shopMapper.deleteCart(cart);
    }
    
    /* 장바구니에서 주문 할 상품 정보 가져오기 */
    public List<CartVo> getOrderProductFromCart(CartVo cart){
        return shopMapper.getOrderProductFromCart(cart);
    }
    /* 주문 할 상품 정보 가져오기 */
    public List<CartVo> getOrderProduct(CartVo cart){
        //List<CartVo> cartVo = shopMapper.getOrderProduct(cart);
        return shopMapper.getOrderProduct(cart);
    }
    public String setOrderId() {
        //java 8 이후의 날짜 구하기
        //LocalDate nowDate = LocalDate.now();  //날짜만
        LocalDateTime  nowDate = LocalDateTime.now();  //시간포함
        //날짜 포멧 정의
        DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("yyMMdd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmmss");
        //포멧 적용
        String day = nowDate.format(dayFormat);
        String time = nowDate.format(timeFormat);
        
        //Random rd = new Random(); //랜덤함수 선언 
        //int authCode = rd.nextInt(111, 999); //111부터 999 선에서 랜덤하게 가져오기

        //현재 주문 개수 가져오기
        int orderCount = shopMapper.getNowOdereCount(day+time);
        //주문번호 생성
        return (day+time+orderCount);
    }
    
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public int setOrders(OrderVo order,boolean fromCart) {
        int result = 0;
        OrderVo.OrderAddrVo orderAddrVo = new OrderVo.OrderAddrVo();
        orderAddrVo.setOrderId(order.getOrderId());
        Map<String,Object> addrMap = new HashMap<>();
        addrMap.put("orderId", order.getOrderId());
        addrMap.put("userId", order.getUserId());
        addrMap.put("addrIndex", order.getAddrIndex());

        order.getPaymentVo().setOrderId(order.getOrderId());

        result += shopMapper.setOrders(order);                   //주문 등록
        result += shopMapper.setOrderAddr(addrMap);              //주문한 주소 등록
        result += shopMapper.setPayment(order.getPaymentVo());   //주문 결제 등록
        
        if(fromCart) {
          //주문한 상품 장바구니 삭제
            result += shopMapper.deleteCartByOrder(order);
        }
  
        return result;
    }
}
