<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="admin_wrap">
    <div class="admin_content">
         <div class="admin_menulist">
            <div class="admin_tablist">
                <a href="/tool/live"      class="main_tab" aria-selected="false">라이브 관리</a>
                <a href="/tool/products"  class="main_tab" aria-selected="false">상품 관리</a>
                <a href="/tool/pdpost" class="main_tab" aria-selected="true">판매글 관리</a>
                <a href="/tool/sale"     class="main_tab" aria-selected="false">판매 내역 관리</a>
                <a href="/tool/setting"   class="main_tab" aria-selected="false">설정</a>
            </div>
        </div>
        <div class="product_create_wrap">
            <div class="product_create_area">
                <div class="product_create_inner">
                    <div class="_title">
                        <c:choose>
                            <c:when test="${empty postInfo}">
                                <h1>판매글 등록</h1>
                            </c:when>
                            <c:otherwise>
                                <h1>판매글 수정</h1>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="product_create_content">
 <%--                        <div class="create_layout">
                            <div class="layout_subject _asterisk">판매글 카테고리</div>
                            <div class="layout_filter">
                                <select name="psCate" id="psCate" class="_filter">
                                    <option value="non">카테고리 선택</option>
                                    <c:forEach items="${category}" var="cate">
	                                    <option value="${cate.CATE_INDEX}" 
	                                       <c:if test="${cate.CATE_INDEX eq postInfo.psCate}"> selected="selected" </c:if>>
	                                       ${cate.CATE_NAME}</option>
	                                </c:forEach>
                                </select>
                             </div>                       
                        </div> --%>
                        <div class="create_layout">
                            <div class="layout_subject _asterisk">판매글 제목</div>
                            <input type="text" id="psTitle" class="layout_input" value="${postInfo.psTitle}" maxlength='30' >                       
                        </div>
                        <div class="create_layout filebox">
                            <div class="layout_subject _asterisk">판매글 메인 이미지</div>
                            <div class="layout_memo">5MB 크기의 정사각형 이미지를 올려주세요.</div>
                            <input class="uploadName"  disabled="disabled" 
                               <c:choose>
                                    <c:when test="${!empty postInfo}">
                                        value='${postInfo.pdPostFileVo.getFileName()}'
                                    </c:when>
                                    <c:otherwise>
                                        value="파일선택"
                                    </c:otherwise>
                                </c:choose> >
				            <label for="fileName">업로드</label> 
                            <input type="file" id="fileName" class="layout_input_file btn_post_img" multiple>                       
                        </div>
                        <div class="create_layout">
                            <div class="layout_subject _asterisk">게시물 판매가(최소 금액)</div>
                            <input type="text" id="psPrice" class="layout_input" value="${postInfo.psPrice}" maxlength='11'
                                oninput="this.value = this.value.replace(/[^0-9.,]/g, '').replace(/(\..*)\./g, '$1');">                       
                        </div>
                        <div class="create_layout">
                            <div class="layout_subject _asterisk">판매 상품 추가</div>
                            <div class="layout_memo">상품 체크/체크 해제 후 등록하면 상품이 새로 등록됩니다.</div>
                            <div>
	                             <div class="pdAddList_area">
	                                <div class="pdAddList_header">
	                                    <div class="pdAddList_h_viewport">
	                                        <div class="pdAddList_h_check">
	                                            <input type="checkbox" class="isChek">
	                                        </div>
	                                        <div class="pdAddList_cell_1">상품명</div>
	                                        <div class="pdAddList_cell_2">상품금액</div>
	                                    </div>
	                                </div>
	                                <div class="pdAddList_content">
	                                    <c:forEach items="${pdList}" var="pd">
	                                        <div class="pdAddList_content_area">
	                                            <div class="pdAddList_h_check">
	                                                <div>
	                                                <input type="checkbox" id="addPdVal" class="iChek" value="${pd.pdId}"
	                                                   <c:forEach items="${postInfo.pdPostAddVo}" var="post">
	                                                   <c:if test="${post.pdId eq pd.pdId}"> checked </c:if>
	                                                   </c:forEach>>
	                                                </div>
	                                            </div>
	                                            <div class="pdAddList_cell_1">${pd.pdName} ${post.pdId}</div>
	                                            <div class="pdAddList_cell_2"><fmt:formatNumber value="${pd.pdPrice}" pattern="#,###" /></div>
	                                        </div>
	                                        
	                                    </c:forEach>
	                                </div>
	                             </div>
                            </div>
                       
                        </div>
                        <div class="create_layout">
                            <div class="layout_subject _asterisk">배송 방법</div>
                            <div class="layout_filter">
                                <select name="psDeliverySel" id="psDeliverySel" class="_filter">
                                    <option value="non">배송방법</option>
                                    <option value="0" <c:if test="${postInfo.psDeliveryOpt eq 0}">selected="selected"</c:if>>무료 배송</option>
                                    <option value="1" <c:if test="${postInfo.psDeliveryOpt eq 1}">selected="selected"</c:if>>일반 배송</option>
                                </select>
                             </div>
                           <input type="text" id="psDelivery" class="layout_input" placeholder="택배비 입력"  maxlength='10' 
                                <c:choose>
                                    <c:when test="${postInfo.psDeliveryOpt eq 1}">
                                       value='${postInfo.psDelivery}' disabled 
                                    </c:when>
                                    <c:otherwise>
                                        value="" disabled 
                                    </c:otherwise>
                                </c:choose>                          
                            oninput="this.value = this.value.replace(/[^0-9.,]/g, '').replace(/(\..*)\./g, '$1');"  >                        
                        </div>
                        <div class="create_layout">
                            <div class="layout_subject _asterisk">판매글 상세 내용</div>
	                        <textarea id="psContentText" name="psContentText">${postInfo.psContent}</textarea>
	                        <iframe id="psContentIframe" name="psContentIframe" scrolling=yes border=0 width="100%" height=300 frameborder=0></iframe>                    
                        </div>
                        <div class="create_layout">
                            <div class="layout_subject _asterisk">판매 상태</div>
                            <select name="pdPostOnOff" id="pdPostOnOff" class="_filter">
                                <option value="non">판매 상태</option>
                                <option value="on" <c:if test="${postInfo.psPostStatus eq 'on'}"> selected="selected"</c:if>>판매중</option>
                                <option value="off" <c:if test="${postInfo.psPostStatus eq 'off'}"> selected="selected"</c:if>>판매중지</option>
                            </select>                    
                        </div>
                        <div class="create_layout">
                        <c:choose>
                            <c:when test="${empty postInfo}">
                                <button class="btn_post_create btn_pp">판매글 등록하기</button>
                            </c:when>
                            <c:otherwise>
                                <button class="btn_post_update btn_pp">판매글 수정하기</button>
                            </c:otherwise>
                        </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="post_id" value="${postInfo.psIndex}">
    <input type="hidden" id="file_id" value="${postInfo.fileId}">
</div>
<script>
    var ifrContent = $('#psContentText').text();
	//iframe 디자인모드
	var iframe= document.getElementById("psContentIframe").contentWindow;
	//var lodeContent=document.getElementById("psContentIframe").innerHTML;
	var iframeD = iframe.document;  
	if(ifrContent != ''){
		iframeD.write("<!DOCTYPE html><html><body>"+ifrContent+"</body></html>");
	}
	iframeD.designMode = 'on';
	//iframe.focus(); 
</script>