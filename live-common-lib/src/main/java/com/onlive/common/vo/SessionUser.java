package com.onlive.common.vo;

import java.io.Serializable;

import com.onlive.common.entity.SNSUserVo;

import lombok.Getter;

@Getter 
public class SessionUser implements Serializable{
    private static final long serialVersionUID = 1L;
    private String userId;
    private String userName;
    private String userNickname;
    private String userRole;
    
    public SessionUser(SNSUserVo user) {
        this.userId = user.getUserId();
        this.userName = user.getUserUname();
        this.userNickname = user.getUserNickname();
        this.userRole = user.getUserRole();
    }
}
