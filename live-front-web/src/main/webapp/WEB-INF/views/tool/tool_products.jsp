<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="admin_wrap">
    <div class="admin_content">
        <div class="admin_menulist">
            <div class="admin_tablist">
                <a href="/tool/live"      class="main_tab" aria-selected="false">라이브 관리</a>
                <a href="/tool/products"  class="main_tab" aria-selected="true">상품 관리</a>
                <a href="/tool/pdpost" class="main_tab" aria-selected="false">판매글 관리</a>
                <a href="/tool/sale"     class="main_tab" aria-selected="false">판매 내역 관리</a>
                <a href="/tool/setting"   class="main_tab" aria-selected="false">설정</a>
            </div>
        </div>
        <div class="admin_tool_wrap">
            <div class="admin_tool_area">
                <div class="admin_tool_inner">
		            <div class="admin_tool_top">
		              <div class="chk_deletePd chk_del">선택 삭제</div>
		                <a href ="/tool/products/create" class="btn_pd_create_move btn_move">제품 등록</a>
		            </div>
		            <div class="admin_tool_content">
		                <div class="admin_list_body">
		                    <div class="admin_list_header">
		                        <div class="admin_list_header_viewport">
		                            <div class="pd_h_check">
		                              <input type="checkbox" class="isChek">
		                            </div>
		                            <div class="pd_cell_2">제품번호</div>
		                            <div class="pd_cell_3">제품명</div>
		                            <div class="pd_cell_4">전시상태</div>
		                            <div class="pd_cell_5">판매가격(1개)</div>
		                            <div class="pd_cell_6">재고수량</div>
		                            <div class="pd_cell_7">등록날짜</div>
		                        </div>
		                    </div>
		                    <div class="pd_content">
		                      <c:forEach var="pds" items="${products}">
                                <div class="pd_content_area">
		                            <div class="pd_h_check">
		                                <div><input type="checkbox" class="iChek" id="pdId" value="${pds.pdId}"></div>
		                            </div>
		                            <div class="pd_cell_2">
		                                <a href="/tool/products/create?pdId=${pds.pdId}" class="text_blue">${pds.pdId}</a>
		                            </div>
		                            <div class="pd_cell_3">
		                                ${pds.pdName}
		                            </div>
		                            <div class="pd_cell_4">
		                              <c:choose>
		                                  <c:when test="${pds.pdStatus eq 'off'}">
		                                      미전시
		                                  </c:when>
		                                  <c:otherwise>
		                                      전시중
		                                  </c:otherwise>
		                              </c:choose>
		                            </div>
		                            <div class="pd_cell_5">
		                                <div><fmt:formatNumber value="${pds.pdPrice}" pattern="#,###" /></div>
		                            </div>
		                            <div class="pd_cell_6">
		                                <div>${pds.pdStrock}</div>
		                            </div>
		                            <div class="pd_cell_7">
                                        <div>${pds.pdDate}</div>
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