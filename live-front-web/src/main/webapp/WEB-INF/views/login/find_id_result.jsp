<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div id="find_id_result_container">
    <div class="img_logo"><img src="/img/logo/logo.png"></div>
    <div id="find_id_result_form" class="find_div_form">
        <div class="find_form_title">
            <h1>아이디 찾기</h1>  
        </div>
        <div class="find_form_content">
            <div>입력하신 정보와 일치하는 회원입니다.</div>
            <div>정보 보호를 위해 일부만 알려 드립니다.</div>
        </div>
        <div class="find_result_area">
            <div class="id_li_height">
                <strong>아이디</strong>${userId}@${userEmail}
            </div>
        </div>
        <div class="find_btn_area">
            <button type="button" id="loginPage" class="btn_point find_btn_round" onClick="location.href='/login'">로그인</button>
            <button type="button" id="pwPage" class="btn_gray find_btn_round" onClick="location.href='/find/pw'">비밀번호 재설정</button>
        </div>
        <div class="find_memo">
            SNS 아이디로 가입하신 경우 각 SNS 간편 로그인을 이용해주세요.
        </div>
    </div>
</div>
