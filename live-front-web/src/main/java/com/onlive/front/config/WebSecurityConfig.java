package com.onlive.front.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.onlive.common.service.CustomOAuth2UserService;
import com.onlive.common.service.CustomUsersDetailService;
import com.onlive.common.vo.type.UserRole;


@Configuration
@EnableWebSecurity   //Spring Security 설정들을 활성화시켜 줌
//@RequiredArgsConstructor
//@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private AuthProvider authProvider;

    @Autowired
    private AuthFailureHandler authenticationFailureHandler;

    @Autowired
    private AuthSuccessHandler authenticationSuccessHandler;
    
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;
    @Autowired
    private CustomUsersDetailService customUsersDetailService;


    @Override
    public void configure(WebSecurity web) throws Exception {
        List<String> excludePathList = new ArrayList<>();
        excludePathList.add("/css/**");
        excludePathList.add("/img/**");
        excludePathList.add("/js/**");
        excludePathList.add("/lib/**");
        String[] excludePathArr = excludePathList.toArray(new String[excludePathList.size()]);

        web.httpFirewall(defaultHttpFirewall());
        // static 디렉터리의 하위 파일 목록은 security가 무시할 수 있도록 설정
        // 파일 기준은 resources/static 디렉터리
        web.ignoring().antMatchers(excludePathArr);
    }

    /* <security 권한 설정 및 표현식> 
     * authenticated():             인증된 사용자의 접근을 허용
     * fullyAuthenticated():        인증된 사용자의 접근을 허용,  rememberMe인증 제외
     * permitAll():                 무조건 허용
     * denyAll():                   무조건 차단
     * anonymous():                 익명사용자 허용
     * rememberMe():                rememberMe 인증 사용자 접근 허용
     * access(String):              주어진 SpEL표현식의 평가 결과가 true 이면 접근허용
     * hasRole(String):             사용자가 주어진 역할이 있다면 접근을 허용
     * hasAuthority(String):        사용자가 주어진 권한이 있다면 허용
     * hasAnyRole(String...):       사용자가 주어진 어떤권한이라도 있으면 허용
     * hasAnyAuthority(String...):  사용자가 주어진 권한중 어떤 것이라도 있다면 허용
     * 출처: https://fenderist.tistory.com/411 [Devman]
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> permitAllPathList = new ArrayList<>();
        permitAllPathList.addAll(Arrays.asList("/", "/index")); // index 페이지
        permitAllPathList.addAll(Arrays.asList("/error", "/error/**","/test/**")); // 에러,테스트 페이지
        permitAllPathList.addAll(Arrays.asList("/login/**","/oauth2/**", "/login", "/loginProcess", "/logout")); // 로그인, 인증, 로그아웃 페이지
        permitAllPathList.addAll(Arrays.asList("/signup", "/signup/**", "/find", "/find/**")); // 회원가입, 계정 찾기 페이지
        permitAllPathList.addAll(Arrays.asList("/shop/**", "/hls/**","/live/**","/replay/**")); // 카테고리, 영상 페이지
        permitAllPathList.addAll(Arrays.asList("/stomp/**","/stomp/chat/**")); // 채팅
        String[] permitAllPathArr = permitAllPathList.toArray(new String[permitAllPathList.size()]);
        // authorizeRequests 특정 경로에 특정 권한을 가진 사용자만 접근 가능
        // permitAll 모든사용자 가능
        // formLogin form 태그 기반의 로그인을 지원
        http.authorizeRequests()                                    // URL 별 권한 관리 설정하는 옵션의 시작점(antMatchers 사용 가능)
                // MVC Request 설정
                .antMatchers(permitAllPathArr).permitAll()          // antMatchers(): URL, HTTP 메소드 별로 권한 관리 대상 지정 옵션
                .antMatchers("/mypage/**").hasAnyAuthority(UserRole.MEMBER.getRole(),UserRole.EMPLOYEE.getRole())
                .antMatchers("/seller_insert").hasAnyAuthority(UserRole.MEMBER.getRole())
                .antMatchers("/tool/**").hasAnyAuthority(UserRole.EMPLOYEE.getRole())
                .antMatchers("/api/v1/**").hasAnyAuthority(UserRole.MEMBER.getRole()) //API는 USER 권한을 가진 사람만 가능하도록 함
                .anyRequest().authenticated()                       // 설정된 값 외의 나머지 URL은 authenticated()을 추가해 인증된 사용자만 허용(로그인한 유저)
                .and()
                    .formLogin()                                    // form태그 기반의 로그인                                                                                                         // 지원
                    .usernameParameter("userId")                    // id 파라미터 명
                    .passwordParameter("userPw")                    // pw 파라미터 명
                    .loginPage("/login")                            // 로그인 페이지
                    .loginProcessingUrl("/loginProcess")            // 로그인 처리 URL
                    .failureUrl("/login?error=true")                // 로그인 실패 후 페이지
                    .failureHandler(authenticationFailureHandler)   // 로그인 실패 핸들러
                    .successHandler(authenticationSuccessHandler)   // 로그인 성공 핸들러
                    .permitAll()
                .and()
                    .rememberMe()                                   // 자동로그인
                    .rememberMeParameter("rememberMe")              // 자동로그인 파라미터 명
                    .tokenValiditySeconds(86400 * 30)               // 쿠키 만료 시간 설정(30일)                    
                    .userDetailsService(customUsersDetailService)   // 시스템의 사용자 계정을 조회할 때 처리 설정하는 api
                    .tokenRepository(tokenRepository())             //DataSource 추가
                .and()
                    .logout()                                       // 로그아웃 기능 설정 진입점
                    .logoutUrl("/logout")                           // 로그아웃 처리 URL
                    .logoutSuccessUrl("/")                          // 로그아웃 성공 시 입력한 주소로 리다이렉트
                    .invalidateHttpSession(true)                    // 세션 정보 삭제
                    .deleteCookies("JSESSIONID","rememberMe")       // 로그아웃 후 자동 로그인 쿠키 삭제
                    .permitAll()
//                .and()  // csrf를 설정하여 사용할 경우 특정 URL 외부 프로그램등에서 POST 방식으로 서버 접근시 403에러 발생
//                    .csrf().disable()                             // csrf 미적용
//                    .exceptionHandling()                          // 예외처리 기능이 작동
//                    .accessDeniedHandler(userDeniedHandler)       // 인가 실행시 처리
                .and()
                    .headers()
                    .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'")).frameOptions().disable()
//                .and()                                                  
//                    .headers()                                              
//                    .frameOptions()                                 // iframe 접근 허용을 위해 'X-Frame-Options'을
//                    .sameOrigin()                                   // 같은 도메인,포트라 인식
//                .and()
//                    .headers()
//                    .frameOptions()
//                    .disable()                                      
//                    .addHeaderWriter(new StaticHeadersWriter("X-FRAME-OPTIONS", "ALLOW-FROM 192.168.219.100:8080/live/video"))
                .and()
                    .cors()                                         // 자원 공유 권한 체제
                .and()      
                    .csrf()                                         //csrf 공격을 막아주는 CsrfFilter 제공
                    .ignoringAntMatchers("/loginProcess","/stomp/**")           // csrf예외처리  
                .and()
                    .oauth2Login()                                  //  OAuth2 로그인 기능 설정 진입점
                    .loginPage("/login")
                    .userInfoEndpoint()                             //  OAuth2 로그인 성공 이후 사용자 정보 가져올 설정 담당
                    .userService(customOAuth2UserService);          //  소셜 로그인 성공 후 처리 구현체 등록    
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {        
      // JDBC 기반의 tokenRepository 구현체
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource); // dataSource 주입
        return jdbcTokenRepository;
    }
    
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{ 
        auth.authenticationProvider(authProvider); 
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/stomp/**")
                        .allowedOrigins("*")
                        .allowedMethods(HttpMethod.POST.name())
                        .allowCredentials(false)
                        .maxAge(3600);
            }
        };
    }
}
