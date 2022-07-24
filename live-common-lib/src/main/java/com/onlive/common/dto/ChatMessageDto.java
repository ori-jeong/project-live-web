package com.onlive.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {
    private String liveId;
    private String chatRoomId;  //채팅 고유 번호
    private String writer;      //작성자
    private String message;     //메세지
    
}
