<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<sec:authentication var="user" property="principal"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="/img/logo/logo_header_icon.png">
    <title>오늘의쇼핑</title>
    <link rel="stylesheet" type="text/css" href="/css/video.css" >
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="/js/live_common.js" ></script>  
</head>
<body>
    <div class="videoLayout_wrap videoLayout_pc">
        <div class="videoLayout_inner">
            <div style="height: 100%;">
                <div class="eg-flick-viewport">
                    <div class="eg-flick-camera">
                    <div class="FlickingContainer_content eg-flick-panel">
                        <div class="FlickingContainer_inner">
                            <header class="LiveHeader_wrap">
                                <h1 class="LiveHeader_logo">
							        <a href="/" class="logo_link">
							            <i class="header_icon logo_icon"></i>
							            <span class="blind">오늘의쇼핑</span>
							        </a>
                                </h1>
                                <div class="ToolBox_wrap">
                                    <div class="SoundButton_wrap"> 
                                        <button type="button" class="SoundButton">
                                            <i class="SoundButton_icon header_icon header_icons"></i>
                                            <span class="blind">음소거</span>
                                        </button>
                                    </div>
                                    <div>
                                       <button type="button" class="CloseButton">
                                            <i class="CloseButton_icon header_icon header_icons"></i>
                                            <span class="blind">닫기</span>
                                        </button>
                                    </div>
                                </div>
                                <div class="LiveHeader_title">
                                    <div class="LiveHeader_img_area" >
                                        <img class="LiveHeader_image" width="38" height="38" src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${replay.liveSellerVo.getSelUploadPath()}/${replay.liveSellerVo.getSelFileId()}">
                                        <span class="LiveBadge_live blind">LIVE</span>
                                    </div>           
                                    <div class="LiveHeader_text_area">
                                        <h2 class="LiveHeader_text">${replay.liveTitle}</h2>
                                        <div class="LiveHeader_item_box">
                                            <span class="LiveHeader_nickname">${replay.liveSellerVo.getSelName()}</span>
                                            <span class="LiveHeader_live">
                                                <i class="LiveHeader_view_icon"></i>
                                                <span class="LiveHeader_view_count">${replay.liveView}</span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </header>
                            <div class="NoticeContent_area blind"></div>
                            <div class="LiveVideoPlayer_wrap">
                                <div class="video_container">
                                    <video id="video" autoplay muted loop controls disablepictureinpicture="true" controlslist="nodownload" width="100%" height="100%" preload="auto" 
                                        poster="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${replay.uploadPath}/${replay.fileId}"
                                        src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${replay.videoPath}${replay.videoName}">
                                    </video>  
                                </div>
                            </div>
                            <div class="Comments_wrap" id="Comments_wrap">
                                <div class="Comments_inner">
                                    <div class="Comments_animation_area"></div>
                                    <div class="Comments_animation_area bottom_ani"></div>
                                </div>
                            </div>
                            
                            <div class="TagItem_wrap" >
                                <div class="TagItem_current">
                                    <a class="TagItem_link">
                                        <div class="TagItem_thumbnail">
                                            <img class="TagItem_image" width="64" height="64" draggable="false" src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${replay.livePdVo.getPsUploadPath()}/${replay.livePdVo.getPsFileId()}">
                                        </div>
                                        <div class="TagItem_title">
                                            ${replay.livePdVo.getPsTitle()}
                                        </div>
                                        <span class="TagItem_price">
                                            <%-- <span class="TagItem_rate">${sale.psDiscount}%</span> --%>
                                            <strong><fmt:formatNumber value="${replay.livePdVo.getPsPrice()}" pattern="#,###" /></strong>원
                                        </span>
                                    </a>
                                </div>
                            </div>
                            <section class="ItemModal_section" style="display: none;">
                                <div class="ItemModal_content ItemModal_content_iframe">
                                    <div class="ModelTitle_wrap">
                                        <h3 class="ModelTitle_title">${replay.livePdVo.getPsTitle()}</h3>
                                        <button type="button" class="ModelTitle_btn">
                                            <i class="ModelTitle_icon"></i>
                                        </button>
                                    </div>
                                    <iframe class="TagItemIframe_iframe" src="http://192.168.219.100:8090/live/item?pd=${replay.psIndex}"></iframe>
                                </div>
                            </section>
                            <div class="CommentWrite_wrap">
                                <textarea class="CommentWrite_text" id="wa_textarea" placeholder="실시간 채팅에 참여하세요" cols="1" ></textarea>
                                <button type="submit" class="CommentWrite_btn" id="send_btn" disabled>전송</button>
                            </div>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
