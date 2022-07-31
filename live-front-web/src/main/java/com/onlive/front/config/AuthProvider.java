package com.onlive.front.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.onlive.common.service.LoginService;
import com.onlive.common.vo.UserVo;
import com.onlive.common.vo.type.UserRole;


@Component
public class AuthProvider implements AuthenticationProvider{
    
    //DB값을 가져오는 service
    @Autowired
    private LoginService loginService;
    
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    // 입력한 로그인 값과 DB 값을 비교해 처리하는 인증로직
    // 만약 <form-login>의 default=target-url 속성에 보내고싶은 값이 있을 때는
    // CustomUserDetail에 세팅을하고 아래에서 CustomUserDetail의 객체를 set해준다.
    // DB에 정보가 없는 경우 예외 발생 (아이디/패스워드 잘못됐을 때와 동일한 것이 좋음 => 악용방지)
    // 패스워드는 matches를 이용해 암호화를 체크해야한다
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        /* 사용자 입력 정보 */
        String userId = authentication.getName();
        String userPw = authentication.getCredentials().toString();
        
        /* DB에서 가져온 정보 */
        UserVo user = loginService.getUserLogin(userId);
        
        /* 인증 진행 */        
        if(user == null) { //사용자 정보 없음
            throw new UsernameNotFoundException(userId);
        }
        if(user.getWithdrawVo().getUserWithdrawYn().equals("Y")) {//탈퇴 유저
            throw new UsernameNotFoundException(userId);
        }else if(user.getUserRole().equals(UserRole.STOP.getRole())) {  //블럭 계정 확인
            throw new DisabledException(userId);
        }else if(!passwordEncoder.matches(userPw, user.getUserPw())) {  //비밀번호 비교
            throw new BadCredentialsException(userId);
        }
        /* 유저 권한 정보 넣기 */
        //UserRole userRole =UserRole.fromRole(user.getUserRole());
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if(UserRole.MEMBER.getKey().equals(user.getUserRole())) {                   //UserRole의 key값과 DB에 저장된 유저의 Role 값을 비교해
            authorities.add(new SimpleGrantedAuthority(UserRole.MEMBER.getRole())); //일치하는 UserRole 값을 부여해준다
        }else if(UserRole.EMPLOYEE.getKey().equals(user.getUserRole())) {
            authorities.add(new SimpleGrantedAuthority(UserRole.EMPLOYEE.getRole()));
        }else if(UserRole.ADMIN.getKey().equals(user.getUserRole())){
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getRole()));
        }else if(UserRole.STOP.getKey().equals(user.getUserRole())) {
            authorities.add(new SimpleGrantedAuthority(UserRole.STOP.getRole()));
        }else{
            authorities.add(new SimpleGrantedAuthority(UserRole.UNKNOWN.getRole()));
        }

        /* 인증 로직 진행 후 비밀번호 값 삭제 */
        user.setUserPw(null);
                
        /* 최종 리턴 시킬 Authentication 객체 생성
         * id,pw 값으로 UsernamePasswordAuthenticationToken 생성 */            
        /* UserVo로 유저 정보를 넘기면 jsp에서 <sec:authentication var="user" property="principal"/>를 사용해 값을 바로 빼서 사용 가능하지만
           자동 로그인은 안된다.(insert의 username 값에 UserVo값이 들어가 쿼리 오류 발생) 
           controller에서 @AuthenticationPrincipal UserVo user로 값을 받아올 수 있다*/
        /* 유저 아이디 값으로만 넘기면 controller에서 Authentication Authentication 를 사용해 아이디 값을 불러옴 
         * <sec:authentication..> 사용하면 아이디 값만 나옴*/        
        return new UsernamePasswordAuthenticationToken(user,null,authorities);
        //UsernamePasswordAuthenticationToken : 인증이 끝나고 SecurityContextHolder.getContext()에 등록될 Authentication 객체        
        //return new UsernamePasswordAuthenticationToken(user.getUserId(),null,authorities);
    }

    // 위의 authenticate 메소드에서 반환한 캑체가 유효한 타입이 맞는지 검사
    // null 값이거나 잘못된 타입을 반환했을 경우 인증 실패로 간주
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
