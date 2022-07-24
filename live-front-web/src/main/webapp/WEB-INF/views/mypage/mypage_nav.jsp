<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="mypage_wrap" class="mypage_wrap">
    <div id="mypage_area" class="mypage_area">      
        <div class="mypage_title">
	        <h1>My Page</h1>
	    </div>
	    <div class="menubox">
	       <div class="menubox_group">
            <div class="mypage_boxs">                   
                <div class="order_list" >
                    <p>구매내역</p>
                </div>              
            </div>
            <div class="mypage_boxs">
                <div class="address_list" style="background-position: 0 -100px;">
                    <p>배송지관리</p>
                </div>
            </div>
            <div class="mypage_boxs">
                <div class="user_modify" style="background-position: 0 -200px;">
                    <p>회원정보수정</p>
                </div>
            </div>
            <div class="mypage_boxs">
                <div class="password_modify" style="background-position: 0 -300px;">
                    <p>비밀번호변경</p>
                </div>
            </div>
	       </div>

	    </div>
	</div>
</div>