package com.onlive.front;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.onlive"})
@MapperScan(basePackages = {"com.onlive.**.mapper"})
@EntityScan(basePackages = {"com.onlive.common.entity"})
@EnableAutoConfiguration      //bean을 등록하는 자바 설정 파일
public class LiveFrontWebApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(LiveFrontWebApplication.class);
        application.run(args);
    }
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) { 
        return builder.sources(LiveFrontWebApplication.class); 
    }
}
