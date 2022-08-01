$(document).ready(function() {  
    //라이브 연결
    var video = document.getElementById('video');
    var videoSrc =url+"/"+stream+"/index.m3u8";
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
            str = "<div class='comment_my'>";
            str +="<strong class='Comment_id'>"+writer+"</strong>";
            str +="<span class='Comment_comment'>"+msg+"</span>";
            str +="</div>"
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
        else if(e.keyCode==13){ //엔터시 줄바꿈 처리
            e.preventDefault(); //엔터시 줄바꿈 방지(동작중단)
            sendMsg();  
        }
    });
});
