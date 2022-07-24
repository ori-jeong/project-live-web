<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="cart_wrap">
 <c:choose>
  <c:when test = "${!empty cartList}">
    <div class="cart_container">
        <div class="cart_inner">
            <div class="cart_content_wrap">
	            <div class="cart_content">
	               <div class="cart_header_wrap">
		               <div class="cart_header">
			               <span class="cart_header_left">
                                <span class="cart_h_check">
                                    <input type="checkbox" class="cart_h_check_input" checked>
                                </span>
                                <span class="cart_h_label">모두 선택
                                
                                </span>
	                       </span>
	                   </div>
	                   <ul class="cart_content_group_list">
	                       <c:forEach var="cart" items="${cartList}">
	                       <li class="cart_content_group_item ${cart.selId}">
	                           <article class="cart_group">
	                               <h1 class="cart_group_header">${cart.selName}</h1>
	                               <ul class="cart_group_item_list">
	                                  <c:forEach var="sale" items="${cartPdSalList}" varStatus="status">
	                                   <c:if test="${sale.selId eq cart.selId}">
	                                    <c:set var = "delivery" value = "${sale.psDelivery}"  />
	                                    <li class="cart_group_item">
	                                       <article class="cart_delivery_group">
	                                           <ul class="cart_delivery_group_pd_list">
	                                               <li class="cart_delivery_group_pd_item ${sale.psIndex}">
	                                                   <article class="cart_product">
	                                                       <div class="product_select">
			                                                   <input type="checkbox" class="selBdCheck" checked>
			                                               </div>
			                                               <a href ="/live/item?pd=${sale.psIndex}" class="product_item_href">
			                                                   <div class="product_item_img">
			                                                     <img src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${sale.uploadPath}/${sale.fileId}">
			                                                   </div>
			                                                   <div class="product_item_content">
			                                                       <h1 class="product_item_title">${sale.psTitle}</h1>
			                                                   </div>
			                                               </a>
			                                               <button type="button" class="cart_product_delete btn_del"></button>
			                                               <ul class="cart_product_list">
			                                                 <c:forEach var="product" items="${cartPdList}">
                                                              <c:if test="${sale.psIndex eq product.psIndex}">
			                                                   <li class="cart_product_list_item  ${product.pdId}">
			                                                       <article class="selling_option_item ${product.pdId}">
			                                                           <h2 class="selling_option_item_name">${product.pdName}</h2>
			                                                           <button type="button" class="selling_option_item_delete btn_del"></button>
			                                                           <div class="selling_option_item_controls">
			                                                               <div class="selling_option_item_quantity ${product.pdId}">
			                                                                   <button type="button" class="count_btn count_minus"></button>
			                                                                   <input type="number" maxlength="5" class="item_count" value="${product.pdCount}">
			                                                                   <button type="button" class="count_btn count_plus"></button>
			                                                               </div>
			                                                               <div class="selling_option_item_price_area">
			                                                                 <c:set var ="pd_price" value="${product.pdPrice * product.pdCount}" />
			                                                                 <span class="selling_option_item_price">
			                                                                     <fmt:formatNumber value="${pd_price}" pattern="#,###" />
			                                                                 </span>
			                                                                  원
			                                                               </div>
			                                                           </div>
			                                                       </article>
			                                                   </li>
			                                                  </c:if>
			                                                 </c:forEach>
			                                               </ul>
			                                               <div class="cart_product_footer">
			                                                   <span class="cart_product_footer_left">
			                                                       상품금액
			                                                   </span>
			                                                   <span class="cart_product_footer_right">
			                                                     <c:set var = "item_total" value = "0" /> 
			                                                     <c:forEach var="itemP" items="${cartPdList}" >
			                                                         <c:if test="${sale.psIndex eq itemP.psIndex}">
			                                                             <c:set var= "item_total" value="${item_total+(itemP.pdPrice * itemP.pdCount)}"/>
			                                                         </c:if>
			                                                     </c:forEach>
			                                                     <span class="cart_product_footer_price">
			                                                         <fmt:formatNumber value="${item_total}" pattern="#,###" />
			                                                     </span>원
			                                                   </span>
			                                               </div>
	                                                   </article>
	                                               </li>
	                                           </ul>
	                                       </article>
	                                    </li>
	                                    <c:set var = "selDelivery" value = "${selDelivery+delivery}"  />
	                                    </c:if>
	                                  </c:forEach>
	                               </ul>
	                               <div class="cart_group_footer">
	                                   <div class="cart_delivery_area">
                                           <span class="title">배송비</span>
                                           <span class="price">
                                                <c:choose>
                                                   <c:when test="${selDelivery eq 0}">
                                                       무료
                                                   </c:when>
                                                   <c:otherwise>
                                                       <span class="price_num">
		                                                    <fmt:formatNumber value="${selDelivery}" pattern="#,###" />
		                                               </span>원
                                                   </c:otherwise>
                                                </c:choose>
                                           </span>
                                       </div>
	                               </div>
	                           </article>
	                       </li>
	                       </c:forEach>
	                   </ul>
	               </div>
	            </div>       
            </div>
        </div>
    </div>
	<div class="total_price_wrap">
	    <div class="list_price">
	        <div class="sub_area">
	            <div class="sub_price_area sub_inner">
	                <span class="title">총 선택상품금액</span>
	                <span class="price"></span> &nbsp;원
	            </div>
	            <div class="sub_delivery_area sub_inner">
	                <span class="title">총 배송비</span>
	                <span class="price"></span> &nbsp;원
	            </div>
	        </div>
	        <div class="total_area">
	            <div class="total_price_area sub_inner">
	                <span class="title">총 결제금액</span>
	                <span class="price"></span> &nbsp;원
	            </div>
	        </div>
	    </div>
	    <a href="javascript:(0);" class="btn_link_buy">구매하기</a>
	</div>
   </c:when>
   <c:otherwise>
        <div class="cart_empty">
	        <img class="cart_empty_img" src="/img/icon/cart_empty_icon.png">
            <h1>장바구니에 담긴 상품이 없습니다.</h1>
            <div class="cart_empty_ms">원하는 상품을 장바구니에 담아보세요!</div>
            <a href="/" class="btn_home">쇼핑하러 가기</a>
        </div>
   </c:otherwise>
  </c:choose>    
