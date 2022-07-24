package com.onlive.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.onlive.common.vo.AddressVo;
import com.onlive.common.vo.OrderVo;
import com.onlive.common.vo.SalesVo;
import com.onlive.common.vo.UserVo;

@Mapper
public interface MyPageMapper {
    //public UserVo getUserDetails(String userId);
    public List<OrderVo.OrderStatusCountVo> getUserOrderStatusCount(OrderVo order);
    public List<OrderVo> getUserOrderInfoList(OrderVo order);
    public OrderVo.CancelOrderVo getCancelTotalPrice(SalesVo salesVo);
    public void insertCancelOrderUser(OrderVo.CancelOrderVo cancelOrderVo);
    public int cancelOrderProcess(SalesVo salesVo);
    public int confirmationProcess(OrderVo order);
    public List<UserVo> getAddress(String userId);
    public AddressVo getSelectAddress(AddressVo addrVo);
    public int deleteByAddress(AddressVo addrVo);
    public void updateAddrNo(AddressVo addrVo);
    public int setAddress(AddressVo addrVo);
    public int nickCheck(String nick);
    public int setUserDetails(UserVo user);
    public String comparePw(UserVo user);
    public int setUserPw(UserVo user);
    public AddressVo getBasicAddress(String userId);
}
