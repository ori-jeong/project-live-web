<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div id="find_pw_container">
    <div class="img_logo"><img src="/img/logo/logo.png"></div>
        <div id="find_pw_form" class="find_div_form">
        <div class="find_form_title">
            <h1>비밀번호 재설정</h1>  
        </div>
        <div class="find_form_content">
            <div>변경할 비밀번호를 입력해주세요.</div>
        </div>
        <div class="find_input_area">
	        <div class="find_result_area">
	            <div>
	                <strong>아이디</strong><span class="id_result_spacing">${userId}</span>
	            </div>
	        </div> 
	        <ul class="find_reset_area">
                <li>
                    <input type="password" name="userPw" id="userPwReset" autocomplete="off" maxlength='16' placeholder="새 비밀번호를 입력해주세요.">       
                </li>
                <li>
                    <input type="password" id="userRpwReset" autocomplete="off" maxlength='16' placeholder="새 비밀번호를 한 번 더 입력해주세요.">                
                </li>
	        </ul>             
        </div>
        <div class="find_memo">
            비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용해 입력하세요.
        </div>
        <div class="btn_area">
            <button type="submit" id="btn_reset_pw" class="btn_find btn_point btn_radius_10">확인</button>
        </div>
    </div>
</div>
