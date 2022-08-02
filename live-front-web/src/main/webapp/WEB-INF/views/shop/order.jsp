<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="user" property="principal"/>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<div id="order_wrap">
    <div class="order_container">
        <h1 class="order_container_title">주문/결제</h1>
        <section class="order_pd_list">
            <div class="order_pd_list_header">
                <div class="section_order_title">주문상품</div>
            </div>
            <div class="order_pd_list_content">
                <ul class="order_group">
                  <c:forEach var="sel" items="${cartList}">
                    <c:set var = "addDelivery" value = "0"  />
                    <li class="order_group_list">
                        <div class="order_group_title">
                            <span class="group_sellerName">${sel.selName}</span>
                        </div>
                        <ul class="order_pd_group">
                          <c:forEach var="pdSal" items="${cartPdSalList}">
                            <c:if test="${sel.selId eq pdSal.selId}">
                            <c:set var = "delivery" value = "${pdSal.psDelivery}"  />
                             <c:forEach var="pd" items="${cartPdList}">
                               <c:if test="${pdSal.psIndex eq pd.psIndex}">
                                <li class="order_pd_group_item">
                                    <div class="product_item_img">
                                        <img src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${pdSal.uploadPath}/${pdSal.fileId}">
                                    </div>
                                    <div class="product_item_text">
                                        <div class="ps_title">${pdSal.psTitle}</div>
                                        <div class="ps_pd_option">${pd.pdName}</div>
                                        <div class="ps_option">
                                          <span class="price">${pd.pdCountPrice}</span>
                                          <span class="count">${pd.pdCount} 개</span>
                                        </div>
                                    </div>
                                </li>
                              </c:if>
                             </c:forEach>
                              <c:set var = "addDelivery" value = "${addDelivery+delivery}"  />
                           </c:if>
                          </c:forEach>
                          <div class="order_group_title">
                            <span class="group_delivery">
                                <span class="group_delivery_tit">배송비</span>
                                 <span class="group_delivery_val">
                                    <c:choose>
	                                    <c:when test="${selDelivery eq 0}">
	                                        무료
	                                    </c:when>
	                                    <c:otherwise>
	                                        <span class="price_num">
	                                             <fmt:formatNumber value="${addDelivery}" pattern="#,###" />
	                                        </span>원
	                                    </c:otherwise>
	                                </c:choose>
	                             </span>
                            </span>
                          </div>
                        </ul>
                    </li>
                  </c:forEach>
                </ul>
            </div>
        </section>
        <section class="order_address">
            <div class="order_address_header">
                <div class="section_order_title">배송지</div>
                <div class="order_pop_center"></div>
                <button type="button" class="order_address_change">변경</button>
            </div>
            <div class="order_address_content">
                <div class="addr_content">
                <c:forEach var="addr" items="${addr}">
                    <c:if test="${addr.addrYn eq 'Y'}">
                        <div class="addr_fir ${addr.addrIndex}">
                            <span class="addr_name">${addr.addrName}</span>
                            <span class="addr_recipient">${addr.addrRecipient}</span>
                            <span class="addr_marker">기본배송지</span>
                        </div>
                        <div class="addr_post">
                            ${addr.addrPostcode}
                        </div>
                        <div class="addr_addr">
                            ${addr.addrRoadaddr}&nbsp;${addr.addrDetailaddr}
                        </div>
                        <div class="addr_phone">
                            ${addr.addrContact}
                        </div>
                    </c:if>
                 </c:forEach>   
                </div>
            </div>
        </section>
        <section class="order_payment">
            <div class="order_payment_header">
                <div class="section_order_title">결제수단</div>
            </div>
            <div class="order_payment_content">
                <div class="order_payment_area">
                    <div class="order_payment_group">
                        <button type="button" class="btn_payment_card btn_method_payment"> 
                            <img class="payment_img" src="/img/icon/img_card.png">
                            <div class="payment_btn_text card">카드</div> 
                        </button>
                        <button type="button" class="btn_payment_kakao btn_method_payment">
                            <img class="payment_img" src="/img/icon/img_kakaopay.webp">
                            <div class="payment_btn_text kakaopay">카카오페이</div> 
                        </button>
                        <button type="button" class="btn_payment_naver btn_method_payment">
                            <img class="payment_img" src="/img/icon/img_toss.webp">
                            <div class="payment_btn_text tosspay">토스페이</div> 
                        </button>
                    </div>
                </div>
            </div>
        </section>
        <section class="order_total_pay">
            <div class="order_payment_header">
                <div class="section_order_title">결제금액</div>
            </div>
            <div class="order_total_price_wrap">
                <div class="order_list_price">
                    <div class="order_sub_area">
                        <div class="order_sub_price_area sub_inner">
                            <span class="order_total_title">총 상품금액</span>
                            <span class="order_total_price totalPdPrice">
                                &nbsp;원</span>
                        </div>
                        <div class="order_sub_delivery_area sub_inner">
                            <span class="order_total_title">총 배송비</span>
                            <span class="order_total_price totalDelivery">&nbsp;원</span>
                        </div>
                    </div>
                    <div class="order_total_area">
                        <div class="order_total_price_area sub_inner">
                            <span class="order_total_title">최종 결제금액</span>
                            <span class="order_total_price totalPrice">
                                &nbsp;원</span> 
                        </div>
                    </div>
                </div>
                <a href="javascript:0" class="btn_payment">결제하기</a>
            </div>
        </section>
    </div>
