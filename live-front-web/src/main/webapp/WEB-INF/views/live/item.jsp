<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"    prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0">    --> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>오늘의쇼핑</title>
    <link rel="stylesheet" type="text/css" href="/css/video.css" >
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="/js/common.js" ></script>
    <script type="text/javascript" src="/js/item.js" ></script>
</head>
<body class="selective">
    <div class="product_wrap ${post.psIndex}">
        <div class="product_content">
	        <div class="content_top">
	            <div class="top_img_wrap">
	               <img src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${post.pdPostFileVo.getUploadPath()}/${post.pdPostFileVo.getFileId()}">
	                <div class="top_img_inner">
	                    <div class="img_area">
	                       <div class="img_ani_wrap">
	                           <div class="img_ani_area">
	                               <div class="img_location">
	                                   
	                               </div>
	                           </div>
	                       </div>
	                    </div>
	                </div>
	            </div>
	            <div class="top_text_wrap">
	               <div class="top_text_inner">
	                   <div class="top_nick">회사명</div>
	                   <h3 class="top_name">${post.psTitle}</h3>
	                   <div class="top_price">
	                       <!-- <span class="discount_rate"></span> -->
	                       <div class="price_area">
	                           <del class="pd_original_price"></del>
	                           <strong class="pd_price"><fmt:formatNumber value="${post.psPrice}" pattern="#,###" /></strong>원
	                       </div>
	                   </div>
	               </div>
	            </div>
	        </div>
	        <div class="product_text">
	           <div class="product_text_title">제품 상세 정보</div>
	           <div class="product_text_area">
	               ${post.psContent}
	           </div>
	        </div>
	        <div class="fix_wrap">
	           <div class="fix_filter_wrap" style="display:none;">
	               <button type="button" class="fix_filter_head"><h1></h1></button>
	               <div class="fix_filter_content">
	                   <div class="fix_filter_inner">
		                   <div class="filter_area">
		                       <div class="filter_box">
		                           <span class="filter_header">옵션선택(필수)</span>
		                           <div class="filter_must">
		                               <a href="javascript:void(0)" class="ft_control_btn">
		                                   구성
		                                   <svg class="ft_icon"></svg>
		                               </a>
		                               <ul class="ft_ct_ul">
 		                                  <c:forEach var="pd" items="${products}" varStatus='status'>
	                                           <li class="ft_ct_li ${pd.pdId}">
	                                               <span class="tf_ct_sel">
	                                                    <span class="tf_ct_sel_box"></span>
	                                                    ${pd.pdName}
	                                               </span>
	                                           </li>
		                                  </c:forEach>
		                               </ul>
		                           </div>
		                       </div>
		                   </div>
		                   <div class="ft_select_area blind">
		                       <ul class="ft_select_ul">

		                       </ul>
		                   </div>
		                   <div class="ft_result blind">
		                       <div class="sum_count_box">
		                           총 수량
		                           <em class="sum_count"></em>개
		                       </div>
		                       <div class="sum_amount_box">
		                           <strong>총 금액</strong>
		                           <div class="sum_amount_div">
		                               <em class="sum_item_amount"></em> 원
		                           </div>
		                       </div>
		                   </div>
                       </div>
                       <div class="parcel_info">
	                      <div class="pc_innner">
	                          <strong class="pc_name">배송방법</strong>
	                          <div class="pc_val">택배</div>
	                      </div>
	                      <div class="pc_innner" style="margin-top: 4px;">
	                          <strong class="pc_name">배송비</strong>
	                          <c:choose>
	                           <c:when test="${post.psDelivery eq 0}">
	                               <div class="pc_val">무료</div>
	                           </c:when>
	                           <c:otherwise>
	                               <div class="pc_val"><fmt:formatNumber value="${post.psDelivery}" pattern="#,###" />
	                                원 주문시 결제</div>
	                           </c:otherwise>
	                          </c:choose>
	                      </div>
                       </div>
	               </div>
                   
	           </div>
               <div class="fix_btn_inner">
 	               <div class="click_be">
	                   <button type="button" class="btn_buy fix_btn">구매하기</button>
	               </div>
                   <div class="fix_a_btn_area" style="display: none;">
                        <button type="button" class="btn_bk fix_btn">장바구니</button>
                   </div>
                   <div class="fix_a_btn_area" style="display: none;">
                        <button type="button" class="btn_buyPage btn_buy fix_btn">바로구매</button>
                   </div>           
	          </div>
	        </div>
        </div>
    </div>
    <form:form id="payForm" name="payForm" method="POST" action="/order?fromCart=false" target = "parent"></form:form>
</body>
<script>
psIndex = '${post.psIndex}';
<c:forEach var="pd" items="${products}" varStatus='status'>
    products[${status.index}] = {'id':'${pd.pdId}', 'price':'${pd.pdPrice}', 'name':'${pd.pdName}'};
</c:forEach>
</script>
</html>
