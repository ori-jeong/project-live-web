<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="/img/logo/logo_header_icon.png">
    <title>오늘의쇼핑</title>
    <link rel="stylesheet" type="text/css" href="/css/video.css" >
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/hls.js@latest"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <!-- <script type="text/javascript" src="/js/live.js" ></script>    -->
    <script type="text/javascript" src="/js/common.js" ></script>  
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
                                    <button class="video_save">영상저장</button>
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
                                        <img class="LiveHeader_image" width="38" height="38" src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${live.liveSellerVo.getSelUploadPath()}/${live.liveSellerVo.getSelFileId()}">
                                        <span class="LiveBadge_live blind">LIVE</span>
                                    </div>           
                                    <div class="LiveHeader_text_area">
                                        <h2 class="LiveHeader_text">${live.liveTitle}</h2>
                                        <div class="LiveHeader_item_box">
                                            <span class="LiveHeader_nickname">${live.liveSellerVo.getSelName()}</span>
                                            <span class="LiveHeader_live">
                                                <i class="LiveHeader_view_icon"></i>
                                                <span class="LiveHeader_view_count"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </header>
                            <div class="NoticeContent_area blind"></div>
                            <div class="NoticeContent_box blind">
                                <div class="NoticeContent_text">라이브가 종료되었습니다.</div>    
                                <div class="NoticeContent_btn_home"> 다른 라이브 보기</div>                            
                            </div>
                            <div class="LiveVideoPlayer_wrap">
                                <div class="video_container">
                                    <video id="video"  muted disablepictureinpicture="true" controlslist="nodownload" width="100%" height="100%" preload="auto" poster="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${live.uploadPath}/${live.fileId}"></video>  
                                </div>
                            </div>
                            <div class="Comments_wrap" id="Comments_wrap">
                                <div class="Comments_inner">
                                    <div class="Comments_animation_area"></div>
                                </div>
                            </div>
                            
                            <div class="TagItem_wrap" >
                                <div class="TagItem_current">
                                    <a class="TagItem_link">
                                        <div class="TagItem_thumbnail">
                                            <img class="TagItem_image" width="64" height="64" draggable="false" src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${live.livePdVo.getPsUploadPath()}/${live.livePdVo.getPsFileId()}">
                                        </div>
                                        <div class="TagItem_title">
                                            ${live.livePdVo.getPsTitle()}
                                        </div>
                                        <span class="TagItem_price">
                                            <%-- <span class="TagItem_rate">${sale.psDiscount}%</span> --%>
                                            <strong><fmt:formatNumber value="${live.livePdVo.getPsPrice()}" pattern="#,###" /></strong>원
                                        </span>
                                    </a>
                                </div>
                            </div>
                            <section class="ItemModal_section" style="display: none;">
                                <div class="ItemModal_content ItemModal_content_iframe">
                                    <div class="ModelTitle_wrap">
                                        <h3 class="ModelTitle_title">${live.livePdVo.getPsTitle()}</h3>
                                        <button type="button" class="ModelTitle_btn">
                                            <i class="ModelTitle_icon"></i>
                                        </button>
                                    </div>
                                    <iframe class="TagItemIframe_iframe" src="/live/item?pd=${live.psIndex}"></iframe>
                                </div>
                            </section>
                            <button type="button" class="CommentBtn_wrap" aria-hidden="false">
                                <span class="CommentBtn_inner">
                                    <i class="header_icon CommentBtn_icon"></i>
                                    <!-- <span class="blind">댓글쓰기</span> -->
                                </span>
                            </button>
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
<script>
$(document).ready(function() {  
	
    const stream = "${live.liveSellerVo.selStreamKey}".trim();
    const roomId = "${live.liveSellerVo.selChatKey}".trim();
    const status = '${liveStatus}';
    const liveId = '${live.liveId}';
    const url = '${url}';
    const nick = '${nick}';
    //라이브 연결
    var video = document.getElementById('video');
    var videoSrc =url+"/hls/"+stream+"/index.m3u8";
    var hls = new Hls();
    if(status==1){
        if(video.canPlayType('application/vnd.apple.mpegurl')) {   // 우선 HLS를 지원하는지 체크
            video.src = videoSrc;
        }else if(Hls.isSupported()){  // HLS를 지원하지 않는다면 hls.js를 지원
            hls.loadSource(videoSrc);
            hls.attachMedia(video);
            hls.on(Hls.Events.MANIFEST_PARSED,()=>{
                video.play(); //라이브 시작
                $('.LiveBadge_live').removeClass('blind');
            })
            hls.on(Hls.Events.ERROR, function(data) {
                liveEnd();
            });
        }
    }else{
        liveEnd();
    }
    function liveEnd(){
        video.src ="";
        $('.NoticeContent_area').removeClass('blind');
        $('.NoticeContent_box').removeClass('blind');
        $('.CommentBtn_wrap').addClass('blind');
        $('.Comments_wrap').addClass('blind');
        hls.destroy();              //hls 종료
    }
    $('#Comments_wrap').scrollTop($('#Comments_wrap')[0].scrollHeight);
    //채팅창 열기 - 하단 팝업창 닫기, 채팅 입력창 열기
    $(".CommentBtn_wrap").click(function(){
        if(nick==""){
            location.href="/login";
        }else{
            this.classList.toggle('active');
        
            if(this.classList.contains('active')){
                //하단 팝업 버튼 숨기기
                $(".TagItem_wrap").css('display','none');
                $(".CommentWrite_wrap").css('visibility','visible');
            }else{
                $(".TagItem_wrap").css('display','');
                $(".CommentWrite_wrap").css('visibility','');
            } 
        }
    })
    
    var sock = new SockJS("/stomp/chat");  // 연결 주소
    var client = Stomp.over(sock);
    var header = {request_type:'web'};
   // client.connected = true;     
    client.debug = null;  //프레임 개발자 도구에서 숨기기
    client.connect({}, function(frame){
        //subscribe(path, callback)으로 메세지 받기
        client.subscribe("/sub/chat/room/"+roomId,function(chat){   // 서버 연결
            var content = JSON.parse(chat.body);
            var writer = content.writer;
            var msg = content.message;
            var str = '';    
            if(writer == "master_user_seller"){
                str = "<div class='Comments_master'>";
                str +="<span class='Comment_comment_master'>"+msg+"</span>";
                str +="</div>";             
            }else{
                str = "<div class='comment_user'>";
                str +="<strong class='Comment_id'>"+writer+"</strong>";
                str +="<span class='Comment_comment'>"+msg+"</span>";
                str +="</div>";             
            }
            $('.Comments_animation_area').append(str);   
            $('#Comments_wrap').scrollTop($('#Comments_wrap')[0].scrollHeight);
        });   
       //send(path,header,message)로 메세지 전송
       client.send('/pub/chat/enter', {}, JSON.stringify({'chatRoomId': roomId,'liveId': liveId}))
       //메세지 받기
       client.subscribe("/sub/chat/count/"+roomId,function(chat){
            var count = JSON.parse(chat.body);
            $('.LiveHeader_view_count').text(count);
         }); 
    })
    
    $("#send_btn").click(function(){
        var msg = $("#wa_textarea").val();
        client.send('/pub/chat/message', {}, JSON.stringify({chatRoomId: roomId, message: msg, writer: nick}));
        $("#wa_textarea").val('');   
        $("#send_btn").attr("disabled",true);
    })
    
    //채팅 입력창 값 있으면 전송 버튼 활성화
    $("#wa_textarea").keyup(function(e){
        //전송 버튼 활성화
        if($("#wa_textarea").val()==""){
            $("#send_btn").attr("disabled",true);
        }else{
            $("#send_btn").attr("disabled",false);
        }
        //textarea 키 값 이벤트
        if((e.ctrlKey || e.metaKey) && (e.keyCode == 13 || e.keyCode == 10)) {
            //ctrl+enter
            $("#wa_textarea").val(function(i,val){
                return val + "\n";
            });
        }
/*        else if(e.keyCode==13){ //엔터시 줄바꿈 처리
            e.preventDefault(); //엔터시 줄바꿈 방지(동작중단)
            sendMsg();  
        }*/
       
    });
});
</script>
</html>