var checkUserId = false;
var checkUserPw = false;
var checkUserRpw = false;
var checkUserPhone = false;
var checkAuthCode = false;
var checkName = false;

var checkUserIdAct = true;
var user_phone="";


$(document).ready(function() {
var id_error = $('#id_error');
var pw_error = $('#pw_error');
var rpw_error = $('#rpw_error');
var name_error = $("#name_error");
var code_error = $("#code_error");
/////////////////*sign in*///////////////////
    //아이디저장하기 체크박스가 변경됐을때 
    //체크: 아이디 쿠키값으로 저장
    //체크해제: 아이디 쿠키값 삭제
    $("input:checkbox[name='rememberId']").change(function(){
        if ($("input:checkbox[name='rememberId']").is(":checked") == true) {
            _cookie.set("rememberId", $("#userId").val());
        } else {
            _cookie.remove("rememberId");
        }
    });
    //로그인 하기
    $('#btn_login').click(function(){
        var login_regul = $("#login_regul");
        var id = $('#userIdSign').val();
        var pw = $('#userPwSign').val();
        if(id == ""){
            login_regul.html("아이디를 입력해주세요");
            return false;
        }else if(pw == ""){
            login_regul.html("비밀번호를 입력해주세요");
            return false;
        }
        //아이디 저장
        //자동로그인
        document.loginForm.action="/loginProcess";
        document.loginForm.method="POST"
        document.loginForm.submit();
    })
    //카카오 로그인
    $('.btn_sns_ka').click(function(){
        location.href="/oauth2/authorization/kakao";
    })
    //네이버 로그인
    $('.btn_sns_na').click(function(){
        location.href="/oauth2/authorization/naver";
    })
/////////////////*sign up*///////////////////
    //아이디 체크
    $('#userIdSign').on('keyup',function(){
        checkUserId = false;
        let user_id = $("#userIdSign").val();        
        if(_validate.userId(user_id)){
            if(checkUserIdAct){
                checkUserIdAct = false;
                var option = new Object();
                option.contentType
                option.data ={userId: user_id};
                option.success = function(data){
                    checkUserIdAct = true;
                    if(!data.result){
                        id_error.html(data.message);
                    }else{
                        //결과
                        checkUserId = true;
                        $('#userIdSign').removeClass('border_red_act');
                        id_error.html("");
                    }
                }
            }
           _ajax.ajaxData("/signup/searchId","POST",option);
        }else{
            $("#userIdSign").addClass('border_red_act');
            if(user_id==''){
                id_error.html("필수 입력입니다.");
            }else{
                id_error.html("이메일 형식이 올바르지 않습니다.");
            }
        }
    })
    
    //비밀번호 유효성 체크
    $('#userPwSign').on('keyup',function(){
        checkUserPw = false;
        let user_pw = $("#userPwSign").val();
        if(_validate.userPw(user_pw)){
            $('#userPwSign').removeClass('border_red_act');
            pw_error.html("");
            checkUserPw = true;
        }else{
            $("#userPwSign").addClass('border_red_act');
            pw_error.html("비밀번호는 영문, 숫자를 포함하여 8자 이상이어야 합니다.");
        }
    })
    
    //비밀번호 재입력
    $('#userRpwSign').on('keyup',function(){
        checkUserRpw = false;
        let user_pw = $("#userPwSign").val();
        let user_rpw = $("#userRpwSign").val();
        if(user_pw==user_rpw){
            $('#userRpwSign').removeClass('border_red_act');
            rpw_error.html('');
            checkUserRpw = true;
        }else{
            $("#userRpwSign").addClass('border_red_act');
            rpw_error.html("비밀번호가 일치하지 않습니다.");
        }
    })
    
    //이름 확인
    $('#userNameSign').on('keyup',function(){
        checkName = true;
        $('#name_error').html("");
    })
      
    //휴대폰 하이픈 넣기
    $('#userPhoneSign').on('keyup',function(){
        //전화번호 수정 시
        checkUserPhone = false;
        checkAuthCode = false;
        
        $("#authCodeSign").val("");
        $('#authCodeSign').attr("disabled")
        $('#authCodeSign').addClass('input_disabled');        
        
        user_phone = $("#userPhoneSign").val();
        user_phone=_validate.phoneHyphen(user_phone);
        $("#userPhoneSign").val(user_phone);
        if(_validate.userPhone(user_phone)){
            checkUserPhone = true;
        }else{
            checkUserPhone = false;
        }
    })
    
    //인증번호 발송
    $('#sendPhoneCodeBtn').click(function(){
        if(checkUserPhone){
            code_error.html('');
            var option = new Object();
            option.data = {"sendTo": user_phone, "sendType" : "phone"};
            option.success = function(data){ 
                if(data.result){
                    alert(data.message);
                    $('#authCodeSign').removeAttr("disabled");
                    $('#authCodeSign').removeClass('input_disabled');
                    $('#phoneCodeCheckBtn').removeAttr("disabled");     
                    $('#phoneCodeCheckBtn').removeClass('btn_gray');
                }else{
                    alert(data.message);
                }           
            }
            _ajax.ajaxData("/signup/sendCode","POST",option);
        }else if($("#userPhoneSign").val()==""){
            code_error.html("휴대폰 번호를 입력해 주세요.");
        }else{
            code_error.html("입력하신 휴대폰 번호가 형식에 맞지 않습니다. 다시 입력해 주세요.");
        }

    })
    
    //인증번호 확인
    //blur 입력후 체크 or keyup으로 글자수 체크 
    $('#phoneCodeCheckBtn').click(function(){
        let input_code = $("#authCodeSign").val();

        checkAuthCode = false;
        //입력한 값이 6글자 이후 체크
        if(input_code.length>5){
            var option = new Object();
            option.data = {"sendTo": user_phone, "inputCode" : input_code};
            option.success = function(data){
                if(data.result){
                    code_error.html('');
                    checkUserPhone = true;
                    checkAuthCode = true;
                    $('#userPhoneSign').attr("readonly",true);
                    $('#userPhoneSign').addClass("input_disabled");
                    $('#sendPhoneCodeBtn').attr("disabled",true);
                    $('#sendPhoneCodeBtn').addClass('btn_gray');
                    $('#authCodeSign').attr("disabled",true);
                    $('#authCodeSign').addClass('input_disabled');
                    $('#phoneCodeCheckBtn').attr("disabled",true);
                    $('#phoneCodeCheckBtn').addClass('btn_gray');
                }else{
                    alert(data.message);
                }
            }
            _ajax.ajaxData("/signup/searchAuthCode","POST",option);
        }else{
            code_error.html("올바른 인증 코드가 아닙니다.");
        }       
    })
    
    //회원가입 버튼
    $('#btn_sign').click(function(){
        if($("#userIdSign").val()==""){
            id_error.text("필수 입력 항목입니다.");
        }
        if($("#userPwSign").val()==""){
            pw_error.html("필수 입력 항목입니다.");
        }
        if($("#userRpwSign").val()==""){
            rpw_error.html("필수 입력 항목입니다.");
        }
        if($("#userNameSign").val()==""){
            name_error.html("필수 입력 항목입니다.");
        }else{
            name_error.html("");
            checkName = true;
        }
    
        if($("#userPhoneSign").val()=="" || $("#authCodeSign").val()==""){
            code_error.html("필수 입력 항목입니다.");
        }else if($("#userPhoneSign").val()!="" && checkAuthCode==false){
            code_error.html("인증번호를 확인해 주세요.");
        }
        if(checkUserId && checkUserPw && checkUserRpw  
            && checkUserPhone  && checkAuthCode  && checkName ){
            var option = new Object();        
            option.data = $('#signUpForm').serialize();
            option.success = function(data){
                if(data.result){
                    alert(data.message);
                    location.href ="/"
                }else{
                    alert(data.message);
                }   
            }
            _ajax.ajaxData("/signup/complete","POST",option);
        }
    })
    
/////////////////*find*///////////////////
    //핸드폰 하이픈 넣기
    $('#userPhoneFind').on('keyup',function(){
        var user_phone = $("#userPhoneFind").val();
        user_phone=_validate.phoneHyphen(user_phone);
        $("#userPhoneFind").val(user_phone);
    })  
    
    //아이디 찾기
    $('#btn_find_id').click(function(){
        if($('#userNameFind').val()==""){
            alert("이름을 입력하세요");
        }else if($('#userPhoneFind').val()==""){
            alert("휴대폰 번호를 입력해 주세요.");
        }else{
            var option = new Object();
            option.data = $("#fineIdForm").serialize();
            option.success = function(v){
                document.fineIdForm.action="/find/id/complete";
                document.fineIdForm.submit();
            }
            _ajax.ajaxAlert("/find/id/userCheck","POST",option);
        }
    })
    
    //비밀번호 재설정 인증코드 전송
    $('#sendEmailCodeBtn').click(function(){
        if($('#userIdFind').val()==""){
            alert("아이디(이메일)를 입력하세요");
        }else{
            var option = new Object();
            option.data={"sendTo":$('#userIdFind').val()};
            option.success = function(v){
                $('#btn_reset_pw').removeAttr("disabled");           
            }
            _ajax.ajaxAlert("/find/pw/sendCode","POST",option);
        }
    })
    //인증번호 비교 후 재설정 페이지 이동
    $('#checkEmailCodeBtn').click(function(){
        var email = $('#userIdFind').val();
        var code = $('#authCodeFind').val();
        if(email==""){
            alert("아이디를 입력하세요.");
        }else if(code==""){
            alert("인증 코드를 입력하세요.");
        }else{
            var option = new Object();
            option.data={"sendTo":email, "inputCode":code};
            option.success = function(v){
                $('#btn_reset_pw').removeAttr("disabled");
                document.finePwForm.action="/find/pw/reset";
                document.finePwForm.submit();               
            }
           _ajax.ajaxAlert("/find/pw/authCodeCheck","POST",option);          
        }
    })
    
    //비밀번호 재설정
    $('#btn_reset_pw').click(function(){
        let user_id = $(".id_result_spacing").text();
        let user_pw = $("#userPwReset").val();
        let user_rpw = $("#userRpwReset").val();
        if(user_pw==""){
            alert("새 비밀번호를 입력해 주세요.");
        }else if(user_rpw==""){
            alert("새 비밀번호 확인을 입력해 주세요.");
        }else if(!_validate.userPw(user_pw)){
            alert("비밀번호는 영문, 숫자를 포함하여 8자 이상이어야 합니다.");
        }else if(user_pw!=user_rpw){
            alert("새 비밀번호와 확인 번호가 맞지 않습니다.다시 입력해 주세요.");
        }else{
            var option = new Object();
            option.data = {"userId":user_id, "userPw":user_pw};
            option.success = function(){
                location.href="/";
            }
            _ajax.ajaxAlert("/find/pw/complete","POST",option);
        }
    })
    
 ///////////////////////판매자 등록
    let selNameCheck = false;
    //판매자 이름 중복확인
    $('#selName_check').click(function(){
        var selName = $("#selName").val();
        console.log(selName);
        if(selName == ''){
            alert("닉네임을 입력해주세요.");
        }else{
            var option = new Object();
            option.data = {"selName":selName};
            option.success = function(v){
                selNameCheck=true;
            }
            _ajax.ajaxAlert("/selNameCheck","POST",option);            
        }
        
    })
    let formData = new FormData();
    //판매지 이미지 등록
    $('.btn_post_img').on('change', function(){
        console.log(123)
        var maxSize = 5242880; //5MB
        let regex = new RegExp("(.)\.(jpg|png|JPG|PNG|image/png)$");
        var files =$("input[type='file']")[0].files; 
        var filename;
        if(files[0].size >= maxSize){
            alert("파일 사이즈 초과");
            return false;
        }else if(regex.test(files[0].name)==false){
            alert("해당 종류의 파일은 업로드할 수 없습니다.");
            return false;
        }else{
            if(window.FileReader){
               filename = files[0].name;
            } else {
               filename = files[0].val().split('/').pop().split('\\').pop();
            }
            formData.set('file',files[0]);
        }       
        $('.uploadProfile').removeClass('red_act');
        $(this).siblings('.uploadProfile').val(filename);

    });
    //판매자 등록하기
    $('#btn_selInsert').click(function(){
        let selCpName = $('#selCpName').val();
        let selCode = $('#selCode').val();
        let selName = $('#selName').val();
        let upload = $('#fileName').val();
        let isVal = true;
        if(selCpName == ''){
           $('#selCpName').addClass('red_act');
           isVal = false;
        }
        if(!selNameCheck){
            alert("중복확인을 해주세요.")
            isVal = false;
        }else if(selCode == ''){
           $('#selCode').addClass('red_act');
           isVal = false;
        }

        if(selName == ''){
           $('#selName').addClass('red_act');
           isVal = false;
        }
        if(upload == ''){
           $('#fileName').addClass('red_act');
           isVal = false;
        }
        
        if(isVal){
           var fileId = uploadImg();
           var option = new Object();        
            option.data = {"selCpName":selCpName, "selCode":selCode, "selName":selName,'fileId':fileId}
            option.success = function(data){
                if(data.result){
                    alert(data.message);
                    location.href ="/"
                }else{
                    alert(data.message);
                }   
            }
            _ajax.ajaxData("/proSellerInsert","POST",option);
        }
    });
    function uploadImg(){
        var result = '';
        //이미지 업로드
        var option = new Object();
        option.data = formData
        option.success = function(v){
            //uploadFileId = ;   //전송된 파일 id 값 저장
            result =  v.data.fileId;
        }
        _ajax.ajaxMultipart("/uploadFileAws","POST",option);
        return result;
    }
});

