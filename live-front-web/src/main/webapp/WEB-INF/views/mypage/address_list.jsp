<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"    prefix="c" %>
<div id="userInfo_wrap" class="mypage_wrap">
    <div id="userInfo_area" class="mypage_area">
        <div class="mypage_title">
            <h1>배송지 관리</h1>
        </div>
        <div class="mypage_content">
            <div class="mypage_group">
                <div class="addr_btn_area">
                    <button type="button" class="btn_addr_insert" id="addr_insert">배송지 등록</button>
                </div>
                <c:choose>
                    <c:when test ="${empty addr}">
                        <div class="non_addr">배송지를 등록해주세요.</div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${addr}" var ="addr">                         
                          <ul class="address_card" id="ul">
                            <li class="address_card_head">
                              <div>
                                <span class="addr_name">${addr.addrName}</span>
                                <span class="addr_recipient">${addr.addrRecipient}</span>
                              </div>
                              <c:if test="${addr.addrYn =='Y' }">
                                  <div>
                                      <span class="address_marker">기본 배송지</span>
                                  </div>
                              </c:if>
                            </li>
                            <li class="address_card_body mt_5" >
                                <div class="zipCode" style="color:#999">${addr.addrPostcode}</div>
                                <div class="baseAddress">${addr.addrRoadaddr}</div>
                                <div class="detailAddress">${addr.addrDetailaddr}</div>
                                <div class="telNum mt_5">${addr.addrContact}</div>                              
                            </li>
                            <li class="address_card_foot mt_5">
                                <input type="hidden" id="hash" value="${addr.addrIndex}">
                                <button type="button" class="btn_pop addrUpdate" >수정</button>
                                <button type="button" class="btn_pop addrDelete" >삭제</button>
                            </li>
                        </ul>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
    </div>
</div>