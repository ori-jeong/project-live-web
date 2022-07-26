package com.onlive.common.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onlive.common.mapper.LoginMapper;
import com.onlive.common.vo.SellerVo;
import com.onlive.common.vo.UserVo;
import com.onlive.common.vo.type.UserRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService{
    private final LoginMapper loginMapper;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //유저 로그인 정보 가져오기
    public UserVo getUserLogin(String userId) {  
        return loginMapper.getUserLogin(userId);
    }
    
    //유저 정보 등록하기(가입)
    public int insertUser(UserVo user) {
        UserVo.SignUpVo platform = new UserVo.SignUpVo();
        platform.setUserPlatform("onl_home");
        //UserVo.SignUp join = new UserVo.SignUp();
        user.setUserRole(UserRole.MEMBER.getKey());
        user.setSignUpVo(platform);
        String nick = user.getUserId();
        user.setUserNickname(nick.substring(0,nick.lastIndexOf("@")));
        //입력한 비밀번호 암호화
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));

        return loginMapper.insertUser(user);
    }
    //유저 정보 가져오기
    public UserVo getUserInfo(String userId) {
        return loginMapper.getUserInfo(userId); //유저 아이디로 유저 정보 가져오기
    }
    
    //유저 아이디 중복체크 및 아이디 찾기 ( 1: 있음 / 0: 없음)
    public int searchId(String userId) {
        return loginMapper.searchId(userId);
    }
    
    //유저 핸드폰 중복체크
    public int searchPhone(String userPhone) {
        return loginMapper.searchPhone(userPhone);  
    }    
      
    //아이디 찾기
    public int findIdInt(UserVo user) {
        return loginMapper.findIdInt(user);
    }
    //아이디 찾기-값
    public Map<String,Object> findId(UserVo user) {
        return loginMapper.findId(user);
    }
    //이번 비밀번호와 비교
    public int checkPw(UserVo user) {
        int result = 0;
        //입력한 비밀번호
        String inputPw = user.getUserPw();
        //이전 비밀번호
        String pw = loginMapper.checkPw(user);
        
        if(passwordEncoder.matches(inputPw, pw)){
            result = 1;
        }
        return result;
    }
    //아이디로 비밀번호 재설정
    public int resetPw(UserVo user){
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));
        return loginMapper.resetPw(user);
    }

    //유저 닉네임 중복체크
    public int searchNickname(String userNickname) {
        return loginMapper.searchNickname(userNickname);
    }

    //유저 정보 수정하기 핸드폰번호, 닉네임, 주소 등
    public int updateUserInfo(UserVo user) {
        return loginMapper.updateUserInfo(user);
    }
    
    //유저 비밀번호 수정(& 비번 찾기 시 사용) 
    // 아직 보류
    public int updateUserPw(String userPw) {
        return loginMapper.updateUserPw(userPw);
    }     
    
    
    //탈퇴하기

    //판매자 이름 중복 확인
    public int countBySelNameCheck(String selName) {
        return loginMapper.countBySelNameCheck(selName);
    }
    
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    //판매자 등록하기-유저 권한 변경
    public int insertSellerInfo(SellerVo selVo) {
        SellerVo.SellerLiveVo selStreamVo= new SellerVo.SellerLiveVo();
        //라이브키 생성
        UUID uuid = UUID.randomUUID(); 
        String liveStream = "live_"+ uuid.toString();
        //채팅키 생성
        String chatStream = "chat_"+ uuid.toString();
        selStreamVo.setSelId(selVo.getSelId());
        selStreamVo.setSelStreamKey(liveStream);
        selStreamVo.setSelChatKey(chatStream);
        loginMapper.setStreamKey(selStreamVo);
        //판매자 등록
        loginMapper.insertSellerInfo(selVo);
        
        //유저 권한 수정
        selVo.setSelRole(UserRole.EMPLOYEE.getKey());
        return loginMapper.updateUserRole(selVo);
    }
}
