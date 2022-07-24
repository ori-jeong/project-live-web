package com.onlive.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder 
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileVo {
    private String psIndex;     //판매글 id
    private String fileId;      //파일번호
    private String uploadPath;  //파일경로(날짜)
    private String fileName;    //파일이름
}
