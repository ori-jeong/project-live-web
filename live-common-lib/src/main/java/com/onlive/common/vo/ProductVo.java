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
public class ProductVo {
    private String  selId;     //판매자 아이디
    private String  pdId;      //상품 id
    private String  pdName;    //상품명
    private Integer pdPrice;   //상품 가격
    private Integer pdStrock;  //상품 재고량
    private String  pdStatus;   //상품 전시상태
    private String  pdDate;    //상품 등록일
    
    private List<ProductVo.ProductIdList> productIdList;
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductIdList{ //판매글의 추가 상품
        private String  pdId;      //상품 id  
    }
    
}
