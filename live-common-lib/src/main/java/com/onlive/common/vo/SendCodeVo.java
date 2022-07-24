package com.onlive.common.vo;

import org.thymeleaf.context.Context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor 
@NoArgsConstructor 
public class SendCodeVo {
    private String sendType;    //전송타입
    private String sendTo;      //수신자
    private String sendFrom;    //수신자
    private String authCode;    //전송된 인증번호
    private String sendDate;    //등록날짜
    private String subject;     //제목
    private String contents;    //내용
    
    private Context template;    //이메일 template context
    
    private String inputCode;   //입력코드
}
