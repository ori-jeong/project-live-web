package com.onlive.common.dto;

import java.util.HashSet;
import java.util.Set;
import org.springframework.web.socket.WebSocketSession;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatRoomDto {      // 각 채팅방을 구분할 수 있는 정보를 담은 Dto
    private String chatRoomId;  //채팅고유번호
    private String name;        //채팅방이름
    //WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션
    private Set<WebSocketSession> session = new HashSet<>();
    private Integer userCount = 0;
}
