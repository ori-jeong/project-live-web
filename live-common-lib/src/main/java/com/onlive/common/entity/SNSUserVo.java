package com.onlive.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name="user_info")
public class SNSUserVo  {
    
  //@GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동 생성; 
  //AUTO: 특정DB에 맞게 자동 선택,IDENTITY: DB의 identity 컬럼 이용, SEQUENCE: DB의 시퀀스 컬럼 이용, TABLE: 유일성이 보장된 DB TABLE 이용
    @Id
    @Column(name="user_id")
    private String userId;

    @Column(nullable = false, name="user_pw")
    private String userPw;
    
    @Column(nullable = false, name="user_uname")
    private String userUname;

    @Column(nullable = false, name="user_nickname")
    private String userNickname;
    
    @Column(nullable = false, name="user_phone")
    private String userPhone;
    
    @Column(nullable = false, name="user_platform")
    private String userPlatform;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false, name="user_role")
//    private UserRole role;
    @Column(nullable = false, name="user_role")
    private String userRole;
    
    @Builder
    public SNSUserVo(String userId, String userPw, String userUname,String userNickname, String userPhone, String userPlatform, String userRole) {
     this.userId = userId;
     this.userPw = userPw;
     this.userUname = userUname;
     this.userNickname = userNickname;
     this.userPhone = userPhone;
     this.userPlatform = userPlatform;
     this.userRole = userRole;
    }
}
