package com.onlive.common.vo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class CustomUserDetails  implements UserDetails {
    
    private String userId;
    private String userPw;
    private String userRole;
    private UserVo user;
    private boolean isEnabled = true;              //사용자활성화
    private boolean isAccountNonExpired = true;    //계정만료
    private boolean isAccountNonLocked = true;     //계정잠김
    private boolean isCredentialsNonExpired = true;//비밀번호만료

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority(userRole));
        return authList;
    }
    @Override
    public String getUsername() {
        return userId;
    }
    @Override
    public String getPassword() {
        return userPw;
    }
}
