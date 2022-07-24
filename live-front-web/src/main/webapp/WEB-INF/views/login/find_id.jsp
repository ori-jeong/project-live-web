<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<div id="find_id_container">
    <div class="img_logo"><img src="/img/logo/logo.png"></div>
    <div id="find_id_form" class="find_div_form">
        <div class="find_form_title">
            <h1>아이디 찾기</h1>
        </div>
        <div class="find_form_content">
            <div>가입시 등록한 휴대폰 번호를 입력해주세요.</div>
        </div>
        <form name="fineIdForm" id="fineIdForm" method="post" autocomplete="off">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	        <div class="find_input_area">
	           <ul>
	               <li>
                        <input type="text" name="userUname" id="userNameFind" placeholder="이름">
	               </li>
	               <li>
                        <input type="text" name="userPhone" id="userPhoneFind" placeholder="휴대폰 번호(번호만 입력)" maxlength="13">
	               </li>
	           </ul>
	        </div>
	        <div class="find_btn_area">
	            <button type="button" id="btn_find_id" class="btn_find btn_point btn_radius_10">확인</button>
	        </div>
        </form>
        <div class="find_memo">
            SNS 아이디로 가입하신 경우 각 SNS 간편 로그인을 이용해주세요.
        </div>
    </div>
</div>
