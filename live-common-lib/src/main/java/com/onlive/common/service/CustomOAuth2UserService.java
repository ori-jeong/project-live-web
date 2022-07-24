package com.onlive.common.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.onlive.common.entity.SNSUserVo;
import com.onlive.common.jpa.OAuthAttributes;
import com.onlive.common.jpa.PrincipalDetails;
import com.onlive.common.jpa.UserRepository;
import com.onlive.common.mapper.LoginMapper;
import com.onlive.common.vo.UserVo;
import com.onlive.common.vo.type.UserRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
    private final UserRepository repository;
    //private final HttpSession httpSession;
    //private final HttpServletRequest httpServletRequest;
    private final LoginMapper loginMapper;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        /* 계정 데이터 담기 */
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        /* 로그인 서비스 구분 :  네이버, 카카오 등*/
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        
        /*로그인 진행 시 키가 되는 필드값(PK와 같은 의미*/
        /* 구글은 기본적으로 코드 지원; 네이버, 카카오는 기본 지원 안함 */
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        
        /* OAuth2UserService 통해 가져온 OAuth2User의 attribute를 담을 클래스 */
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        
        /* sns에서 가져온 정보 UserVo에 담기 = SNS 정보를 가지고 있음*/
        UserVo user = userInfo(attributes);

        /* 유저 아이디 존재 확인 = DB 정보를 가지고 있음*/
        SNSUserVo snsUser = repository.findByUserId(attributes.getUserId()).orElse(null);
        /* DB정보가 없으면 SNS에서 가져온 정보로 회원가입하기 */
        if(snsUser==null) {
            snsUser = userSignUp(user);
            //snsUser = saveOrUpdate(user);
        }
        //SessionUser: 세션에 사용자 정보 저장하는 DTO
        //UserVo 클래스로 전송하려 했으나 에러 발생해 
        //httpSession.setAttribute("userInfo", new SessionUser(snsUser));
        /* principal 객체 안에 DB 정보를 넣어주기 위해 Map으로 다시 저장 */
        Map<String, Object> map = new HashMap<String, Object>() ;
        try {
            map = BeanUtils.describe(snsUser);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        /* NameAttributeKey 값을 key로 넣어줘야함 */
        map.put(attributes.getNameAttributeKey(), attributes.getNameAttributeKey());
        /* snsUser가 가지고 있는 유저 비밀번호,클래스 데이터 삭제 */
        map.remove("userPw");
        map.remove("class");
        /* DefaultOAuth2User의 권한을 가진 User를 load합니다. */
        new PrincipalDetails(snsUser, map);
               
        /* 유저 권한 정보 넣기 */
        //UserRole userRole =UserRole.fromRole(user.getUserRole());
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
 
        return new UserVo(
                snsUser.getUserId(),
                snsUser.getUserUname(),
                snsUser.getUserNickname(),
                snsUser.getUserPhone(),
                authorities
        );
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority(attributes.getUserRole())), //권한부여
//                map,
//                attributes.getNameAttributeKey());
    }
    
    private UserVo userInfo(OAuthAttributes attributes) {
        UserVo.SignUpVo platform = new UserVo.SignUpVo();
        platform.setUserPlatform(attributes.getUserPlatform());
        UserVo user = UserVo.builder()
                .userId(attributes.getUserId())
                .userUname(attributes.getUserUname())
                .userNickname(attributes.getUserUname())
                .userPhone(attributes.getUserPhone())
                .userPlatform(platform)
                .userRole(attributes.getUserRole())
                .build();
        return user;
    }
    
    /* SNS 계정으로 회원가입 */
    private SNSUserVo  userSignUp(UserVo user) {
        //비밀번호 만들어 DB에 넣어주기
        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String userPw = passwordEncoder.encode(user.getUserId()+uuid);
        user.setUserPw(userPw);
        user.setUserPhone("null");
        
        //유저 정보 등록
        int result = loginMapper.insertUser(user);
        //int result = repository.insertUserInfo( user.getUserId(), userPw, user.getUserUname()
        //        , user.getUserNickname(),user.getSignUpVo().getUserPlatform(),user.getUserRole());
        SNSUserVo snsUser = new SNSUserVo();
        if(result == 1) {
            snsUser = repository.findByUserId(user.getUserId()).orElse(null); 
        }
        return snsUser;
    }
//    private SNSUserVo saveOrUpdate(UserVo user) {
//        //비밀번호 만들어 DB에 넣어주기
//        String uuid = UUID.randomUUID().toString().substring(0, 6);
//        String userPw = passwordEncoder.encode(user.getUserId()+uuid);
//
//        return userRepository.save(user);
//    }

//    public OAuth2User updateUser(UserVo user) throws OAuth2AuthenticationException{
//        System.out.println("유저정보 : "+user);
//        Map<String, Object> map = new HashMap<String, Object>() ;
//        try {
//            map = BeanUtils.describe(user);
//        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        /* NameAttributeKey 값을 key로 넣어줘야함 */
//        map.put("id", "id");
//        System.out.println("getNameAttributeKey : "+getNameAttributeKey);
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority(user.getUserRole())), //권한부여
//                map,"id");
//    }
    
//    public OAuth2User updateUserInfo(UserVo user) {
//        Map<String, Object> testMap = new HashMap<String, Object>() ;
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority(attributes.getUserRole())), //권한부여
//                testMap,
//                attributes.getNameAttributeKey());
//    }
}
