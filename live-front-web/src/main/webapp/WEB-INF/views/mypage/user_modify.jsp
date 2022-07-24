<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="user" property="principal"/>
<script type="text/javascript" src="/js/login.js"></script>
<div id="userInfo_wrap" class="mypage_wrap">
    <div id="userInfo_area" class="mypage_area">
        <div class="mypage_title">
            <h1>회원정보 수정</h1>
        </div>
        <div class="mypage_content">
            <div class="mypage_group">
	            <div class="withdrawal"><a href="">탈퇴하기</a></div>
	            <div class="userInfo_input_area">
	                <div class="subject">이메일</div>
	                <input type="text" class="input_w_100" value="${userInfo.userId}" readonly>
	            </div>
	            <div class="userInfo_input_area">
	                <span class="subject">이름</span>
	                <input type="text" class="input_w_100" value="${userInfo.userUname}" readonly>
	            </div>
	            <div class="userInfo_input_area">
	                <span class="subject">닉네임</span>
	                <div class="dis_flex">
	                   <input type="text" id="nick_edit" class="input_w_75" value="${userInfo.userNickname}">
                        <button type="button" id="nick_check" class="info_btn btn_point btn_radius_5" >중복확인</button>
	                </div>
	                <div class="regul_div">* 2자 이상 입력해주세요.</div>
	            </div>
	            <div class="userInfo_input_area">
	                <span class="subject">전화번호</span>
	                <div class="dis_flex">
		                <input type="text" class="input_w_75" value="${userInfo.userPhone}" id="userPhoneSign" autocomplete="off"
		                  oninput="this.value = this.value.replace(/[^0-9.-]/g, '').replace(/(\..*)\./g, '$1');" maxlength='13'>
		                <button type="button" id="sendPhoneCodeBtn" class="info_btn btn_point btn_radius_5">인증번호</button>
	                </div>
	                <div class="dis_flex margin_7">
                       <input type="text" name="authCode" id="authCodeSign" disabled="disabled" class="input_disabled input_w_75" 
                                    autocomplete="off" placeholder="인증번호를 입력하세요.">
                        <button type="button" id="phoneCodeCheckBtn" disabled="disabled" class="info_btn btn_gray btn_point btn_radius_5" >확인</button>
                    </div>
	            </div>
	            <div class="userInfo_btn_area">
	               <button type="button" id="btn_userEdit" class="info_edit btn_point btn_radius_5">회원정보 수정</button>
	            </div>
            </div>
        </div>
    </div>
</div>