<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <!-- <meta http-equiv="X-Frame-Options" content="deny" /> -->
    <link rel="stylesheet" type="text/css" href="/css/video.css" >
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<title>Insert title here</title>
</head>
<body class="prevent_overscroll">
<div id="root">
    <div class="IframeLivePage_wrap">
        <iframe class="IframeLivePage_iframe" src="http://192.168.219.100:8080/live/video" sandbox="allow-scripts allow-popups"></iframe>
    </div>
</div>
</body>
</html>