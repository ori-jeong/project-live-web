<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div id="sign_in_container">
    <div class="img_logo login_img_logo"><img src="/img/logo/logo.png"></div>
    <div id="sign_in_form">      
        <form name="loginForm" autocomplete="off" method="POST">
            <div class="login_input_area">
                <div class="login_input">
                    <input type="text" name="userId" id="userId" placeholder="이메일 주소">
                </div>
                <div class="login_input">
                    <input type="password" name="userPw" id="userPw" placeholder="비밀번호">
                </div>
            </div>
            <div class="login_check_area">
	            <input type="checkbox" name="rememberId" id="keep_id" class="keep_check" value="true">
                <label for="keep_id"></label>
                <label class="label_text">아이디 저장</label>
                <input type="checkbox" name="rememberMe" id="keep_signed" class="keep_check" value="true">
                <label for="keep_signed"></label>
                <label class="label_text">로그인 상태 유지</label>
            </div>
            <div id="login_regul" class="regul_div">${requestScope.loginFailMsg}</div>          
            <div id="btn_login">
                <button type="submit" id="btn_login" class="btn_login btn_point btn_radius_10">로그인</button>
            </div>              
        </form>
    </div>
	<div class="caption">
	    <a href="/find/id">아이디 찾기</a> <em></em> 
	    <a href="/find/pw">비밀번호 재설정</a> <em></em> 
	    <a href="/signup">회원가입</a>
	</div>
    <div id="link_id_login"> 
        <div class="or_bar_box">
            <span class="or_bar or_bar_left"></span>
            <div> 다른 서비스 계정으로 로그인</div>
            <span class="or_bar or_bar_right"></span>
        </div>
        <div class="login_sns">
	        <button id="btn_sns" class="btn_sns_na">
	            <span class="logo_n">N</span>
	        </button>
	        <button id="btn_sns" class="btn_sns_ka">
	            <span class="bubble"></span>
	        </button>       
        </div>       

	</div>
</div>
<script>
$(document).ready(function() {
	/////////////////*sign in*///////////////////
	//쿠키로 저장한 id 가져와서 input에 출력
	//아이디저장하기 체크박스 체크하기
	if (_cookie.get("rememberId")) {
       document.loginForm.userId.value = _cookie.get("rememberId");
	   $("input:checkbox[name='rememberId']").prop("checked", true);
	} else {
	   $("input:checkbox[name='rememberId']").prop("checked", false);
	}
});
</script>
