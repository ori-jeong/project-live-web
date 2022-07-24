<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="user" property="principal"/>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
<div id="signup_container">
    <div class="img_logo signup_img_logo"><img src="/img/logo/logo.png"></div>
    <div id="signup_form">
        <div class="signup_title"><h1>판매자 등록</h1></div>
        <form name="signUpForm" id="signUpForm" autocomplete="off">
            <div class="signup_input_area" >
                <div class="subject">판매자 아이디</div>
                <input type="text" autocomplete="off" value="${user.userId}" readonly> 
            </div>
            <div class="signup_input_area">
                <div class="subject">판매자 업체 이름</div>
                <input type="text" id="selCpName" autocomplete="off" value="${userInfo.userId}" maxlength='15' placeholder="ex)(주)회사명" > 
                <div id="selCpName_error" class="regul_div"></div>
            </div>
            <div class="signup_input_area">
                <div class="subject">사업자 등록번호</div>
                <input type="text" id="selCode" autocomplete="off" maxlength='13'>
                <div id="selCode_error" class="regul_div"></div>
            </div>
            <div class="signup_input_area">
                <div class="subject">판매자 이름</div>
                <input type="text" id="selName" autocomplete="off" maxlength='13' placeholder="ex) OO스토어">
                <button type="button" id="selName_check" class="btn_sandCode btn_point btn_radius_5" >중복확인</button>
                <div id="selName_error" class="regul_div"></div>
            </div>
            <div class="signup_input_area-img filebox">
                <div class="subject">판매자 이미지</div>
                <input class="uploadProfile"  id="fileName" disabled="disabled" placeholder="파일선택" >
                <label for="fileCheck">업로드</label> 
                <input type="file" name="fileId" id="fileCheck" class="layout_input_file btn_post_img"> 
                <div id="name_error" class="regul_div"></div>                      
            </div>
            <div class="signup_btn_area">
                <button type="button" id="btn_selInsert" class="btn_sign btn_point btn_radius_5">판매자 등록하기</button> 
            </div>            
        </form>
    </div>
</div>
