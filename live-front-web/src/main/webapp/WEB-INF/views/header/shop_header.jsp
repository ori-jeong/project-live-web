<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<sec:authentication var="user" property="principal"/>
<header class="shop_header">
    <form:form action="/logout" method="POST" id="logoutForm"></form:form>
    <div class="shop_logo_wrap">
        <a href="/" class="logo_link">
            <i class="header_icon logo_icon"></i>
            <span class="blind">오늘의쇼핑</span>
        </a>
    </div>
    <div class="header_search_wrap">
        <div class="search_input_header">
            <input id="search" type="search" class="searchInput" placeholder="오늘의쇼핑 검색창" autocomplete=off
             onKeyup="if(event.keyCode==13){search()}">
        </div>
    </div>
    <nav class="header_nav">
        <c:choose>
            <c:when test="${user eq 'anonymousUser'}">
                <a href="/login" class="h_nav_pa">
                    <span class="h_btn_login">로그인</span>
                </a>
            </c:when>
            <c:otherwise>   
                <button class="h_btn_login h_btn_logout">로그아웃</button>
            </c:otherwise>
        </c:choose>
        <a href="/mypage" class="h_nav_pa">
            <i class="header_icon header_nav_icon user_icon"></i>
            <span class="blind">유저정보</span>                
        </a>
        <a href="/cart" class="h_nav_pa">
            <i class="header_icon header_nav_icon cart_icon"></i>
            <span class="blind">장바구니</span>                
        </a>
<!--         <a href="/calender" class="h_nav_pa search_a">
            <i class="header_icon header_nav_icon search_icon"></i>
            <span class="blind">검색</span>                
        </a> -->
<!--             <a href="/calender" class="h_nav_pa">
                <i class="header_icon header_nav_icon calender_icon"></i>
                <span class="blind">라이브 캘린더</span>                
            </a> -->
    </nav>
</header> 