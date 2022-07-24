<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div id="find_pw_container">
    <div class="img_logo"><img src="/img/logo/logo.png"></div>
    <div id="find_pw_form" class="find_div_form">
        <div class="find_form_title">
            <h1>비밀번호 재설정</h1>  
        </div>
        <div class="find_form_content form_content_left">
            <div style="padding: 12px 0;">아이디 확인 후 등록된 이메일 주소로 비밀번호 <br> 재설정을 위한 인증 메일이 발송됩니다.</div>
        </div>
        <form name="finePwForm" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <div class="find_input_area">
                <ul>
                    <li class="relative">
                        <input type="text" name="userId" id="userIdFind" autocomplete="off" placeholder="아이디(ex : onlShop@example.com)">
                        <button type="button" class="btn_sendCodeText" id="sendEmailCodeBtn" >전송</button>
                    </li>
                    <li>
                        <input type="text" name="authCode" id="authCodeFind" autocomplete="off" placeholder="인증번호">
                    </li>
                </ul>                
            </div>
            <div class="find_btn_area">
                <button type="button" id="checkEmailCodeBtn" class="btn_find btn_point btn_radius_10">확인</button>
            </div>
        </form>
        <div class="find_memo">
            SNS 아이디로 가입하신 경우 각 SNS 홈페이지에서 비밀번호 재설정이 가능합니다.
        </div>
    </div>
</div>
