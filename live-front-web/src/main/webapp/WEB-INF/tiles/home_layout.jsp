<%@page   contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="tiles"    uri="http://tiles.apache.org/tags-tiles"%>
<%-- <%
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
	if (request.getProtocol().equals("HTTP/1.1"))
	        response.setHeader("Cache-Control", "no-cache");
%> --%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link rel="shortcut icon" href="/img/logo/logo_header_icon.png">
    <title>오늘의쇼핑</title>
    <link rel="stylesheet" type="text/css" href="/css/common.css" >
    <link rel="stylesheet" type="text/css" href="/css/shop.css" > 
    <link rel="stylesheet" type="text/css" href="/css/mypage.css" >
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script> 
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/shop.js"></script>
    <script type="text/javascript" src="/js/mypage.js"></script>
    <script type="text/javascript" src="/js/order.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c2dbd6c0b5c00df629f26d19c5981c33&libraries=services"></script>
    
</head>
<body>

    <tiles:insertAttribute name="header" flush="true" />
    <main class="container">
       <tiles:insertAttribute name="body" />
    </main>
    <tiles:insertAttribute name="footer" flush="true" />
</body>
</html>