</div>
<form:form id="payForm" method="POST" action="/order?fromCart=true" ></form:form>
<script type="text/javascript">
$(document).ready(function() {
	//enctype="multipart/form-data">
	let itemId;
	const deliverys = [];
	const products = [];
    let thisClass;
	    <c:forEach var="cart" items="${cartList}" >
	        <c:forEach var="sale" items="${cartPdSalList}" varStatus='status'>	          
	            <c:if test="${sale.selId eq cart.selId}">
	              deliverys[${status.index}] = {'id':'${sale.selId}', 'delivery':'${sale.psDelivery}'};
	            </c:if>	          
	        </c:forEach>
	    </c:forEach> 
	    <c:forEach var="pd" items="${cartPdList}" varStatus='status'>
	        products[${status.index}] = {'id':'${pd.pdId}', 'price':'${pd.pdPrice}'};
	    </c:forEach> 
	    
    totalProductPrice();
    totalDelivery();
    totalPayment();
    
    //-버튼
    $(document).on('click','.count_minus',function(){
        thisClass = $(this);
        let minus = parseInt($(this).next().val());
        var psIndex = $(this).parents('.cart_delivery_group_pd_item').attr('class').split('cart_delivery_group_pd_item').join('').trim();
        itemId = $(this).parent().attr('class').split('selling_option_item_quantity').join('').trim();
        if(minus > 1){
            //수량 수정
            minus = minus - 1;
            $(this).next().val(minus);
            //가격 수정
            getItemPrice(thisClass,itemId,minus);
            //DB 수정
            countModifyAjax(psIndex,itemId,minus);
        }
/*         if(minus == 1){
            $(this).attr("disabled",true);
        } */
    })
    //+버튼
    $(document).on('click','.count_plus',function(){
        thisClass = $(this);
        var psIndex = $(this).parents('.cart_delivery_group_pd_item').attr('class').split('cart_delivery_group_pd_item').join('').trim();
        let plus = parseInt($(this).prev().val());
        itemId = $(this).parent().attr('class').split('selling_option_item_quantity').join('').trim();
        if(plus < 100){
            //수량 수정
            plus = plus + 1;
            $(this).prev().val(plus);
            //가격 수정
            getItemPrice(thisClass,itemId,plus);
            //totalDelivery();
            //DB 수정
            countModifyAjax(psIndex,itemId,plus);
        }
/*         if(plus != 1){
            $(this).prev().prev().attr("disabled",false);
        } */
    });
    //모두 선택 체크박스
    $('.cart_h_check_input').click(function(){
        var allCheck = $(this).is(':checked');
        var itemCheck= $('.selBdCheck');
        if(allCheck){
            for(var i = 0; i<itemCheck.length; i++){
                $(itemCheck[i]).prop("checked",true);
            }
        }else{
            for(var i = 0; i<itemCheck.length; i++){
                $(itemCheck[i]).prop("checked",false);
            }
        }
        totalProductPrice();
        totalDeliveryCheck();
        totalPayment();
    });    
    //상품 체크박스 확인
    $('.selBdCheck').click(function(){
        totalProductPrice();
        totalDeliveryCheck();

    })
    //상품 게시물 기준 삭제
     $('.cart_product_delete').click(function(){
         var psIndex = $(this).parents('.cart_delivery_group_pd_item').attr('class').split('cart_delivery_group_pd_item').join('').trim();
    	 var isVal = cartDeleteAjax(psIndex);
    	 if(isVal){
  	         var sellist = $(this).parents('.cart_group_item_list').find('.cart_delivery_group_pd_item').length;
  	     
  	         if(sellist == 1){
  	             $(this).parents('.cart_content_group_item').remove(); //판매자와 판매글이 1개씩이면 전체 삭제
  	         }else{
  	             $(this).parents('.cart_delivery_group_pd_item').remove();
  	         }
  	         
  	         totalProductPrice();
  	         totalDeliveryCheck();
  	         totalPayment(); 
    	 }
    });
    //상품 기준 삭제
    $('.selling_option_item_delete').click(function(){
        var psIndex = $(this).parents('.cart_delivery_group_pd_item').attr('class').split('cart_delivery_group_pd_item').join('').trim();
        var pdId    = $(this).parent().attr('class').split('selling_option_item').join('').trim();
    	var isVal = cartPdDeleteAjax(psIndex,pdId);
    	if(isVal){
	        /*상품 삭제시 해당 상품li 부터 삭제하면(remove 사용시) dom도 삭제되어 부모 노드를 찾을 수 없음 그러니 위에서부터 찾아서 삭제하면 된다 */
	        
	    	// 상품 삭제 후에 해당 판매자의 상품이 없는 경우에 판매자 li 삭제
	    	var sellist = $(this).parents('.cart_group_item_list').find('.cart_delivery_group_pd_item').length;
	    	// 상품 삭제 후에 상품이 없는 경우에 판매글 li 삭제
	    	var pdlist = $(this).parents('.cart_delivery_group_pd_list').find('.cart_product_list_item').length;
	    	if(sellist == 1){
	            if(pdlist == 1){
	            	$(this).parents('.cart_content_group_item').remove(); //판매자와 판매글이 1개씩이면 전체 삭제
	            }else{
	                $(this).parents('.cart_product_list_item').remove();   //그게 아니면 해당 상품만 삭제
	            }
	    	}else{
	            if(pdlist == 1){
	                $(this).parents('.cart_delivery_group_pd_list').remove();
	            }else{
	                $(this).parents('.cart_product_list_item').remove();
	            } 
	    	}
	        totalProductPrice();
	        totalDeliveryCheck();
	        totalPayment();
    	}

    })
    $('.btn_link_buy').click(function(){
    	//판매글id, 주문상품id, 주문개수
    	var payForm = $('#payForm');
    	//var orderList=[];
    	var check = $('.selBdCheck');
    	for (var i = 0; i<check.length; i++){
    		if($(check[i]).is(':checked')==true){
    			var checkNum = $(check[i]).parents('.cart_product').find('.cart_product_list_item').length;
    			var psIndex = $(check[i]).parents('.cart_delivery_group_pd_item').attr('class').split('cart_delivery_group_pd_item').join('').trim();
    			
    			for(var j = 0; j<checkNum; j++){
                    var pdClass    = $("."+psIndex).find('.cart_product_list_item').eq(j).attr('class');
                    pdClass = pdClass.split('cart_product_list_item').join('').trim();
                    
                    $('#payForm').append($('<input />',{type:'hidden',name:'psIndex', value:psIndex}));
                    $('#payForm').append($('<input />',{type:'hidden',name:'pdId', value:pdClass}));
    			}

    		}
    	} 
    	
    	payForm.submit();
    })
    
    //상품의 가격 수정
    function getItemPrice(thisClass,item_id,num){
        products.map(item => {
            if(item.id === item_id){
                item_price = item.price;
            }
        });
        //해당 상품 가격 수정
        thisClass.parent().parent().children().next().children().text(_format.numberFormatComma(item_price*num));
        //상품 금액 가져와 합하기
        var priceClass = $("."+item_id).parents('.cart_delivery_group_pd_item').find('.selling_option_item_price');
        var priceSum = 0;
        for(var i =0; i<priceClass.length; i++){
            var price = _format.numberFormat($(priceClass[i]).text().trim());
            priceSum += parseInt(price)
        }
        //한 판매글의 상품 합계 출력
        $("."+item_id).parents('.cart_product_list').next('.cart_product_footer').find('.cart_product_footer_price').text(_format.numberFormatComma(priceSum));
        //총 선택 상품 금액
        totalProductPrice();
        //총 결제금액
        totalPayment();
    }
    
    function totalProductPrice(){
        //총 선택상품 금액
        var totalPdPriceClass = $('.cart_product_footer_price');
        let totalChPrice = 0;
        for(var i =0; i<totalPdPriceClass.length; i++){
            var isVal = $($('.product_select')[i]).children().is(':checked');
            if(isVal){
                var price = _format.numberFormat($(totalPdPriceClass[i]).text());
                totalChPrice += parseInt(price);
            }
        }
        $('.sub_price_area>.price').text( _format.numberFormatComma(totalChPrice));
    };
    function totalDelivery(){
        //총 배송비
        var totalPdDeliveryClass = $('.cart_delivery_area>.price>.price_num');
        let totalDelivery = 0;        
        deliverys.map(function(item){
            totalDelivery += parseInt(_format.numberFormat(item.delivery));
        })
    
        $('.sub_delivery_area>.price').text( _format.numberFormatComma(totalDelivery));   
    };
    //체크용 배송비
    function totalDeliveryCheck(){
        var deli = 0;
        var isVal = [];
        //회사의 li 안에 체크 박스의 체크 유무를 확인해서 배송비를 추가한다
        var selLi = $('.cart_content_group_item');
        for(var i = 0; i<selLi.length;i++){
            isVal =[];
            var selCheck = $(selLi[i]).find('.selBdCheck');         
            for(var j = 0; j<selCheck.length;j++){
                if($(selCheck[j]).is(':checked')){
                    isVal.push('true');
                }
            }
            if(isVal.length != 0){
                var num = $(selLi[i]).find('.cart_delivery_area>.price>.price_num').text();
                if(num !=""){
                    deli+=parseInt(_format.numberFormat(num));
                }

            }
        }       
        $('.sub_delivery_area>.price').text( _format.numberFormatComma(deli));
        totalPayment();
    };
    
    function totalPayment(){
        //총 결제 금액 구하기
        var chPrice = _format.numberFormat($('.sub_price_area>.price').text());
        var chDelivery = _format.numberFormat($('.sub_delivery_area>.price').text());
        let totalPrice = parseInt(chPrice) + parseInt(chDelivery);   
        $('.total_price_area>.price').text( _format.numberFormatComma(totalPrice)); 
    };
    
    function countModifyAjax(psIndex,pdId,pdCount){
    	console.log(psIndex+ " : "+pdId+ " : "+pdCount);
        var option = new Object();
        option.data = {'psIndex':psIndex,'pdId':pdId,'pdCount':pdCount};
        _ajax.ajaxData("/cart/updateCartCount","POST",option);
    	
    };
    function cartDeleteAjax(psIndex){
    	let isVal = false;
    	var msg = '<spring:message code="message.cart.delete" />';
    	if(confirm(msg) == true){
            var option = new Object();
            option.data = {'psIndex':psIndex}
            option.success = function(v){location.href = "/cart"} 
            _ajax.ajaxData("/cart/deleteCart","POST",option);
            isVal = true;
    	}
    	return isVal;
    };
    function cartPdDeleteAjax(psIndex,pdId){
    	let isVal = false;
        var msg = '<spring:message code="message.cart.delete" />';
        if(confirm(msg) == true){
	        var option = new Object();
	        option.data = {'psIndex':psIndex,'pdId':pdId}
	        option.success = function(v){location.href = "/cart"} 
	        _ajax.ajaxData("/cart/deleteCart","POST",option);
        }
        return isVal;
    };
});
</script>