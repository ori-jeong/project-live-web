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
public class LiveVo {
    private String  selId;
    private String  liveId;
    private Integer cateIndex;
    private String  psIndex;
    private String  liveTitle;
    private String  fileId;
    private String  liveStartDay;    
    private String  liveStartTime;
    private String  liveEndTime;
    private String  liveView;
    private String  liveStatus;
    private String  region1Code;
    private String  uploadPath;
    private String  fileName;
    private String  videoId;
    private String  videoPath;
    private String  videoName;
    
    private LivePdVo livePdVo;
    private LiveSellerVo liveSellerVo;
    private List<LiveVo.LiveVideoVo> liveVideoVo;
    
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LivePdVo{
        private String psIndex;         //판매글 id
        private String psTitle;         //판매글 제목
        private String psPrice;         //판매글 기본 가격
        private String psPostStatus;           
        private String psUploadPath;
        private String psFileId;
    }
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LiveSellerVo{
        private String selId;         
        private String selName;                
        private String selUploadPath;
        private String selFileId;
        private String selStreamKey;
        private String selChatKey;
    }
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LiveVideoVo{
        private String liveId;    
        private String selStreamKey;
        private String videoId;
        private String videoPath;
        private String videoName;

    }
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LiveChatVo{
        private String selChatKey;
        private String liveId;
        private int liveView;
    }
}
