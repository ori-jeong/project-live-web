<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<style>
.admin_tool_inner_chat{margin-right: auto;margin-left: auto;max-width: 100%;box-sizing: border-box;min-height: 1px;}
.admin_tool_top_chat{max-width: 880px;position: relative;display: block;margin: 0 auto;}
.top_chat{margin: 20px 0;font-size: 20px;border: 1px #ddd solid;border-radius: 10px;padding: 20px;}
.admin_tool_content_chat{margin-top: 50px;width: 100%;overflow: visible;line-height: 20px;min-height: 100%;}
.live_area{margin-right: 20px;display: inline-block;}
.live_iframe{width: 400px;height: 700px;border-radius: 15px;border: 0;pointer-events: none;}
.admin_chat_area{display: inline-block;}
.admin_chat_wrap{margin-bottom: env(safe-area-inset-bottom);overflow-y: scroll;transition: bottom 0.5s cubic-bezier(0, 1, 0, 1) 0.1s,max-height 0.1s linear;
    line-height: 1.3;font-size: 14px;color: #fff;overscroll-behavior: none;will-change: bottom;width: 450px;height: 700px;border-radius: 15px;display: table;position: relative;}
.admin_chat_inner{width: 450px;height: 700px;border: 1px solid #d8d8d8;border-radius: 15px;position: relative;z-index: 1;overflow: hidden;}
.chat_list{position: relative;z-index: 1;overflow: hidden;height: 620px;margin: 10px;text-align: left;}
.chat_input_area{padding-bottom: 10px;position: absolute;right: 0;bottom: 0;left: 0;z-index: 80;display: flex;flex-wrap: wrap;visibility: visible;border-top: 1px solid #d8d8d8;}
.chat_input{flex: 1 1 auto;height: 23px;padding: 11px 0 11px 13px;border: 0;background-color: #fff;line-height: 1.54;font-size: 15px;color: #303038;letter-spacing: -0.25px;word-break: break-all;overflow: hidden;}
.chat_send{flex: 0 0 auto;height: 45px;margin: auto 2px 0 0;padding: 13px 13px 13px 8px;line-height: 1.27;font-size: 15px;font-weight: 400;letter-spacing: -0.25px;color: #6c6c6c;}
.Comment_user{display: block;margin-top: 6px;font-weight: 500;color: #494949;}
.Comment_comment{display: inline;margin-left: 5px;font-weight: 500;}
.Comment_comment_master{display: block;margin-top: 7px;font-weight: 700;}
.Comments_master{color:#22c3f6;}
.Comments_animation_area{position: absolute;right: 0;bottom: 0;left: 0;transform: perspective(0);}

</style>
<div id="admin_wrap">
    <div class="admin_content">
        <div class="admin_menulist">
            <div class="admin_tablist">
                <a href="/tool/live"      class="main_tab" aria-selected="true">라이브 관리</a>
                <a href="/tool/products"  class="main_tab" aria-selected="false">상품 관리</a>
                <a href="/tool/pdpost" class="main_tab" aria-selected="false">판매글 관리</a>
                <a href="/tool/sale"     class="main_tab" aria-selected="false">판매 내역 관리</a>
                <a href="/tool/setting"   class="main_tab" aria-selected="false">설정</a>
            </div>
        </div>
        <div class="admin_tool_wrap">
            <div class="admin_tool_area">
                <div class="admin_tool_inner_chat">
                    <div class="admin_tool_top_chat">
                        <div class="top_chat">
                            <h1>라이브쇼핑 채팅</h1>
                        </div>
                    </div>
                    <div class="admin_tool_content_chat">
                        <div class="live_area">
                            <iframe class="live_iframe" src="/live?fm=${liveId}"></iframe>
                        </div>
                        <div class="admin_chat_area">
                            <div class="admin_chat_wrap">
                            <div class="admin_chat_inner" id="admin_chat_inner">
	                            <div class="chat_list">
	                                <div class="Comments_animation_area"></div>
	                            </div>
	                            <div class="chat_input_area">
	                               <textarea id="chat_input" class="chat_input"></textarea>
	                               <button type="submit" id="master_send" class="chat_send">전송</button>
	                               
	                            </div>                            
                            </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>        
    </div>
</div>
<script>
$(document).ready(function(){ 
	const chatUrl = '${chatUrl}';	   
    const roomId = "${liveKey.selChatKey}".trim();
    var sock = new SockJS(chatUrl+"/stomp/chat", null, {transports: ["websocket", "xhr-streaming", "xhr-polling"]});  // 연결 주소
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
            if(writer == null){
                str = "<div class='Comments_master'>";
                str +="<span class='Comment_comment_master'>"+msg+"</span>";
                str +="</div>";            	
            }else{
                str = "<div class='Comment_user'>";
                str +="<strong class='Comment_id'>"+writer+"</strong>";
                str +="<span class='Comment_comment'>"+msg+"</span>";
                str +="</div>";            	
            }

            $('.Comments_animation_area').append(str);   
            $('#admin_chat_inner').scrollTop($('#admin_chat_inner')[0].scrollHeight);
        });   
       //send(path,header,message)로 메세지 전송
       client.send('/pub/chat/enter', {}, JSON.stringify({'chatRoomId': roomId,'liveId': "${liveId}"}))
       //메세지 받기
/*        client.subscribe("/sub/chat/count/"+roomId,function(chat){
            var count = JSON.parse(chat.body);
            $('.LiveHeader_view_count').text(count);
         });  */
    })
    
    $("#master_send").click(function(){
    	send();
    })
    
    //채팅 입력창 값 있으면 전송 버튼 활성화
    $("#chat_input").keyup(function(e){
        //전송 버튼 활성화
        if($("#chat_input").val()==""){
            $("#master_send").attr("disabled",true);
        }else{
            $("#master_send").attr("disabled",false);
        }
        if(e.keyCode==13 || e.keyCode == 10){
            e.preventDefault(); //엔터시 줄바꿈 방지(동작중단)
            send();
         }
    });
    function send(){
        var msg = $("#chat_input").val();
        client.send('/pub/chat/message', {}, JSON.stringify({chatRoomId: roomId, message: msg}));
        $("#chat_input").val('');    
        $("#master_send").attr("disabled",true);
    }
    
});
</script>