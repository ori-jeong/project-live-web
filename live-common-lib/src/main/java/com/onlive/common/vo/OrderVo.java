package com.onlive.common.vo;

import java.math.BigInteger;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder 
@AllArgsConstructor
@NoArgsConstructor
public class OrderVo {                  //주문내역VO
    private String orderId;             //주문 코드
    private String orderPdId;
    private String userId;              //유저 id 
    private String selId;               //기업 id
    private String selName;             //판매자 명
    private String psIndex;             //판매 게시물 id  
    private String  psDeliveryOpt;
    private String addrIndex;           //유저 배송지 코드
    private String paymentStatus;        //결제 상태
    private String orderStatus;          //주문 상태
    private String orderDate;           //주문 날짜
    
    private List<OrderVo.OrderPdSelVo> orderPdSelVo;
    private List<OrderVo.OrderPdVo> orderPdVo;
    private OrderVo.PaymentVo paymentVo;
    private OrderVo.OrderAddrVo orderAddrVo;
    private List<OrderVo.UserOrderListVo> userOrderListVo;

    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor 
    public static class OrderPdSelVo{
        private String  selId;          //기업 id
        private String  psIndex;        //판매 게시물 id
        private String  psTitle;        //판매 게시물 제목
        private String  fileId;         //판매 게시물 img
        private String  psDeliveryOpt;  //판매 게시물 배송 옵션
        //private Integer psDiscount;   //판매 게시물 할인율
        private Integer psDelivery;     //판매 게시물 배송비
    }
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor 
    public static class OrderPdVo{
        private String  orderPdId;          //주문 상품 번호
        private String  psIndex;            //판매 게시물 id
        private String  pdId;               //상품 id
        private String  pdName;             //상품 명
        private Integer pdCount;            //상품 개수
        private BigInteger pdCountPrice;    //(주문 개수 * 1개 가격)
    }
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor 
    public static class PaymentVo{
        private String paymentId;           //결제 수단 코드
        private String orderId;             //주문 코드
        private String paymentType;         //결제수단
        private String totalPdPrice;        //총 상품 주문 금액
        private String totalDelivery;       //총 배송비 
        private BigInteger totalPrice;      //총 주문 금액
    }
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor 
    public static class OrderAddrVo{
        private String orderId;         //주문 코드        
        private String addrName;        //배송지 이름
        private String addrRecipient;   //받는 사람
        private String addrPostcode;    //받는 우편번호
        private String addrRoadaddr;    //받는 도로명 주소
        private String addrDetailaddr;  //받는 상세 주소
        private String addrContact;     //받는 사람 전화번호
    }
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor 
    public static class CancelOrderVo{
        private String      cancelOrderId;      //주문 취소 ID
        private String      selId;              //기업 id
        private String      orderId;            //주문 코드
        private BigInteger  cancelOrderPrice;   //주문취소 금액
    }
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserOrderListVo{
        private String  orderId;
        private String  orderPdId;
        private String  psIndex;
        private String  pdId;
        private Integer pdCount;            
        private BigInteger pdCountPrice;    
        private String  paymentStatus;       
        private String  orderStatus;         
        private String  selName;            
        private String  psTitle;            
        private String  uploadPath;
        private String  fileId;
        private String  orderDate;
    }
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor 
    public static class OrderStatusCountVo{
        private String  paymentStatus;          //결제상태
        private String  paymentStatusCount;     //결제상태개수
        private String  orderStatus;            //주문상태
        private String  orderStatusCount;       //주문상태개수
    }
}
