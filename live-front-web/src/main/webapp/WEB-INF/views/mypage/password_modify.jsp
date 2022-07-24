<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<div id="userInfo_wrap" class="mypage_wrap">
    <div id="userInfo_area" class="mypage_area">
        <div class="mypage_title">
            <h1>비밀번호 변경</h1>
        </div>
        <div class="mypage_content">
            <div class="mypage_group">
                <div class="userInfo_input_area">
                    <div class="subject">현재 비밀번호</div>
                    <input type="password" class="input_w_100" id="nowPw">
                </div>
                <div class="userInfo_input_area">
                    <span class="subject">새 비밀번호</span>
                    <input type="password" class="input_w_100" id="newPw">
                </div>
                <div class="userInfo_input_area">
                    <span class="subject">새 비밀번호 확인</span>
                    <input type="password" class="input_w_100" id="newRpw">
                </div>
                <div class="userInfo_btn_area">
                   <button type="button" id="edit_pw" class="info_edit btn_point btn_radius_5">비밀번호 변경</button>
                </div>
            </div>
        </div>
    </div>
</div>