</div>
<div class="order_addr_pop">
    <div class="order_addr_pop_wrap">
        <div class="order_addr_pop_open">
            <div class="order_addr_pop_inner">
                <div class="order_addr_pop_container">
                    <header class="order_addr_pop_header">
                        <div class="order_addr_pop_exit"></div>
                        <div class="order_addr_pop_title">배송지 선택</div>
                    </header>
                    <div class="order_addr_pop_content">
                        <div class="order_addr_list_area">
                            <ul>
                              <c:forEach var="addr" items="${addr}">
                                 <li class="order_addr_list">
                                    <div class="addr_name_area">
                                        <span class="addr_name">${addr.addrName}</span>
                                        <span class="addr_recipient"> ${addr.addrRecipient}</span>
                                        <c:if test="${addr.addrYn eq 'Y' }">
                                            <span class="addr_marker"> 기본배송지</span>
                                        </c:if>
                                    </div>
                                    <div class="addr_post">${addr.addrPostcode}</div>
                                    <div class="addr_addr">${addr.addrRoadaddr}&nbsp;${addr.addrDetailaddr}</div>
                                    <div class="addr_phone">${addr.addrContact}</div>
                                    <div class="addr_button_area">
                                        <button class="btn_addr_check ${addr.addrIndex}">선택</button>
                                    </div>
                                 </li>
                               </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript"> 
var email = '${user.userId}';
var orderName = '${user.userUname}';
var tel = '${user.userPhone}';
var fromCart = '${fromCart}';
var psList = [];
var pdList = [];
var productList = [];  //상품리스트
var deliverys = [];     //배송리스트
var productPrices = [];

var delivery = 0;
var totalPdPrice = 0;
var totalDelivery =0;
var totalPrice= 0;
var paymentCode = 0;

$(document).ready(function() {
   
     <c:forEach var="sel" items="${cartList}" >
	     <c:forEach var="pdSal" items="${cartPdSalList}" varStatus='status'>
	        <c:if test="${sel.selId eq pdSal.selId}">           
	            psList.push({'selId':'${sel.selId}','psIndex':'${pdSal.psIndex}','psDeliveryOpt':'${pdSal.psDeliveryOpt}','psDelivery':'${pdSal.psDelivery}'})
	             deliverys[${status.index}] = '${pdSal.psDelivery}';
	           <c:forEach var="pd" items="${cartPdList}">
	            <c:if test="${pdSal.psIndex eq pd.psIndex}">
		            var pdId = '${pd.pdId}';
		            pdId = pdId.substring(3);
		            pdList.push({'orderPdId':pdId,'psIndex':'${pdSal.psIndex}','pdId':'${pd.pdId}','pdCount':'${pd.pdCount}','pdCountPrice':'${pd.pdCountPrice}'})
	            </c:if>
	           </c:forEach>  
	        </c:if>
	     </c:forEach>   
	 </c:forEach>  
	    //배송비 더해서 출력
	    for(var i = 0; i<deliverys.length; i++){
	        //$('.group_delivery_val').eq(i).text(_format.numberFormatComma(deliverys[i])+'원');
            totalDelivery += parseInt(deliverys[i]);
	    } 
	    $('.order_sub_delivery_area').find('.order_total_price').text(_format.numberFormatComma(totalDelivery)+' 원');
	    //상품가격 출력 및 합계 출력
	    var pdPriceClass = $('.ps_option').find('.price');
	    for(var i = 0; i<pdPriceClass.length; i++){
	        pdPrice = $(pdPriceClass[i]).text();
	        totalPdPrice += parseInt(pdPrice);
	        $(pdPriceClass[i]).text(_format.numberFormatComma(pdPrice)+' 원')
	    }
	    $('.totalPdPrice').text(_format.numberFormatComma(totalPdPrice)+' 원');
	    totalPrice = totalPdPrice+totalDelivery;
	    $('.totalPrice').text(_format.numberFormatComma(totalPrice)+' 원');

});

</script>