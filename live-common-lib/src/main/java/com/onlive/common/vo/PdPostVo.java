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
public class PdPostVo {              //판매글VO
    private String  selId;           //판매자 아이디
    private String  psIndex;         //판매글 id
    private String  psCate;          //판매글 카테고리
    private String  psCateName;      //판매글 카테고리 이름
    private String  psTitle;         //판매글 제목
    private String  psPrice;         //판매글 기본 가격
    private String  psDiscount;      //판매글 할인율
    private String  fileId;          //판매글 대표이미지
    private Integer psDeliveryOpt;   //배송 방법(0:무료,1:일반)
    private Integer psDelivery;      //배송비
    private String  psContent;       //판매 상세글
    private String  psPostStatus;     //판매글 상태
    
    private List<PdPostAddVo> pdPostAddVo;
    private PdPostFileVo    pdPostFileVo;
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdPostAddVo{ //판매글의 추가 상품
        private String psIndex;     //판매글 id
        private String pdId;        //상품 id   
        private String pdName;      //상품 이름
        private String PdPrice;     //상품 가격
    }
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdPostFileVo{
        private String fileId;
        private String uploadPath;
        private String fileName;
        
    }
}
