<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="order_complate_wrap">
    <div class="order_cp_container">
        <img class="order_cp_img" src="/img/icon/complate_icon.png">
	    <h1 class="order_cp_title">주문이 <span>완료</span>되었습니다.</h1>
	    <div class="order_info_area">
	       <ul>
	           <li class="order_cp_li">
	                <span>주문번호</span>
                    <span>${orderId}</span>
	           </li>
	           <li class="order_cp_li">
	                <span>결제금액</span>
                    <span class="pay_price">
                        <fmt:formatNumber value="${price}" pattern="#,###" />
                    원</span>
	           </li>
	       </ul>

	    </div>
	    <div class="order_cp_btn_area">
	       <a href="/" class="btn_home">홈으로 가기</a>
	    </div>
	    
    </div>
</div>