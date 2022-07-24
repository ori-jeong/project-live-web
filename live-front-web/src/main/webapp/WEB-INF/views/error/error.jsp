<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>오늘의쇼핑</title>
	<link rel="stylesheet" type="text/css" href="/css/common.css" >
</head>
<body>
    <div class="error-page">
        <p class="error-txt01"><spring:message code="error.title" /></p>
        <p class="error-txt02">
            <c:choose>
                <c:when test="${code == '403'}">
                    <spring:message code="error.403" />
                </c:when>
                <c:when test="${code == '404'}">
                    <spring:message code="error.404" />
                </c:when>
                <c:otherwise>
                    <spring:message code="error.etc" />
                </c:otherwise>            
            </c:choose>
        </p>
        <div>
            <a href="/index" class="btn_home btn_point"><spring:message code="label.go.home" /></a>
        </div>
    </div>
</body>
</html>