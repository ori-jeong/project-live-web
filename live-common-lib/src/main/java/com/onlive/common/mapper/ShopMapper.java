package com.onlive.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.onlive.common.vo.CartVo;
import com.onlive.common.vo.LiveVo;
import com.onlive.common.vo.OrderVo;

@Mapper
public interface ShopMapper {
    public int searchCartList(CartVo.CartPdVo cp);
    public int setCart(Map<String,Object> listCart);
    public int setCartList(CartVo cart);
    public List<CartVo> getCartList(String userId);
    public int updateCartCount(CartVo.CartPdVo cart);
    public int deleteCart(CartVo.CartPdVo cart);
    public List<CartVo> getOrderProductFromCart(CartVo cart);
    public List<CartVo> getOrderProduct(CartVo cart);
    
    public int getNowOdereCount(String formatedNow);
    public int setOrders(OrderVo order);
    //public int setOderDetail(Map<String,Object> addrMap);
    public int setOrderAddr(Map<String,Object> addrMap);
    public int setPayment(OrderVo.PaymentVo order);
    
    public int deleteCartByOrder(OrderVo order);
    public List<LiveVo> selectLive();
}
