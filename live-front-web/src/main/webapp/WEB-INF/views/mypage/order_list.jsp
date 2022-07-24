<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="orderList_wrap" class="mypage_wrap">
    <div id="orderList_area" class="mypage_area">
        <div class="mypage_title">
            <h1>주문 목록</h1>
        </div>
        <div class="orderList_content">
            <div class="orderList_group">
                <div class="orderList_menu">
                    <a href="/mypage/order_list?status=20" class="orderList_menu_list">
                        <div class="orderList_menu_list_area">
                            <div class="orderList_menu_list_title ">입금대기</div>
                            <div class="orderList_menu_list_value">
                                <c:set var="statusCount" value="0" /> 
                                <c:forEach var='status' items="${status}">
                                  <c:if test="${status.orderStatus eq null && status.paymentStatus eq '20'}">
                                      <c:set var="statusCount" value="${statusCount+1}" /> 
                                      ${status.paymentStatusCount}
                                  </c:if>
                                </c:forEach>

                                <c:if test="${statusCount eq 0}">
                                    ${statusCount}
                                </c:if>
                            </div>
                        </div>
                    </a>
                    <a href="/mypage/order_list?status=21" class="orderList_menu_list">
                        <div class="orderList_menu_list_area">
                            <div class="orderList_menu_list_title pay1">결제완료</div>
                            <div class="orderList_menu_list_value">
                                <c:set var="statusCount" value="0" /> 
                                <c:forEach var='status' items="${status}">
                                  <c:if test="${status.orderStatus eq null && status.paymentStatus eq '21'}">
                                     <c:set var="statusCount" value="${statusCount+2}" /> 
                                      ${status.paymentStatusCount}
                                  </c:if>
                                </c:forEach>
                                
                                <c:if test="${statusCount eq 0}">
                                    ${statusCount}
                                </c:if>
                            </div>
                        </div>
                    </a>
                    <a href="/mypage/order_list?status=30" class="orderList_menu_list">
                        <div class="orderList_menu_list_area">
                            <div class="orderList_menu_list_title">배송준비</div>
                            <div class="orderList_menu_list_value">
                                <c:set var="statusCount" value="0" /> 
                                <c:forEach var='status' items="${status}">
                                  <c:if test="${status.paymentStatus eq '21' && status.orderStatus eq '30'}">
                                     <c:set var="statusCount" value="1" /> 
                                      ${status.paymentStatusCount}
                                  </c:if>
                                </c:forEach>
                                <c:if test="${statusCount eq 0}">
                                    0
                                </c:if>
                            </div>
                        </div>
                    </a>
                    <a href="/mypage/order_list?status=32" class="orderList_menu_list">
                        <div class="orderList_menu_list_area">
                            <div class="orderList_menu_list_title">배송중</div>
                            <div class="orderList_menu_list_value">
                                <c:set var="statusCount" value="0" /> 
                                <c:forEach var='status' items="${status}">
                                  <c:if test="${status.paymentStatus eq '21' && status.orderStatus eq '32'}">
                                     <c:set var="statusCount" value="1" /> 
                                      ${status.paymentStatusCount}
                                  </c:if>
                                </c:forEach>
                                <c:if test="${statusCount eq 0}">
                                    ${statusCount}
                                </c:if>
                            </div>
                        </div>
                    </a>
                    <a href="/mypage/order_list?status=33" class="orderList_menu_list">
                        <div class="orderList_menu_list_area">
                            <div class="orderList_menu_list_title">배송완료</div>
                            <div class="orderList_menu_list_value">
                                <c:set var="statusCount" value="0" /> 
                                <c:forEach var='status' items="${status}">
                                  <c:if test="${status.paymentStatus eq '21' && status.orderStatus eq '33'}">
                                     <c:set var="statusCount" value="1" /> 
                                      ${status.paymentStatusCount}
                                  </c:if>
                                </c:forEach>
                                <c:if test="${statusCount eq 0}">
                                    ${statusCount}
                                </c:if>
                            </div>
                        </div>
                    </a>
                    <a href="/mypage/order_list?status=50" class="orderList_menu_list">
                        <div class="orderList_menu_list_area">
                            <div class="orderList_menu_list_title">구매확정</div>
                            <div class="orderList_menu_list_value">
                                <c:set var="statusCount" value="0" /> 
                                <c:forEach var='status' items="${status}">
                                  <c:if test="${status.paymentStatus eq '21' && status.orderStatus eq '50'}">
                                     <c:set var="statusCount" value="1" /> 
                                      ${status.paymentStatusCount}
                                  </c:if>
                                </c:forEach>
                                <c:if test="${statusCount eq 0}">
                                    ${statusCount}
                                </c:if>
                            </div>
                        </div>
                    </a>
                </div>
                <div>
                    <div class="orderList_list_wrap">
                        <div class="orderList_list_area">
	                        <button class="btn_select_day 
	                            <c:choose>
	                                <c:when test="${param.before == 1 || empty param.before}">daySel</c:when>
	                                <c:otherwise>dayNoSel</c:otherwise>
	                            </c:choose>" type="button">1개월전</button>
		                     <button class="btn_select_day 
		                        <c:choose>
		                            <c:when test="${param.before == 3 || param.before==''}">daySel</c:when>
		                            <c:otherwise>dayNoSel</c:otherwise>
		                        </c:choose>" type="button">3개월전</button>
		                     <button class="btn_select_day 
		                        <c:choose>
		                            <c:when test="${param.before == 2022 || param.before==''}">daySel</c:when>
		                            <c:otherwise>dayNoSel</c:otherwise>
		                        </c:choose>" type="button">2022</button>
		                     <button class="btn_select_day 
		                        <c:choose>
		                            <c:when test="${param.before == 2021 || param.before==''}">daySel</c:when>
		                            <c:otherwise>dayNoSel</c:otherwise>
		                        </c:choose>" type="button">2021</button>
		                     <button class="btn_select_day 
		                        <c:choose>
	                                <c:when test="${param.before == 2020 || param.before==''}">daySel</c:when>
	                                <c:otherwise>dayNoSel</c:otherwise>
	                            </c:choose>" type="button">2020</button>
                        </div>
                        <c:choose>
                        <c:when test="${!empty orders}">
                        <c:forEach var="order" items="${orders}">       
                         <ul class="orderList_card">
                           <li class="orderList_card_title">
                               <span class="order_day">${order.orderDate}</span>
                               <!-- <a class="a_order_detail" href="/orders/주문번호">상세정보</a> -->
                           </li> 
                           <c:forEach var="list" items="${order.userOrderListVo}">  
                             <c:if test="${order.orderId eq list.orderId}"> 
                              <li class="orderList_card_body">
                                <h3 class="order_status">
                                    <c:choose>
                                        <c:when test="${list.orderStatus eq null}">
                                            <c:if test="${list.paymentStatus eq 20}">입금전</c:if>
                                            <c:if test="${list.paymentStatus eq 21}">결제완료</c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${list.orderStatus eq 30}">배송준비중</c:if>
                                            <c:if test="${list.orderStatus eq 31}">배송지연</c:if>
                                            <c:if test="${list.orderStatus eq 32}">배송중</c:if>
                                            <c:if test="${list.orderStatus eq 33}">배송완료</c:if>
                                            <c:if test="${list.orderStatus eq 40}">취소요청</c:if>
                                            <c:if test="${list.orderStatus eq 41}">취소처리중</c:if>
                                            <c:if test="${list.orderStatus eq 42}">취소완료</c:if>
                                            <c:if test="${list.orderStatus eq 50}">구매확정</c:if>
                                        </c:otherwise>
                                    </c:choose>
                                </h3>
                                <div class="goods_item">
                                    <div class="goods_thumb">
                                        <img src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${list.uploadPath}/${list.fileId}" width=90 height=90>
                                    </div>
                                    <div class="goods_info">
                                        <div class="goods_seller">${list.selName}</div>
                                        <div class="goods_name">${list.psTitle}</div>
                                        <div class="goods_order_info">
                                            <span class="goods_price"> <fmt:formatNumber value="${list.pdCountPrice}" pattern="#,###" />원</span>
                                             <fmt:formatNumber value="${list.pdCount}" pattern="#,###" />개
                                        </div>
                                    </div>
                                    <div>
                                      <c:choose>
                                        <c:when test="${list.orderStatus eq null}">
                                            <c:if test="${list.paymentStatus eq 21}">
                                                <button class="btn_cancel ${list.orderId}-${list.orderPdId}-${list.psIndex}">주문 취소</button>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${list.orderStatus eq 30 || list.orderStatus eq 31}">
                                                <button class="btn_cancel ${list.orderId}-${list.orderPdId}-${list.psIndex}">주문 취소</button>
                                                <button class="btn_complete ${list.orderPdId}">구매확정</button>
                                            </c:if>
                                            <c:if test="${list.orderStatus eq 32 || list.orderStatus eq 33}">
                                                <button class="btn_complete ${list.orderPdId}">구매확정</button>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                               </li>
                             </c:if>
                           </c:forEach>
                         </ul>         
                        </c:forEach>
                       </c:when>
                       <c:otherwise>
                            <div> 구매 이력이 없습니다.</div>
                       </c:otherwise>
                      </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
