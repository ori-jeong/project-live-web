package com.onlive.front.config.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.onlive.front.LiveFrontWebApplication;

import lombok.RequiredArgsConstructor;


@EnableWebMvc
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public LiveFrontWebApplication frontInterseptor() {
        return new LiveFrontWebApplication();
    }
    @Bean
    public MessageSource messageSource() { // 빈 이름은 무조건 messageSource      
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("message"); //properties 공통이름
        messageSource.setDefaultEncoding("UTF-8");
        
        return messageSource;
    }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");

        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathList = new ArrayList<>();
        excludePathList.add("/css/**");
        excludePathList.add("/img/**");
        excludePathList.add("/js/**");
        excludePathList.add("/lib/**");
       // String[] excludePathArr = excludePathList.toArray(new String[excludePathList.size()]);

        registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**");
        //registry.addInterceptor((HandlerInterceptor) frontInterseptor()).excludePathPatterns(excludePathArr);
    }
    // configureViewResolvers() = @EnableWebMvc에 의한 세팅 값 + 사용자에 의한 추가 
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
      registry.jsp("/WEB-INF/views/", ".jsp");
    }
    // 정적 파일의 경로를 매핑한다. 
    @Override 
    public void addResourceHandlers(ResourceHandlerRegistry registry) { 
        registry.addResourceHandler("/img/**","/css/**","/js/**")
            .addResourceLocations("classpath:/static/img/"
                  ,"classpath:/static/css/"
                  ,"classpath:/static/js/","classpath:/tiles.xml");
    }
    // customViewResolver = @EnableWebMvc 세팅 값 직접 구현 + 사용자에 의한 추가 세팅
    @Bean
    public ViewResolver customViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }
}
