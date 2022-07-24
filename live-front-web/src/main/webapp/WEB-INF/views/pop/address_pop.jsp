<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"    prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link rel="stylesheet" type="text/css" href="/css/common.css" >
    <link rel="stylesheet" type="text/css" href="/css/mypage.css" >
    <title>오늘의쇼핑</title>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script> 
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/mypage.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body style="background:#fff;">
    <input type="hidden" id="addrIndex" value="${addr.addrIndex}">
    <div id="pop_wrap">
        <div id="pop_header">
            <h2 class="h2">배송지 등록/수정</h2>
        </div>
        <div id="pop_container">
            <div id="pop_content">
                <div class="setting_popup_title">
                    <h3 class="h_title">배송지 정보 상세</h3>
                </div>
                <table class="tbl_delivery_info">
                    <tr>
	                    <th class="cell_title">배송지명</th>
	                    <td>
                            <span class="basic_input">
                                <input type="text" id="addressName" class="ip_text" value="${addr.addrName}" maxlength="15">
                            </span>
	                    </td>
                    </tr>
                    <tr>
                        <th class="cell_title">수령인<em class="mark_necessity">*</em></th>
                        <td>
                            <span class="basic_input">
                                <input type="text" id="recipient" class="ip_text" value="${addr.addrRecipient}" maxlength="15" placeholder="15자 이내로 입력">
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <th class="cell_title">주소<em class="mark_necessity">*</em></th>
                        <td>
                            <span class="basic_input" style="width: 64px;">
                                <input type="text" id="zipCode" class="ip_text" value="${addr.addrPostcode}" disabled="disabled">
                            </span>    
	                        <button class="btn_addr_search setting_btn sky">주소검색</button>
	                        <p class="address_detail">
	                            <span class="basic_input" style="width: 338px;">
                                    <input type="text" id="baseAddress" class="ip_text" value="${addr.addrRoadaddr}" disabled="disabled">
	                            </span> 
	                        </p>
	                        <p class="address_detail">
                                <span class="basic_input" style="width: 338px;">
                                    <input type="text" id="detailAddress" class="ip_text" value="${addr.addrDetailaddr}" maxlength="100" placeholder="상세 주소를 입력해주세요.">
	                            </span> 
	                        </p>
                        </td>

                    </tr>
                    <tr>
                        <th class="cell_title">연락처<em class="mark_necessity">*</em></th>
                        <td>
                            <span class="basic_input">
                                <input type="text" id="telNum" class="ip_text" value="${addr.addrContact}" maxlength="15" placeholder="'-'를 제외한 숫자만 입력">                               
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <th class="cell_title">기본 배송지</th>
                        <td>
                            <span class="default_delivery">
                                <span class="setting_checkbox">
                                    <input type="checkbox" id="addressYn" class="keep_check"   
                                        <c:if test="${addr.addrYn eq 'Y'}"> checked </c:if>>                                                         
                                </span>
                                <label for="baseAddressYn" class="lb_text">기본 배송지로 설정</label>  
                            </span>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="pop_footer">
            <button type="button" class="btn_pop btn_exit" onclick="javascript:window.close();return false;">닫기</button>
            <button type="button" class="btn_pop btn_save">저장</button>
        </div>
    </div>
</body>
</html>