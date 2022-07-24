<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <div class="product_create_wrap">
            <div class="product_create_area">
                <div class="product_create_inner">
                    <div class="_title">
	                    <c:choose>
	                        <c:when test="${empty pd}">
	                            <h1>제품 등록</h1>
	                        </c:when>
	                        <c:otherwise>
	                            <h1>제품 수정</h1>
	                        </c:otherwise>
	                    </c:choose>
                    </div>
                    <div class="product_create_content">
                        <div class="create_layout">
	                        <div class="layout_subject _asterisk">상품명</div>
	                        <input type="text" id="pdName" class="layout_input" value="${pd.pdName}" maxlength='15'>                       
                        </div>
                        <div class="create_layout">
                            <div class="layout_subject _asterisk">상품 가격</div>
                            <input type="text" id="pdPrice" class="layout_input" value="${pd.pdPrice}" maxlength='11'
                                oninput="this.value = this.value.replace(/[^0-9.,]/g, '').replace(/(\..*)\./g, '$1');">                       
                        </div>
                        <div class="create_layout">
                            <div class="layout_subject _asterisk">상품 재고량</div>
                            <input type="text" id="pdStrock" class="layout_input" maxlength='3' value="${pd.pdStrock}">                       
                        </div>
                        <div class="create_layout">
                            <div class="layout_subject _asterisk">상품 전시 상태</div>
                            <div class="layout_filter">
                                <select name="pdStatus" id="pdStatus" class="_filter">
                                    <option value="non">상태 선택</option>
                                    <c:choose>
                                        <c:when test ="${!empty pd.pdStatus}">
                                            <c:if test="${pd.pdStatus eq 'off'}">
                                                <option value="${pd.pdStatus}" selected="selected">미전시</option>
                                                <option value="on">전시중</option>
                                            </c:if>
                                            <c:if test="${pd.pdStatus eq 'on '}">
                                                <option value="off">미전시</option>
                                                <option value="${pd.pdStatus}" selected="selected">전시중</option>
                                            </c:if>
                                            
                                        </c:when>
                                        <c:otherwise>
                                            <option value="off">미전시</option>
                                            <option value="on">전시중</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>
                            <div class="memo">* 미전시 선택 시 판매등록이 불가능합니다.</div>                      
                        </div>
                        <c:choose>
                            <c:when test="${empty pd}">
                                <button class="btn_product_create btn_pp">상품 등록하기</button>
                            </c:when>
                            <c:otherwise>
                                <button class="btn_product_update btn_pp">상품 수정하기</button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="product_id" value="${pd.pdId}">
</div>