package com.onlive.front.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onlive.common.config.MessageSourceConfig;
import com.onlive.common.service.LiveService;
import com.onlive.common.service.MyPageService;
import com.onlive.common.service.ShopService;
import com.onlive.common.vo.CartVo;
import com.onlive.common.vo.CommonApiResponseVo;
import com.onlive.common.vo.OrderVo;
import com.onlive.common.vo.UserVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class shopController {
    private final MessageSourceConfig messageSource;
    private final ShopService shopService;
    private final MyPageService myPageService;
    private final LiveService liveService;
    
    @Value("${onl.video.url}")
    private String liveUrl;
    
    @RequestMapping("/shop")
    public ModelAndView getCategory(@ModelAttribute("category") String category,@ModelAttribute("location") String location ) {
        ModelAndView mv = new ModelAndView();
        if(category.equals("98")) {                                         //라이브쇼
            mv.addObject("live",liveService.liveListHome());
        }else if(category.equals("99")) {                                   //우리동네라이브
            mv.addObject("live",liveService.hometownLiveList(location));
        }else {                                                             //이외 카테고리
            mv.addObject("live",liveService.categoryLiveList(category));
        }
        mv.addObject("url",liveUrl);
        mv.setViewName("/shop/category");  
        return mv;
        
    }
    @RequestMapping("/search")
    public ModelAndView getCategory(@ModelAttribute("query") String query) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("live",liveService.searchLiveList(query));
        mv.addObject("url",liveUrl);
        mv.setViewName("/shop/search");  
        return mv;
        
    }
    
    @RequestMapping("/cart")
    public ModelAndView getCart(@AuthenticationPrincipal UserVo user) {
        ModelAndView mv = new ModelAndView();     

        List<CartVo> cartList = shopService.getCartList(user.getUserId());
        List<CartVo.CartPdSaleVo> cartPdSalList = new ArrayList<>();
        List<CartVo.CartPdVo> cartPdList = new ArrayList<>();
                
        for(int j = 0;j<cartList.size();j++) {
            cartPdSalList.addAll(cartList.get(j).getCartPdSaleVo());
            cartPdList.addAll(cartList.get(j).getCartPdVo());
        }

        mv.addObject("cartList",cartList);
        mv.addObject("cartPdSalList",cartPdSalList);
        mv.addObject("cartPdList",cartPdList);
        mv.setViewName("/shop/cart");  
        return mv;
    }
    @RequestMapping("/cart/setCart")
    @ResponseBody
    public CommonApiResponseVo<String> setCart(@AuthenticationPrincipal UserVo user,@RequestBody CartVo cart){
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        int result = 0;
        if(user !=null) {
            cart.setUserId(user.getUserId());
            result = shopService.setCartList(cart);
        }
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.cart.move"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }
    
    @RequestMapping("/cart/updateCartCount")
    @ResponseBody
    public CommonApiResponseVo<String> updateCartCount(CartVo.CartPdVo cart){
        /* 장바구니 상품 개수 수정 */
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        int result = shopService.updateCartCount(cart);
        if(result != 0) {
            response.setResult(true);
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        
        return response;
    }
    
    @RequestMapping("/cart/deleteCart")
    @ResponseBody
    public CommonApiResponseVo<String> deleteCart(CartVo.CartPdVo cart){
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        int result = shopService.deleteCart(cart);
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.delete"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        
        return response;
    }

    @RequestMapping("/order")
    public ModelAndView orderPageFromCart(HttpServletRequest request,@AuthenticationPrincipal UserVo user
            ,@ModelAttribute("fromCart") boolean fromCart){
        ModelAndView              mv            = new ModelAndView();  
        CartVo                    cart          = new CartVo();
        List<CartVo.CartPdVo>     cartPdVo      = new ArrayList<>();
        List<CartVo>              cartList      = new ArrayList<>();
        List<CartVo.CartPdSaleVo> cartPdSalList = new ArrayList<>();
        List<CartVo.CartPdVo>     cartPdList    = new ArrayList<>();
        
        if(fromCart) {
            log.debug("장바구니에서 구매창으로 이동");
            String[] psIndex = request.getParameterValues("psIndex");
            String[] pdId = request.getParameterValues("pdId");
            for (int i = 0; i < pdId.length; i++) {
                CartVo.CartPdVo cartPd = new CartVo.CartPdVo();
                cartPd.setPsIndex(psIndex[i]);
                cartPd.setPdId(pdId[i]);
                cartPdVo.add(cartPd);
            }
            cart.setUserId(user.getUserId());
            cart.setCartPdVo(cartPdVo);

            cartList = shopService.getOrderProductFromCart(cart);
                    
            for(int j = 0;j<cartList.size();j++) {
                cartPdSalList.addAll(cartList.get(j).getCartPdSaleVo());
                cartPdList.addAll(cartList.get(j).getCartPdVo());
            }
        }else {
            log.debug("바로 구매창으로 이동");
            String[] psIndex = request.getParameterValues("psIndex");
            String[] pdId = request.getParameterValues("pdId");
            String[] pdCount = request.getParameterValues("pdCount");
            for (int i = 0; i < pdId.length; i++) {
                CartVo.CartPdVo cartPd = new CartVo.CartPdVo();
                cartPd.setPdId(pdId[i]);
                cartPd.setPdCount(Integer.parseInt(pdCount[i]));
                cartPdVo.add(cartPd);
            }
            cart.setUserId(user.getUserId());
            cart.setPsIndex(psIndex[0]);
            cart.setCartPdVo(cartPdVo);

            cartList = shopService.getOrderProduct(cart);
            /*  */
            for(int j = 0;j<cartList.size();j++) {
                cartPdSalList.addAll(cartList.get(j).getCartPdSaleVo());
                cartPdList.addAll(cartList.get(j).getCartPdVo());
            }
            for(int k = 0; k<cartPdList.size();k++) {
                String getPdId1 = cartPdList.get(k).getPdId();
                Integer getOnePrice = Integer.parseInt(cartPdList.get(k).getPdPrice());
                for(int h = 0; h<cartPdVo.size();h++) {
                    String getPdId2 = cartPdVo.get(h).getPdId();
                    Integer getPdCount = cartPdVo.get(h).getPdCount();
                    if( getPdId1.equals(getPdId2) ) {
                        cartPdList.get(k).setPdCount(getPdCount);
                        cartPdList.get(k).setPdCountPrice(getOnePrice * getPdCount); 
                    }
                }
            }
        }

        mv.addObject("addr",myPageService.getAddress(user.getUserId()));
        
        mv.addObject("cartList",cartList);
        mv.addObject("cartPdSalList",cartPdSalList);
        mv.addObject("cartPdList",cartPdList);
        mv.addObject("fromCart",fromCart);
        mv.setViewName("/shop/order");  
        return mv;
    }
    
    @RequestMapping("/order/getOrderId")
    @ResponseBody
    public String getOrderId() {
        return shopService.setOrderId();
    }
    @RequestMapping("/orderPaymentProcess")
    @ResponseBody
    public CommonApiResponseVo<String> orderPaymentProcess(@RequestBody OrderVo order,@ModelAttribute("fromCart") boolean fromCart, @AuthenticationPrincipal UserVo user){
        //주문 및 결제 성공 처리
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        order.setUserId(user.getUserId());
        int result = shopService.setOrders(order,fromCart);
        if(result != 0) {
            response.setResult(true);
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        
        return response;
    }
    @RequestMapping("/paymentComplete/{orderId}/{price}")
    @ResponseBody
    public ModelAndView paymentComplete(@PathVariable("orderId") String orderId,@PathVariable("price") BigInteger price) {
        ModelAndView mv = new ModelAndView();  
        mv.addObject("orderId",orderId);
        mv.addObject("price",price);
        mv.setViewName("/shop/order_complete");
        return mv;
    }
}
