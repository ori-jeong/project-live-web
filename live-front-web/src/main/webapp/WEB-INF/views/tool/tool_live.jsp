<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="admin_wrap">
    <div class="admin_content">
        <div class="admin_menulist">
            <div class="admin_tablist">
                <a href="/tool/live"      class="main_tab" aria-selected="true">라이브 관리</a>
                <a href="/tool/products"  class="main_tab" aria-selected="false">상품 관리</a>
                <a href="/tool/pdpost" class="main_tab" aria-selected="false">판매글 관리</a>
                <a href="/tool/sale"     class="main_tab" aria-selected="false">판매 내역 관리</a>
                <a href="/tool/setting"   class="main_tab" aria-selected="false">설정</a>
            </div>
        </div>
        <div class="admin_tool_wrap">
            <div class="admin_tool_area">
                <div class="admin_tool_inner">
                    <div class="admin_tool_top">
                        <a href ="/tool/live/create" class="btn_pd_create_move btn_move">라이브 등록</a>
                    </div>
                    <div class="admin_tool_content">
                        <div class="admin_list_body">
                            <div class="admin_list_header">
                                <div class="admin_list_header_viewport">
  <!--                                   <div class="pd_h_check">
                                      <input type="checkbox" class="isChek">
                                    </div> -->
                                    <div class="live_cell_2">상태</div>
                                    <div class="live_cell_3">라이브아이디</div>
                                    <div class="live_cell_4">라이브</div>
                                    <div class="live_cell_5"></div>
                                    <div class="live_cell_6"></div>
                                </div>
                            </div>
                            <div class="live_content">
                              <c:forEach var="live" items="${liveList}">
                                <div class="live_content_area">
                                    <%-- <div class="pd_h_check">
                                        <div><input type="checkbox" class="iChek" value="${pds.pdId}"></div>
                                    </div> --%>
                                    <div class="live_cell_2">
                                        <c:if test="${live.liveStatus eq '0'}">대기</c:if>
                                        <c:if test="${live.liveStatus eq '1'}">라이브중</c:if>
                                        <c:if test="${live.liveStatus eq '2'}">종료</c:if>
                                        <c:if test="${live.liveStatus eq '3'}">라이브불가</c:if>
<%-- 	                                    <jsp:useBean id="now" class="java.util.Date" />
	                                    <fmt:formatDate var="todayFormat" value="${now}" pattern="yyyy.MM.dd HH:mm" />
	                                    <c:set var="liveStartDate" value="${live.liveStartDay} ${live.liveStartTime}" />
	                                    <c:set var="liveEndDate" value="${live.liveStartDay} ${live.liveEndTime}" />
                                        <c:if test = "${todayFormat < liveStartDate}">대기</c:if>
                                        <c:if test = "${todayFormat >= liveStartDate && todayFormat <= liveEndDate}">라이브중</c:if>
                                        <c:if test = "${todayFormat > liveEndDate}">종료</c:if> --%>
                                    </div>
                                    <div class="live_cell_3">
                                       <a href="/tool/live/create?live=${live.liveId}" class="text_blue">${live.liveId}</a>
                                    </div>
                                    <div class="live_cell_4">
                                        <div class="live_cell_4_img">
                                            <img src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${live.uploadPath}/${live.fileId}" class="title_img_mini">
                                        </div>
                                    </div>
                                    <div class="live_cell_5">
                                            <div class="live_cell_5_1">${live.liveTitle}</div>
                                            <div class="live_cell_5_2">${live.liveStartDay} ${live.liveStartTime}</div>
                                            <div class="live_cell_5_3">${live.liveView}</div>
                                    </div>
                                    <div class="live_cell_6">
                                        <c:if test="${live.liveStatus eq '1'}"><a href="/tool/live/chat?live=${live.liveId}" class="toolChat">채팅</a></c:if>
                                        <button class="btn_live_delete ${live.liveId}">삭제</button>
                                    </div>
  
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