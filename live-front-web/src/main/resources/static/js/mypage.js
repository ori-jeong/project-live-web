$(document).ready(function() {
/////////////////*mypage nav*///////////////////
    $(".mypage_boxs .order_list").click(function(){
        location.href="/mypage/order_list";
    })
    $(".mypage_boxs .address_list").click(function(){
        location.href="/mypage/address_list";
    })
    $(".mypage_boxs .user_modify").click(function(){
        location.href="/mypage/userModify";
    })
    $(".mypage_boxs .password_modify").click(function(){
        location.href="/mypage/pwModify";
    })
/////////////////*mypage orderList*///////////////////
//날짜 조회
$('.btn_select_day').click(function(){
    var text = $(this).text();
    var value = '';
    console.log(text)
    if(text =='1개월전') value=1
    else if(text =='3개월전') value=3
    else value = text
    location.href='/mypage/order_list?before='+value;
})   
//상태조회
$('.orderList_menu_list').click(function(){
    var text = $(this).fine('.orderList_menu_list_title').text();
    var value = '';
    console.log(text)
    if(text =='입금대기') value=20
    else if(text =='결제완료') value=21
    else if(text =='배송준비') value=30
    else if(text =='배송중') value=32
    else if(text =='배송완료') value=33
    location.href='/mypage/order_list?status='+value;
}) 
//주문취소
$('.btn_cancel').click(function(){
    if(confirm("주문을 취소 하시겠습니까?")==true){
        var url = window.location.href;
        var clickId = $(this).attr('class').split('btn_cancel').join('').trim();
        var arrId = [];
        arrId = clickId.split('-');
        var option = new Object();
        option.data = {'orderId':arrId[0],'orderPdId':arrId[1],'psIndex':arrId[2]};
        option.success = function(v){
            if(v.result){
                alert(v.message);
                location.href = url;
            }else{
                alert(v.message);
            }
        }
        _ajax.ajaxData("/mypage/order_list/cancel_order_process","POST",option);
    }
}) 
//구매확정
$('.btn_complete').click(function(){
    if(confirm("구매 확정 처리 하시겠습니까?")==true){
        var url = window.location.href;
        var clickId = $(this).attr('class').split('btn_complete').join('').trim();
        var option = new Object();
        option.data = {'orderPdId':clickId};
        option.success = function(v){
            if(v.result){
                alert(v.message);
                location.href = url;
            }else{
                alert(v.message);
            }
        }
        _ajax.ajaxData("/mypage/order_list/confirmation_process","POST",option);
    }
})
/////////////////*mypage userInfo*///////////////////
    var nickCheck = false;
    //닉네임 체크
    $("#nick_check").click(function(){
        nickCheck = false;
        var nick = $("#nick_edit").val();
        if(nick==""){
            alert("닉네임을 입력해주세요.");
        }else if(nick.length<2){
            alert("닉네임을 2자 이상 입력해주세요.");
        }else{
            var option = new Object();
            option.data = {"userNickname":nick}
            option.success = function(v){
                nickCheck=true;
            }
            _ajax.ajaxAlert("/mypage/nickCheck","POST",option);
        }
    })
    //회원정보 수정
    $("#btn_userEdit").click(function(){
        var nick, phone;
        if(nickCheck){
            nick=$("#nick_edit").val();
        }
        if(checkUserPhone){
            phone = $("#userPhoneSign").val();
        }
        var option = new Object();
        option.data={"userNickname":nick,"userPhone":phone}
        option.success = function(v){
            location.href="/mypage/userModify";
        }        
        _ajax.ajaxAlert("/mypage/userModify/complete","POST",option);
    })
    //비밀번호 수정
    $("#edit_pw").click(function(){
        //비밀번호 유효성 체크 및 비교
        var nowPw = $("#nowPw").val();
        var pw = $("#newPw").val();
        var rpw = $("#newRpw").val();
        console.log(pw, rpw);
        if(_validate.userPw(pw)){
            if(pw != rpw){
              alert("비밀번호가 일치하지 않습니다.");  
            }else{
                //현재 비밃먼호 비교 후 새 비밀번호로 저장
                var option = new Object();
                option.data={"nowUserPw":nowPw,"newPw":pw}
                option.success= function(v){
                    location.href="/mypage/pwModify";
                }
                _ajax.ajaxAlert("/mypage/pwModify/complete","POST",option);
            }
        }else{
            alert("비밀번호는 영문, 숫자를 포함하여 8자 이상이어야 합니다.");
        }
    })
    $("#addr_insert").click(function(){
        window.open("/addrPop","addr_insert",'width=540,height=520,top=80,left=2');
    })
    $(".addrDelete").click(function(){
        var result = confirm("해당 배송지를 삭제하시겠습니까?");
        if(result){
            var index = $(this).siblings("#hash").val();
            var option = new Object();
            option.data={"addrIndex":index};
            option.success= function(v){
                location.reload();
            }
            _ajax.ajaxAlert("/mypage/deleteAddress","POST",option);
        }
    })
    $(".addrUpdate").click(function(){
        var index = $(this).prev("#hash").val();
        console.log(index);
        var url="/mypage/addrPop?index="+index;
        window.open(url,"addr_insert",'width=540,height=520,top=80,left=2');
    }) 
 
    $(".btn_addr_search").click(function(){
        new daum.Postcode({
            oncomplete: function(data) {
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수
                            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
    
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $("#zipCode").val(data.zonecode);
                $("#baseAddress").val(roadAddr);
            }
        }).open();    
    })
    $('#telNum').on('keyup',function(){        
        user_phone = $("#telNum").val();
        user_phone=_validate.phoneHyphen(user_phone);
        $("#telNum").val(user_phone);
    })
    $(".btn_save").click(function(){
        var addrIndex = $("#addrIndex").val();
        console.log(addrIndex);
        if(addrIndex == ""){
            addrIndex = 0;
        }
        console.log(addrIndex);
        var addrName = $("#addressName").val();
        var recipient = $("#recipient").val();
        var zipCode = $("#zipCode").val();
        var baseAddr = $("#baseAddress").val();
        var detailAddr = $("#detailAddress").val();
        var telNum = $("#telNum").val();
        var addrYn = $("#addressYn").prop("checked");
        if(recipient==""){
            alert("수령인을 입력해주세요.");
        }else if(zipCode==""){
            alert("우편번호를 입력해주세요.");
        }else if(telNum==""){
            alert("연락처를 입력해주세요.");
        }else {
            if(addrYn){
                addrYn = "Y";
            }else{
                addrYn = "N";
            }
            if(addrIndex==""){
                addrIndex=0;
            }
            var option = new Object();
            option.data={"addrIndex":addrIndex,"addrName":addrName,"addrRecipient":recipient
                ,"addrPostcode":zipCode,"addrRoadaddr":baseAddr,"addrDetailaddr":detailAddr
                ,"addrContact":telNum,"addrYn":addrYn};
            option.success = function(v){
                opener.parent.location.reload();
                window.close();
            }        
            _ajax.ajaxAlert("/mypage/setAddress","POST",option);
        }

    })
    
    $('.filter_days').click(function(){
        $('.filter_days .drop_down_content').toggleClass('is_selected');
    })
    $('.filter_status').click(function(){
        $('.filter_status .drop_down_content').toggleClass('is_selected');
    })
    $(document).mouseup(function (e){
      var selectList = $(".is_selected");
      if(selectList.has(e.target).length === 0){
        selectList.removeClass("is_selected");
      }
    });

});