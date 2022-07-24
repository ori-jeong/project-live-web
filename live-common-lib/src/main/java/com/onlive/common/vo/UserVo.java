package com.onlive.common.vo;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data     //@Getter,@Setter,@ToString,@EqualsAndHashCode,@RequiredArgsConstructor를 합쳐놓은 어노테이션
@Builder  //생성자
@AllArgsConstructor  //모든 필드 값을 파라미터로 받는 생성자로 만들어줌
@NoArgsConstructor   //final이나 @NonNull인 필드 값만 파라미터로 받는 생성자
public class UserVo implements OAuth2User,UserDetails{
    private String userId;
    private String userPw;
    private String userUname;
    private String userNickname;
    private String userPhone;
    private String userRole;
    
    private Collection<? extends GrantedAuthority> authorities; //자동로그인, sns 권한 저장

    private SignUpVo signUpVo;
    private WithdrawVo withdrawVo;
    
    private String selName;
    
    @Builder
    public UserVo(String userId,String userUname,String userNickname,SignUpVo userPlatform,String userRole) {
        this.userId = userId;
        this.userUname = userUname; 
        this.userNickname = userNickname;
        this.signUpVo = userPlatform;
        this.userRole = userRole;
    }
    @Builder
    public UserVo(String userId,String userUname, String userNickname, String userPhone, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.userUname = userUname;
        this.userNickname = userNickname;
        this.userPhone = userPhone;
        this.authorities = authorities;
    }

    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignUpVo{
        /* 유저 가입 */
        private String userPlatform; 
    }
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignInVo{
        /* 로그인 정보 */
        private String sessionId;
        private String userRemoteIp;
        private String connDate;      
    }
    
    @Data
    @Builder 
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WithdrawVo{
        /* 유저 탈퇴 */
        private String userWithdrawYn;
    }
    
    /* OAuth2User */
    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }
    
    //UserDetails 권한 목록
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    //권한 이름
    @Override
    public String getName() {
        return userId;
    }

    /* UserDetails */
    @Override
    public String getPassword() {
        return null;
    }
    @Override
    public String getUsername() {
        return userId;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    public String getUserUname() {
        return userUname;
    }

}
