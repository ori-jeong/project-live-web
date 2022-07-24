<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<sec:authentication var="user" property="principal"/>
<header class="admin_header">
    <form:form action="/logout" method="POST" id="logoutForm"></form:form>
    <div class="admin_logo_wrap">
        <a href="/" class="logo_link">
            <i class="header_icon logo_icon"></i>
            <span class="blind">오늘의쇼핑</span>
        </a>
        <a href="/tool/live" class="logo_link_admin">
            <h1 class="tool">관리</h1>
        </a>
    </div>
    <nav class="header_nav">
        <c:choose>
            <c:when test="${user eq 'anonymousUser'}">
                <a href="/login" class="h_nav_pa">
                    <span class="h_btn_login">로그인</span>
                </a>
            </c:when>
            <c:otherwise>
                <span class="selName_area">${user.selName}</span>
                <button class="h_btn_login h_btn_logout">로그아웃</button>
            </c:otherwise>
        </c:choose>
    </nav>
</header> 