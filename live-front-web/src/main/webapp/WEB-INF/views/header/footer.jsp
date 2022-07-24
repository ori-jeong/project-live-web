<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="user" property="principal"/>
<footer class="shop_footer">
<div id="footer_view">
    <div class="footer_btn_area">
        <c:if test="${user ne 'anonymousUser'}">
	        <c:if test="${user.userRole eq 'ROLE_MEMBER'}">
		        <button class="btn_seller_insert">판매자 등록</button>
	        </c:if>
        </c:if>
		<button class="btn_tool_move">오늘의 쇼핑 관리툴</button>
	</div>
	<div class="footer_nav">
	    <a href="javascript:void(0);">게시판 소개</a>
	    <a href="javascript:void(0);">개인정보처리방침 </a>
	    <a href="javascript:void(0);">이용약관</a>
	    <a href="javascript:void(0);">문제보고</a>
	</div>
    <div class="footer-capy minitext">
        © OnlShop Corporation
    </div>
</div>
</footer> 