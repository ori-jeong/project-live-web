package com.onlive.common.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.onlive.common.dto.ChatRoomDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private Map<String, ChatRoomDto> chatRooms;
    
    //의존관계 주입완료되면 실행되는 코드
    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }
    
    //채팅방 생성
    public ChatRoomDto createRoom(String name) {
        ChatRoomDto room = new ChatRoomDto();
        String roomId = UUID.randomUUID().toString();
        room.setChatRoomId(roomId);
        room.setName(name);
        chatRooms.put(roomId, room);
        return room;        
    }
    
    //선택한 채팅방 불러오기(선택한 채팅방에 메세지 전송)
    public ChatRoomDto findRoomById(String chatRoomId) {
        return chatRooms.get(chatRoomId);        
    }

}
