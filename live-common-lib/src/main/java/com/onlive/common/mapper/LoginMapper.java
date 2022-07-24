package com.onlive.common.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.onlive.common.vo.SellerVo;
import com.onlive.common.vo.UserVo;

@Mapper
public interface LoginMapper {
    public UserVo getUserInfo(String userId);
    public int insertUser(UserVo user);
    public int searchId(String userId);
    public int searchPhone(String userPhone);
    public int findIdInt(UserVo user);
    public Map<String, Object> findId(UserVo user);   
    public int searchNickname(String userNickname);
    public int updateUserInfo(UserVo user);
    public int updateUserPw(String userPw);
    public String checkPw(UserVo user);
    public int resetPw(UserVo user);

    public UserVo getUserLogin(String userId);
    
    public int countBySelNameCheck(String selName);
    public int setStreamKey(SellerVo.SellerLiveVo selStreamVo);
    public int insertSellerInfo(SellerVo selVo);
    public int updateUserRole(SellerVo selVo);
}
