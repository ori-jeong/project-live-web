<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<div id="admin_wrap">
    <div class="admin_content">
        <div class="admin_menulist">
            <div class="admin_tablist">
                <a href="/tool/live"      class="main_tab" aria-selected="false">라이브 관리</a>
                <a href="/tool/products"  class="main_tab" aria-selected="false">상품 관리</a>
                <a href="/tool/pdpost" class="main_tab" aria-selected="false">판매글 관리</a>
                <a href="/tool/sale"     class="main_tab" aria-selected="false">판매 내역 관리</a>
                <a href="/tool/setting"   class="main_tab" aria-selected="true">설정</a>
            </div>
        </div>
        <div class="setting_wrap">
            <div class="setting_area">
                <div class="setting_content">
                    <div class="setting_part">
                        <div class="_title">
                            <h1>프로필</h1>
                        </div>
                        <div class="setting_layout flex">
                            <div class="setting_layout_part_1">
                                <span class="profile_box">
                                    <img class="profile_img"
                                        src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${seller.uploadPath}/${seller.fileId}">
                                     <label for="profileImg" class="btn_changeImg">+</label>
                                     <input type="file" id="profileImg" class="layout_input_file">
                                     <input type="hidden" id="uploadId" value="${seller.fileId}">
                                </span>
                               
                            </div>
                            <div class="setting_layout_part_2">
                                <div class="layout_subject">스토어 명</div>
                                <input type="text" id="selName" class="layout_input" value="${seller.selName}">
                                <button type="button" class="btn_changeInfo btn_blue">회원정보 수정</button>
                            </div>
                        </div>   
                    </div>
                    <div class="setting_part">
		                <div class="_title">
			                <h1>방송 암호키 설정</h1>
			            </div>
			            <div class="setting_layout">
			                <div class="layout_subject">방송 암호 키</div>
			                <input type="password" id="stream" class="layout_input" value="${streamkey}">
			                
			                <div class="setting_layout_botton">
	                            <button class="btn_input_type">표시</button>
	                            <span class="setting_layout_btn_area">
		                            <button type="button" class="btn_copy _btn btn_blue">복사</button>
		                            <button type="button" class="btn_clean _btn">초기화</button>                               
	                            </span>
			                </div>
			            </div>  
		            </div>
	            </div>        
            </div>
        </div>
    </div>
</div>