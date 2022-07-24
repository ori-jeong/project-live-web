package com.onlive.common.jpa;

import java.util.Map;

import com.onlive.common.entity.SNSUserVo;
import com.onlive.common.vo.type.UserRole;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String userId;
    private String userUname;
    private String userNickname;
    private String userPhone;
    private String userPlatform;
    private String userRole;
    
    @Builder
    public OAuthAttributes(String userId, String userUname, String userNickname,String userPhone, String userPlatform, String userRole, Map<String, Object> attributes, String nameAttributeKey) {
        this.userId = userId;
        this.userUname = userUname;
        this.userNickname=userNickname;
        this.userPhone = userPhone;
        this.userPlatform = userPlatform;
        this.userRole = userRole;
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
    }
    /* OAuth2User에서 반환하는 사용자 정보가 Map이라 값을 따로 변환해야 함 */
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String,Object> attributes) {
        
        if("kakao".equals(registrationId)){
            return ofKakao("id", attributes);
        }
        return ofNaver("id", attributes);
        
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> naverAccount = (Map<String, Object>)attributes.get("response");
        String userId = (String) naverAccount.get("email");
        int idx = userId.indexOf("@");
        return OAuthAttributes.builder()
                .userId( userId.substring(0,idx))
                .userUname((String) naverAccount.get("nickname"))
                .userNickname((String) naverAccount.get("nickname"))
                .userPlatform("naver")
                .userRole(UserRole.MEMBER.getKey())
                .attributes(naverAccount)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        // 받아 온 kakao 정보 안에 kakao_account 담기
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        // kakao_account안에 profile이라는 JSON객체 정보를 담는다
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");
        return OAuthAttributes.builder()
                .userId((attributes.get("id"))+"@k")
                .userUname((String) kakaoProfile.get("nickname"))
                .userNickname((String) kakaoProfile.get("nickname"))
                .userPlatform("kakao")
                .userRole(UserRole.MEMBER.getKey())
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    
    /* SNS로 가입시 생성 */
    public static OAuthAttributes snsUserInfo(SNSUserVo snsUser) {
        return OAuthAttributes.builder()
                .userId(snsUser.getUserId())
                .userUname(snsUser.getUserUname())
                .userNickname(snsUser.getUserNickname())
                .userRole(snsUser.getUserRole())
                .build();
    }
}
