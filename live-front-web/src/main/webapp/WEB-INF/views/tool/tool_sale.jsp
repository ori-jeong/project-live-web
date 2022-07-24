<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="admin_wrap">
    <div class="admin_content">
        <div class="admin_menulist">
            <div class="admin_tablist">
                <a href="/tool/live"      class="main_tab" aria-selected="false">라이브 관리</a>
                <a href="/tool/products"  class="main_tab" aria-selected="false">상품 관리</a>
                <a href="/tool/pdpost" class="main_tab" aria-selected="false">판매글 관리</a>
                <a href="/tool/sale"     class="main_tab" aria-selected="true">판매 내역 관리</a>
                <a href="/tool/setting"   class="main_tab" aria-selected="false">설정</a>
            </div>
        </div>
        <div class="admin_tool_wrap">
            <div class="admin_tool_area">
                <div class="admin_tool_inner">
		            <div class="admin_sale_tool_top">
		              <div class="chk_cancelOrder chk_sale_opt">주문 취소</div>
		              <div class="chk_sendCheck chk_sale_opt">발주 확인</div>
		              <div class="chk_sendHold chk_sale_opt">발송 지연처리</div>
		              <div class="chk_sendPro chk_sale_opt">발송 처리</div>
		            </div>
		            <div class="admin_tool_content">
		                <div class="admin_list_body">
		                    <div class="admin_list_header sale_list_header">
		                        <div class="sale_list_header_viewport">
		                            <div class="pd_h_check">
		                              <input type="checkbox" class="isChek" value="">
		                            </div>
		                            <div class="sale_cell_2 cell_size">주문번호
		                              <!-- <div ref="eResize" class="ag-header-cell-resize" role="presentation"></div> -->
		                            </div>
		                            <div class="sale_cell_2-1">주문상품번호</div>
		                            <div class="sale_cell_3">결제상태</div>
		                            <div class="sale_cell_4">주문상태</div>
		                            <div class="sale_cell_5">판매글번호</div>
		                            <div class="sale_cell_5">상품번호</div>
		                            <div class="sale_cell_6">상품명</div>
		                            <div class="sale_cell_7">주문개수</div>
		                            <div class="sale_cell_8">주문금액</div>
		                            <div class="sale_cell_9">배송방법</div>
		                            <div class="sale_cell_10">배송비</div>
		                            <div class="sale_cell_11">구매자ID</div>
		                            <div class="sale_cell_12">수취인명</div>
		                            <div class="sale_cell_13">주문일시</div>
		                            
		                        </div>
		                    </div>
		                    <div class="sale_content">
		                      <c:forEach var="sale" items="${saleList}">
                                <div class="sale_content_area">
		                            <div class="pd_h_check">
		                                <div>
		                                <input type="checkbox" class="iChek" id="orderPdId" value="${sale.orderId}-${sale.orderPdId}-${sale.psIndex}-${sale.orderStatus}"
		                                  <c:if test='${sale.orderStatus eq 40}'>disabled</c:if>>
		                                </div>
		                            </div>
		                            <div class="sale_cell_2 cell_size">${sale.orderId}</div>
		                            <div class="sale_cell_2-1 cell_size">${sale.orderPdId}</div>
                                    <div class="sale_cell_3">
                                        <c:if test='${sale.paymentStatus eq 20}'>입금 전</c:if>
                                        <c:if test='${sale.paymentStatus eq 21}'>결제 완료</c:if>
                                        <c:if test='${sale.paymentStatus eq 22}'>결제 취소</c:if>
                                    </div>
                                    <div class="sale_cell_4">
                                        <c:if test='${sale.orderStatus eq 30}'>배송 준비중</c:if>
                                        <c:if test='${sale.orderStatus eq 31}'>배송 지연</c:if>
                                        <c:if test='${sale.orderStatus eq 32}'>배송 중</c:if>
                                        <c:if test='${sale.orderStatus eq 33}'>배송 완료</c:if>
                                        <c:if test='${sale.orderStatus eq 40}'>주문 취소</c:if>
                                        <c:if test='${sale.orderStatus eq null}'>-</c:if>
                                    </div>
                                    <div class="sale_cell_5">${sale.psIndex}</div>
                                    <div class="sale_cell_5">${sale.pdId}</div>
                                    <div class="sale_cell_6">${sale.pdName}</div>
                                    <div class="sale_cell_7">
                                        <fmt:formatNumber value="${sale.pdCount}" pattern="#,###" />
                                    </div>
                                    <div class="sale_cell_8">
                                        <fmt:formatNumber value="${sale.pdCountPrice}" pattern="#,###" />
                                    </div>
                                    <div class="sale_cell_9">
                                        <c:if test='${sale.psDeliveryOpt eq 0}'>무료배송</c:if>
                                        <c:if test='${sale.psDeliveryOpt eq 1}'>일반배송</c:if>
                                    </div>
                                    <div class="sale_cell_10">
                                        <fmt:formatNumber value="${sale.psDelivery}" pattern="#,###" />
                                    </div>
                                    <div class="sale_cell_11">${sale.userId}</div>
                                    <div class="sale_cell_12">${sale.addrRecipient}</div>
                                    <div class="sale_cell_13">${sale.orderDate}</div>
                               </div>
                              </c:forEach>
		                    </div>
		                </div>
		            </div>
                </div>
            </div>
        </div>
    </div>
</div>