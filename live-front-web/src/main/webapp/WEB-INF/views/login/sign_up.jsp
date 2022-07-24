<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
<div id="signup_container">
    <div class="img_logo signup_img_logo"><img src="/img/logo/logo.png"></div>
    <div id="signup_form">
        <div class="signup_title"><h1>회원가입</h1></div>
        <form name="signUpForm" id="signUpForm" autocomplete="off">
            <div class="signup_input_area" >
                <div class="subject">아이디</div>
                <div class="content">로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해 주세요.</div>
                <input type="text" name="userId" id="userIdSign" autocomplete="off" maxlength='50' placeholder="ex) onlShop@example.com">
                <div id="id_error" class="regul_div"></div>
            </div>
            <div class="signup_input_area">
                <div class="subject">비밀번호</div>
                <div class="content">8~16자 영문 대 소문자, 숫자, 특수문자(!@#$%^&*()-_+=\.)를 입력하세요.</div>            
                <input type="password" name="userPw" id="userPwSign" autocomplete="off" maxlength='16' autocomplete="new-password">
                <div id="pw_error" class="regul_div"></div>
            </div>
            <div class="signup_input_area">
                <div class="subject">비밀번호 재확인</div>
                <input type="password" name="userRpw" id="userRpwSign" autocomplete="off" maxlength='16' autocomplete="new-password">
                <div id="rpw_error" class="regul_div"></div>
            </div>
            <div class="signup_input_area">
                <div class="subject">이름</div>
                <input type="text" name="userUname" id="userNameSign" autocomplete="off" maxlength='15' placeholder="ex) 홍길동">
                <div id="name_error" class="regul_div"></div>
            </div>
            <div class="signup_input_area">
                <div class="subject">전화번호</div>
                <input type="text" name="userPhone" id="userPhoneSign" autocomplete="off" placeholder="휴대폰 번호(번호만 입력)"
                    oninput="this.value = this.value.replace(/[^0-9.-]/g, '').replace(/(\..*)\./g, '$1');" maxlength='13'>
                <button type="button" id="sendPhoneCodeBtn" class="btn_sandCode btn_point btn_radius_5">인증번호 받기</button>
                <div class="relative">
                    <input type="text" name="authCode" id="authCodeSign" disabled="disabled" class="input_disabled" 
                            autocomplete="off" placeholder="인증번호를 입력하세요.">
                    <button type="button" id="phoneCodeCheckBtn" disabled="disabled" class="btn_checkCode btn_gray btn_radius_5" >확인</button>                
                </div>
                <div id="code_error" class="regul_div"></div>
            </div>
            <div class="signup_btn_area">
                <button type="button" id="btn_sign" class="btn_sign btn_point btn_radius_5">가입하기</button> 
            </div>            
        </form>
    </div>
</div>
