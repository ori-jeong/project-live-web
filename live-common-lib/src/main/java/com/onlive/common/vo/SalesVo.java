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
public class SalesVo {
    private String      selId;              //판매자 아이디
    private String      orderId;            //주문 코드
    private String      orderPdId;          //주문 상품 코드
    private String      paymentStatus;       //결제상태
    private String      orderStatus;         //주문상태
    private String      psIndex;            //상품 id
    private String      pdId;               //상품 id
    private String      pdName;             //상품명
    private Integer     pdCount;            //주문개수
    private BigInteger  pdCountPrice;       //주문금액
    private Integer     psDeliveryOpt;      //배송 방법(0:무료,1:일반)
    private String      psDelivery;         //배송비
    private String      userId;             //구매자id
    private String      addrRecipient;      //수취인명
    private String      orderDate;          //주문 날짜

    private String      cancelOrderId; //주문 취소 ID
    private String      uploadPath;
    private String      fileName;
    
    private List<SalesVo.SalesIdsVo> salesIdsVo;
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor 
    public static class SalesIdsVo{
        private String      orderId;            //주문 코드
        private String      orderPdId;          //주문 상품 코드
        private String      psIndex;             //상품 id

    }

    
}
