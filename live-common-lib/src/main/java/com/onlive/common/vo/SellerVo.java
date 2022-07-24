package com.onlive.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder 
@AllArgsConstructor
@NoArgsConstructor
public class SellerVo {             //판매자 정보
    private String selId;           //판매자 아이디
    private String selCpName;       //판매자 업체 이름
    private String selCode;         //판매자 사업자번호
    private String selName;         //판매자 이름
    private String fileId;          //판매자 이미지
    private String selRole;         //유저권한
    private String selRegDate;      //판매자 가입 날짜
    private String selWithdrawDate; //판매자 탈퇴 날짜
    private String selWithdrawYn;   //판매자 탈퇴여부
    
    private String  uploadPath;
    private String  fileName;
    
    private SellerDetailVo sellerDetailVo;
    private SellerLiveVo   sellerLiveVo;
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor   
    public static class SellerDetailVo{
        private String selPhone;        //판매자 번호
        private String selPostcode;     //우편번호
        private String selRoadaddr;     //도로명주소
        private String selDetailaddr;   //상세주소       
    }
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor    
    public static class SellerLiveVo{   //판매자 라이브 기본정보
        private String selId;           //판매자 아이디
        private String selStreamKey;      //방송스크림키
        private String selChatKey;         //채팅창 id
    }
}
