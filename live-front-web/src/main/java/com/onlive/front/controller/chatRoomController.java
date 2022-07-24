package com.onlive.front.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlive.common.dto.ChatMessageDto;
import com.onlive.common.service.ChatService;
import com.onlive.common.service.LiveService;
import com.onlive.common.vo.LiveVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class chatRoomController {
    private final ChatService chatService;
    private final LiveService liveService;
    private final SimpMessageSendingOperations sendingOperations; //특정 broker로 메세지 전달
    //private static final List<String> countList = new ArrayList<String>();
    
    //private static final List<LiveVo.LiveChatVo> countListMap = new ArrayList<LiveVo.LiveChatVo>();
    
    //채팅방생성
    @RequestMapping("/chat/room")
    @ResponseBody
    public void createRoom(@RequestParam String name) {
        chatService.createRoom(name);
    }
    
    //@MessageMapping : WebSocket으로 들어오는 메세지를 받는다
    //Client에서는 prefix를 붙여 "/sub/chat"로 발행 요청하면 Controller가 해당 메세지를 받아 처리하는데
    //메세지가 발행되면 '/sub/chat/5-;/0[chatRoomId]'로 메세지가 전송 된다
    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message) {
        sendingOperations.convertAndSend("/sub/chat/room/"+message.getChatRoomId(), message);
    }
    
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDto message) {
        //채팅방 id 추가
        //countList.add(message.getLiveId());
        //해당 채팅방 id 값 개수 출력
        //int count = Collections.frequency(countList,message.getLiveId());
        
        //뷰 수 변경
        LiveVo liveVo = new LiveVo();
        liveVo.setLiveId(message.getLiveId());
        //liveChatvo.setLiveView(count);
        //뷰 수 가져오기
        liveService.setLiveView(liveVo);
        int view = liveService.getLiveView(liveVo);
        //뷰 수 전달
        sendingOperations.convertAndSend("/sub/chat/count/" + message.getChatRoomId(),view);
        //람다식을 이용해 list안에 map 데이터 찾기
       //countListMap.stream().filter(x ->x.getLiveId().equals(message.getLiveId())).findAny();

    }
}
