package com.onlive.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableScheduling
@EnableWebSocketMessageBroker
//메세지 브로거에 대한 설정을 해주는 config
public class StompWebSocketConfig  implements WebSocketMessageBrokerConfigurer{
    
    @Value("${onl.chat.url}")
    private String chatUrl;
     
    /*client에서 websocket연결할 때 사용할 API 경로를 설정해주는 메서드*/
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
       // registry.addEndpoint("/stomp/chat").withSockJS();
        
        registry.addEndpoint("/stomp/chat")  // 새로운 커넥션 생성시 사용됨 ex)new SockJS("/stomp/chat");
                .setAllowedOrigins(chatUrl) // 주소입력을 *(전체허용)로 하면 보안상의 문제 발생할 수 있음
                .withSockJS();              // SockJS를 추가해 모든 브라우저에서 사용할 수 있게 한다
    }
    /*어플리케이션 내부에서 사용할 path를 지정할 수 있음*/
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/pub"); //메시지 보낼 때 관련 경로 설정
        registry.enableSimpleBroker("/sub");                //메세지 받을 때 관련 경로
        
    }

}
