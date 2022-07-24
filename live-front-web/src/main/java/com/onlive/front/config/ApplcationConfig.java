package com.onlive.front.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//import com.onlshopping.front.config.Connector;
//import com.aistudio.global.front.config.Connector;


//스프링 설정 클래스를 선언하는 어노테이션, bean 등록
//java config로 설정할 클래스 위에 @Configuration를 붙여준다
@Configuration
public class ApplcationConfig {
    
    //포트번호
    @Value("${server.http.port:8090}")
    private int httpPort;
       
    //톰캣 내장 서버는 기본적으로 커넥터가 1개로 설정되어 있어 http 프로토콜을 받기 위해 커넥터 하나 더 생성
    @Bean
    @Profile("dev")
    public ServletWebServerFactory servletContainer() {
        //자동 설정되어있는 서버를 커스터마이징한다
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createHttpConnector());
        return tomcat;
    }  
    
    private Connector createHttpConnector() {
        Connector httpConnector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        httpConnector.setPort(httpPort);
        return httpConnector;
    }
}
