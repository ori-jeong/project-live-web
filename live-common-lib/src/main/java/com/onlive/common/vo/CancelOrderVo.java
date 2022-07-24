package com.onlive.common.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder 
@AllArgsConstructor
@NoArgsConstructor
public class CancelOrderVo {
    private String cancelOrderId;      //주문 취소 코드
    private String selId;              //판매자 아이디
    private String orderId;            //주문 코드
    private String pdId;               //상품 id
    private String paymentStatus;       //취소상태

    private List<CancelOrderVo.CancelIdsVo> cancelIdsVo;

    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor 
    public static class CancelIdsVo{
        private String      orderId;            //주문 코드
        private String      pdId;               //상품 id
    }
    
}
