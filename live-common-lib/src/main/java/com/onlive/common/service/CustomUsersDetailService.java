package com.onlive.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onlive.common.jpa.UserRepository;
import com.onlive.common.vo.UserVo;
import com.onlive.common.vo.type.UserRole;

import lombok.RequiredArgsConstructor;


@Service("userDetailsService") 
@RequiredArgsConstructor
public class CustomUsersDetailService implements UserDetailsService{
    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserVo user = userRepository.findbyUserInfo(userId);
        if (user == null){
            throw new UsernameNotFoundException(userId + "is not found. ");
        }    
        /* 유저 권한 정보 넣기  - 자동로그인*/
        // userRole =UserRole.fromRole(user.getUserRole());
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        if(UserRole.MEMBER.getKey().equals(user.getUserRole())) {
            authorities.add(new SimpleGrantedAuthority(UserRole.MEMBER.getRole()));
        }else if(UserRole.EMPLOYEE.getKey().equals(user.getUserRole())) {
            authorities.add(new SimpleGrantedAuthority(UserRole.EMPLOYEE.getRole()));
        }else if(UserRole.ADMIN.getKey().equals(user.getUserRole())){
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getRole()));
        }else if(UserRole.STOP.getKey().equals(user.getUserRole())) {
            authorities.add(new SimpleGrantedAuthority(UserRole.STOP.getRole()));
        }else{
            authorities.add(new SimpleGrantedAuthority(UserRole.UNKNOWN.getRole()));
        }
        
        user.setAuthorities(authorities);
        user.setUserPw(null);
        
        return user;
    }
}
