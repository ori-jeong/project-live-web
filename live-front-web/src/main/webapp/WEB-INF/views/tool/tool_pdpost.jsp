<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="admin_wrap">
    <div class="admin_content">
        <div class="admin_menulist">
            <div class="admin_tablist">
                <a href="/tool/live"      class="main_tab" aria-selected="false">라이브 관리</a>
                <a href="/tool/products"  class="main_tab" aria-selected="false">상품 관리</a>
                <a href="/tool/pdpost" class="main_tab" aria-selected="true">판매글 관리</a>
                <a href="/tool/sale"     class="main_tab" aria-selected="false">판매 내역 관리</a>
                <a href="/tool/setting"   class="main_tab" aria-selected="false">설정</a>
            </div>
        </div>
        <div class="admin_tool_wrap">
            <div class="admin_tool_area">
                <div class="admin_tool_inner">
                    <div class="admin_tool_top">
                      <div class="chk_deletePost chk_del">선택 삭제</div>
                        <a href ="/tool/pdpost/create" class="btn_post_create_move btn_move">판매글 등록</a>
                    </div>
                    <div class="admin_tool_content">
                        <div class="admin_list_body">
                            <div class="admin_list_header">
                                <div class="admin_list_header_viewport">
                                    <div class="pd_h_check">
                                      <input type="checkbox" class="isChek">
                                    </div>
                                    <!-- <div class="post_cell_1">카테고리</div> -->
                                    <div class="post_cell_2">판매글 번호</div>
                                    <div class="post_cell_3">제목</div>
                                    <div class="post_cell_4"></div>
                                    <div class="post_cell_5">판매가</div>
                                    <div class="post_cell_6">배송방법</div>
                                    <div class="post_cell_7">배송비</div>
                                    <div class="post_cell_8">전시 상태</div>
                                </div>
                            </div>
                            <div class="pdPost_content">
                              <c:forEach var="pds" items="${pdPostList}">
                                <div class="pdPost_content_area">
                                    <div class="pd_h_check">
                                        <div><input type="checkbox" id="psId" class="iChek" value="${pds.psIndex}"></div>
                                    </div>
                                    <%-- <div class="post_cell_1">
                                        ${pds.psCateName}
                                    </div> --%>
                                    <div class="post_cell_2">
                                        <a href="/tool/pdpost/create?psIndex=${pds.psIndex}" class="text_blue">${pds.psIndex}</a>
                                    </div>
                                    <div class="post_cell_3">
                                        <img src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${pds.pdPostFileVo.getUploadPath()}/${pds.pdPostFileVo.getFileId()}" class="post_img"> 
                                    </div>
                                    <div class="post_cell_4">
                                        ${pds.psTitle}
                                    </div>
                                    <div class="post_cell_5">
                                        <fmt:formatNumber value="${pds.psPrice}" pattern="#,###" />
                                    </div>
                                    <div class="post_cell_6">
                                      <c:choose>
                                          <c:when test="${pds.psDeliveryOpt eq 0}">
                                              무료 배송
                                          </c:when>
                                          <c:otherwise>
                                              일반 배송
                                          </c:otherwise>
                                      </c:choose>
                                    </div>
                                    <div class="post_cell_7">
                                        <div><fmt:formatNumber value="${pds.psDelivery}" pattern="#,###" /></div>
                                    </div>
                                    <div class="post_cell_8">
                                        <c:choose>
                                          <c:when test="${pds.psPostStatus eq 'on '}">
                                              판매중
                                          </c:when>
                                          <c:otherwise>
                                              판매 중지
                                          </c:otherwise>
                                        </c:choose>  
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