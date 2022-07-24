<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="admin_wrap">
    <div class="admin_content">
         <div class="admin_menulist">
            <div class="admin_tablist">
                <a href="/tool/live"      class="main_tab" aria-selected="true">라이브 관리</a>
                <a href="/tool/products"  class="main_tab" aria-selected="false">상품 관리</a>
                <a href="/tool/pdpost" class="main_tab" aria-selected="false">판매글 관리</a>
                <a href="/tool/sale"     class="main_tab" aria-selected="false">판매 내역 관리</a>
                <a href="/tool/setting"   class="main_tab" aria-selected="false">설정</a>
            </div>
        </div>
        <div class="product_create_wrap">
            <div class="product_create_area">
                <div class="product_create_inner">
                    <div class="_title">
                        <c:choose>
                            <c:when test="${empty live}">
                                <h1>라이브 등록</h1>
                            </c:when>
                            <c:otherwise>
                                <h1>라이브 수정</h1>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="product_create_content">
                        <div class="create_layout">
                            <div class="layout_subject _asterisk">라이브 제목</div>
                            <input type="text" id="liveTitle" class="layout_input" value="${live.liveTitle}" maxlength='30' >                       
                        </div>
                        <div class="create_layout">
                            <div class="layout_subject _asterisk">라이브 카테고리</div>
                            <div class="layout_filter">
                                <select name="liveCate" id="liveCate" class="_filter">
                                    <option value="non">카테고리 선택</option>
                                    <c:forEach items="${category}" var="cate">
	                                    <option value="${cate.CATE_INDEX}" 
	                                       <c:if test="${cate.CATE_INDEX eq live.cateIndex}"> selected="selected" </c:if>>
	                                       ${cate.CATE_NAME}</option>
	                                </c:forEach>
                                </select>
                             </div>                       
                        </div>
                        <div class="create_layout">
                            <div class="layout_subject _asterisk">라이브 메인 이미지</div>
                            <div class="layout_live_upload">
                                <span class="live_img_upload">
                                    <c:choose>
                                        <c:when test="${empty live}">
                                            <img src="" class="title_img">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${live.uploadPath}/${live.fileId}" class="title_img">
                                        </c:otherwise>
                                    </c:choose>
                                    
                                    <label for="" class="btn_img">이미지</label> 
                                </span>                                
                                <span class="live_img_text">
                                    <label for="fileName" class="btn_up">업로드</label> 
                                    <input type="file" id="fileName" class="layout_input_file btn_title_img" multiple> 
                                    <p class="p_red">- 최대 용량 : 20MB</p>
                                    <p class="p_red">- 권장 크기 : 720 X 1280</p>
                                    <p class="p_gray">- 라이브의 대표 이미지를 등록해주세요.</p>
                                    <p class="p_gray">- 글자가 포함되지 않은 이미지를 사용해주세요.</p>
                                </span>
                            </div>                    
                        </div>
                        <div class="create_layout">
                            <div class="layout_subject _asterisk">라이브 예정 일시</div>
                            <div>
                                <jsp:useBean id="today" class="java.util.Date" />
                                <input type="date" id="live_day" class="input_date" 
                                    <c:choose>
                                        <c:when test="${empty live}">
                                            value="<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" />"
                                        </c:when>
                                        <c:otherwise>
                                            value="${live.liveStartDay}"
                                        </c:otherwise>
                                    </c:choose> >                                                                      
                                <input type="time" id="live_startTime" class="input_date" min="7" max="24"
                                    <c:choose>
                                        <c:when test="${empty live}">
                                            value="<fmt:formatDate value="${today}" pattern="HH:mm" />"
                                        </c:when>
                                        <c:otherwise>
                                            value="${live.liveStartTime}"
                                        </c:otherwise>
                                    </c:choose> >
                                <span style="font-size: 24px;padding: 0 10px;">~</span>
                                <input type="time" id="live_endTime" class="input_date" min="7" max="24"
                                    <c:choose>
                                        <c:when test="${empty live}">
                                            value="<fmt:formatDate value="${today}" pattern="HH:mm" />"
                                        </c:when>
                                        <c:otherwise>
                                            value="${live.liveEndTime}"
                                        </c:otherwise>
                                    </c:choose> >
                            </div>
                        </div>
                        <div class="create_layout">
                            <div class="layout_subject"><span>[우리동네라이브]<span> 지역 설정</div>
                            <div class="layout_filter">
                                <select name="region1Code" id="region1Code" class="_filter">
                                    <option value="0">지역 선택</option>
                                    <c:forEach items="${region}" var="region">
                                        <option value="${region.REGION_1_CODE}" <c:if test="${region.REGION_1_CODE eq live.region1Code}"> selected="selected" </c:if>>${region.REGION_1_NAME}</option>
                                    </c:forEach>
                                </select>
                             </div>
                        </div>
                        <div class="create_layout">
                            <div class="layout_subject _asterisk">라이브에 소개할 상품</div>
                            <div class="layout_filter">
                                <select name="psPostSel" id="psPostSel" class="_filter">
                                    <option value="non">판매글 선택</option>
                                    <c:forEach items="${pdPostList}" var="post">
                                        <option value="${post.psIndex}" 
                                           <c:if test="${post.psIndex eq live.psIndex}"> selected="selected" </c:if>>
                                           ${post.psTitle}</option>
                                    </c:forEach>
                                </select>
                             </div>
                        </div>
                        <div class="create_layout">
                        <c:choose>
                            <c:when test="${empty live}">
                                <button class="btn_live_create btn_pp">라이브 등록하기</button>
                            </c:when>
                            <c:otherwise>
                                <button class="btn_live_update btn_pp">라이브 수정하기</button>
                            </c:otherwise>
                        </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="live_id" value="${live.liveId}">
    <input type="hidden" id="file_id" value="${live.fileId}">
</div>
