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
public class CartVo {                   //장바구니 및 주문페이지 vo
    private String  userId;             //유저 id 
    private String  psIndex;            //판매 게시물 id
    private String  selId;              //판매자 id
    private String  selName;            //판매자 명

    private List<CartVo.CartPdSaleVo> cartPdSaleVo;
    private List<CartVo.CartPdVo> cartPdVo;
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor    
    public static class CartPdSaleVo{
        private String  psIndex;        //판매 게시물 id
        private String  selId;          //기업 id
        private String  psTitle;        //판매 게시물 제목
        private String  fileId;         //판매 게시물 img
        //private Integer psDiscount;   //판매 게시물 할인율
        private Integer psDelivery;     //판매 게시물 배송비
        private String  uploadPath;
        private String  fileName;
    }
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CartPdVo{
        private String  psIndex;        //판매 게시물 id
        private String  pdId;           //상품 id
        private String  pdName;         //상품 명
        private String  pdPrice;        //상품 가격
        private Integer pdCount;        //상품 개수
        private Integer pdCountPrice;     //(주문 개수 * 1개 가격)

    }
}
