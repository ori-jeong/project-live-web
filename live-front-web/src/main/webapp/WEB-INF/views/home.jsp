<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://cdn.jsdelivr.net/npm/hls.js@latest"></script>
<script src="https://cdn.jsdelivr.net/npm/hls.min.js.map"></script>
<div id="shop_wrap">
    <div class="shop_content">
        <div class="shop_menulist" role="presentation">
            <div class="shop_tablist" role="tablist" style="transition-timing-function: cubic-bezier(0.1, 0.57, 0.1, 1); transition-duration: 0ms;">
                <a href="/home" class="main_tab" aria-selected="true">홈</a>
                <a href="/shop?category=98" class="main_tab">라이브</a>
                <a href="javascript:void(0);" class="main_tab loc_live" class="main_tab">우리동네라이브</a>
                <a href="/shop?category=101" class="main_tab">패션</a>
                <a href="/shop?category=102" class="main_tab">뷰티</a>
                <a href="/shop?category=103" class="main_tab">푸드</a>
                <a href="/shop?category=104" class="main_tab">라이프</a>
                <a href="/shop?category=105" class="main_tab">취미 · 문화생활</a>
            </div>
        </div>
        <div class="shop_mainPage">
            <section id="HomeBanner">
                <div class="banner_view">
                    <div class="banner_pannel">
                        <div class="HomeBanner_wrap" style="position: absolute; left: 0px;">
                            <a href="#" class="HomeBanner_link link_black" style="background-color: #e9dcf3;">
	                            <div class="HomeBanner_banner">
	                                <span class="HomeBanner_txt">
	                                    <span class="HomeBanner_label">LIVE 예고</span>
	                                    <strong class="HomeBanner_title">스위트홈🧁<br>인기 과자폭탄 선물세트</strong>
	                                    <span class="HomeBanner_date">8월 10일 오후 9시</span>
	                                </span>
	                                <img class="HomeBanner_img" src="/img/banner/live_banner2.png" ></img>
	                            </div>
                            </a>
                        </div>
                    </div>
                </div>
            </section>
            <div id="MainContent">
                <section id="LiveNow" class="main_section">
                    <div class="section_title"><span class="color_point">⚡NOW⚡</span>
                    </div>
<!--                     <div class="SortBox_wrap LiveNow_sord">
                        <a href="javascript:0;" class="SortBox_label">시청순</a>
                        <i class="SortBox_icon"></i>
                    </div> -->
                    <div class="ListBox_wrap">
                        <div class="ListBox_list">
                            <a href="javascript:0;" class="livenow_tab_menu" aria-selected="true">전체</a>
<!--                        <a href="javascript:0;" class="livenow_tab_menu">우리동네라이브</a>     
                            <a href="javascript:0;" class="livenow_tab_menu">패션</a>
                            <a href="javascript:0;" class="livenow_tab_menu">뷰티</a>
                            <a href="javascript:0;" class="livenow_tab_menu">푸드</a>
                            <a href="javascript:0;" class="livenow_tab_menu">라이프</a>
                            <a href="javascript:0;" class="livenow_tab_menu">취미 · 문화생활</a> -->
                        </div>                     
                    </div>
                    <div class="livenow_tab_wrap">
                        <c:if test="${empty live}">
                            <h2 class="live_non_text">
                                현재 라이브 중인 상품이 없습니다.
                            </h2>
                        </c:if>
                        <div class="video_list_wrap">
                            <div class="video_list_scroll" draggable="true">
                              <c:forEach var="live" items="${live}">
                                <div class="video_list_item video_inline">
	                                <a href="/live?fm=${live.liveId}" target="self" class="video_link">
		                                <div class="video_wrap">
		                                   <!-- 라이브 -->
		                                    <div class="video_container">
		                                        <!--  기본 이미지 -->
		                                        <%-- <img class="video_mini_img" src="/img/upload/${live.uploadPath}/${live.fileName}" > --%>
		                                        <div class="video_player" >
		                                        <!-- video poster: 재생 전 보여줄 이미지  -->
		                                          <video class="VideoPlayer_video liveVideo" id="vid" autoplay muted loop poster="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${live.uploadPath}/${live.fileId}">
		                                              
		                                          </video>
		                                        </div>
		                                        <!-- 영상 상단 -->
		                                        <span class="OnAirBadge_wrap">
                                                    <span class="LiveBadge">
                                                        <span class="LiveBadge_txt">LIVE</span>
                                                    </span>
                                                    <span class="OnAirBadge_count">
                                                        <c:choose>
                                                            <c:when test="${live.liveView > 9999}">
                                                                <c:set var="liveView" value="${live.liveView / 10000}"/>
                                                                <fmt:formatNumber value="${liveView}" pattern="#.#" />만 시청
                                                            </c:when>
                                                            <c:otherwise>
                                                                <fmt:formatNumber value="${live.liveView}" pattern="#,###" /> 시청
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </span>
		                                        </span>
		                                        <span class="video_item_wrap">   
		                                        <!-- 영상 하단 --> 
		                                          <span class="video_item_inner">
		                                              <span class="video_item_img">
		                                                  <img class="video_mini_img" src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${live.livePdVo.getPsUploadPath()}/${live.livePdVo.getPsFileId()}" >
		                                              </span>
		                                              <span class="video_item_title">
		                                                  ${live.livePdVo.getPsTitle()}
		                                              </span>
		                                              <span class="video_item_price">
		                                                  <!-- <span class="video_discount">20%</span> -->
		                                                  <span class="video_price">
		                                                      <strong><fmt:formatNumber value="${live.livePdVo.getPsPrice()}" pattern="#,###" /></strong>원
		                                                  </span>
		                                              </span>
		                                          </span>
		                                        </span>
		                                    </div>                                    
		                                </div>
		                                <span class="video_title">${live.liveTitle}</span>
	                                </a>
                                    <a rel="opener" target="_self" href="javascript:0;" class="video_link_creator">
                                        <span class="video_profile">
                                            <img class="video_profile_img" alt="기업명" src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${live.liveSellerVo.getSelUploadPath()}/${live.liveSellerVo.getSelFileId()}" draggable="false">
                                        </span>${live.liveSellerVo.getSelName()}
                                    </a>
                                </div>
                              </c:forEach>
                              <%-- <c:if test="${!empty live}">
                                <div class="video_list_more">
                                    <a href="/shop/live" class="video_list_more_link">
                                        <div class="list_more_IconBox">
                                            <span class="list_more_icon">-></span>
                                            <span class="list_more_txt">더보기</span>
                                        </div>
                                    </a>
                                </div>
                               </c:if> --%>
                            </div>
                        </div>
                    </div>
                </section>
                <section id="liveTrailer" class="main_section">
                    <div class="section_title">📆라이브 예고</div>
                    <!-- <a href="#" class="section_link">더보기
                        <i class="section_link_icon"></i>
                    </a> -->
                    <ul class="LiveList_list LiveList_pc_twoColumn">
                        <c:forEach var="trailer" items="${trailer}">
                        <li class="LiveList_item" data-id>
                            <div class="LiveList_itembox">
                                <div class="LiveList_inner">
                                    <a class="LiveItem_wrap" target="_self" href="javascript:void(0);" >
                                        <div class="LiveItem_airtime">
                                            <time datetime="">
                                                
                                                <span class="LiveItem_day">
	                                                <fmt:parseDate value="${trailer.liveStartDay}" var="startDate" pattern="MM-dd" />
	                                                <fmt:formatDate value="${startDate}" pattern="MM월dd일" />
                                                </span>
                                                <span class="LiveItem_time">${trailer.liveStartTime}</span>
                                            </time>
                                        </div>
                                        <div class="LiveItem_thumbnail">
                                            <div class="video_container">
                                                <img class="video_container_img" src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${trailer.uploadPath}/${trailer.fileId}" draggable="false">
                                            </div>
                                        </div>
                                        <div>
                                            <div class="LiveContent_title">${trailer.liveTitle}</div>
                                            <!-- <div class="LiveContent_additional">라이브 방송 중 사은품 증정 이벤트 </div> -->
                                            <div class="LiveContent_product">
                                                <div class="LiveContent_thumbnail">
                                                    <img class="LiveContent_img" src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${trailer.livePdVo.getPsUploadPath()}/${trailer.livePdVo.getPsFileId()}" draggable="false">
                                                </div>
                                                <div class="LiveContent_info">
                                                    <span class="LiveContent_product_name">${trailer.livePdVo.getPsTitle()}</span>
                                                    <div class="LiveContent_price">
                                                        <strong>
                                                            <!-- <span class="LiveContent_discount">91%</span>  -->
                                                            <fmt:formatNumber value="${trailer.livePdVo.getPsPrice()}" pattern="#,###" />원
                                                        </strong>
                                                    </div>
                                                </div>
                                                
                                            </div>
                                            <span class="LiveContent_seller">${trailer.liveSellerVo.getSelName()}</span>
                                       </div>
                                    </a>
                                </div>
                            </div>
                        </li>
                       </c:forEach>
                    </ul>
                </section>
                <section id="TopTen" class="main_section">
                    <div class="section_title">TOP 10✨<!-- <span class="Guide_icon">i</span> --></div>
                    <div class="TopTen_tab_wrap">
                        <div class="video_list_wrap video_inline">
                            <div class="video_list_scroll toptenscroll" draggable="true">
                               <c:set var="num" value="1"/>
                               <c:forEach var="top" items="${topten}">
                                <div class="video_list_item video_list_wrap">
                                    <a href="/replay?fm=${top.liveId}" target="self" class="video_link">
                                        <div class="video_wrap">
                                           <!-- 라이브 -->
                                            <div class="video_container">
                                                <!--  기본 이미지 -->
                                                <%-- <img class="video_container_img videoload" draggable="false" src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${top.uploadPath}/${top.fileId}"> --%>
                                                <div class="video_player" >
                                                <!-- video poster: 재생 전 보여줄 이미지  -->
                                                  <video class="VideoPlayer_video" id="vid" autoplay muted loop 
                                                        poster="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${top.uploadPath}/${top.fileId}">
                                                        <source src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${top.videoPath}${top.videoName}" type="video/mp4">
                                                          <!-- <source src="/img/video_mp4/omg_short.mp4" type="video/mp4"> -->
                                                  </video>
                                                </div>
                                                <!-- 영상 상단 -->
                                                <strong class="video_ranking">${num}</strong>
                                                <span class="video_viewer video_viewer_type_ranking">
                                                    <span class="OnAirBadge_count">
                                                        <c:choose>
                                                            <c:when test="${top.liveView > 9999}">
                                                                <c:set var="view" value="${top.liveView / 10000}"/>
                                                                <fmt:formatNumber value="${view}" pattern="#.#" />만 시청
                                                            </c:when>
                                                            <c:otherwise>
                                                                <fmt:formatNumber value="${top.liveView}" pattern="#,###" /> 시청
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </span>
                                                </span>
                                                <span class="video_item_wrap">   
                                                <!-- 영상 하단 --> 
                                                  <span class="video_item_inner">
                                                      <span class="video_item_img">
                                                          <img class="video_mini_img" src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${top.livePdVo.getPsUploadPath()}/${top.livePdVo.getPsFileId()}" >
                                                      </span>
                                                      <span class="video_item_title">
                                                          ${top.livePdVo.getPsTitle()}
                                                      </span>
                                                      <span class="video_item_price">
                                                          <!-- <span class="video_discount">20%</span> -->
                                                          <span class="video_price"><strong><fmt:formatNumber value="${top.livePdVo.getPsPrice()}" pattern="#,###" /></strong>원</span>
                                                      </span>
                                                  </span>
                                                </span>
                                            </div>                                    
                                        </div>
                                        <span class="video_title">${top.liveTitle}</span>
                                    </a>
                                    <a rel="opener" target="_self" href="javascript:0;" class="video_link_creator">
                                        <span class="video_profile">
                                            <img class="video_profile_img" alt="기업명" src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${top.liveSellerVo.getSelUploadPath()}/${top.liveSellerVo.getSelFileId()}" draggable="false">
                                        </span>${top.liveSellerVo.getSelName()}
                                    </a>
                                </div>
                                <c:set var="num" value="${num+1}" />
                               </c:forEach>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
</div>

<script>
//라이브 연결
var hls = new Hls();
<c:forEach var="live" items="${live}" varStatus='st'>
   <c:if test="${live.liveStatus eq 1}">
     var video = $('.liveVideo')[${st.index}];
     var stream = "${liveUrl}/${live.liveSellerVo.getSelStreamKey()}/index.m3u8"
     if(video.canPlayType('application/vnd.apple.mpegurl')) {   // 우선 HLS를 지원하는지 체크
         video.src = stream;
     }else if(Hls.isSupported()){  // HLS를 지원하지 않는다면 hls.js를 지원
         hls.loadSource(stream);
         hls.attachMedia(video);
         hls.on(Hls.Events.MANIFEST_PARSED,()=>{
             video.play(); //라이브 시작
         })
         hls.on(Hls.Events.ERROR, function(data) {
        	 video.src="";
         });
     }
  </c:if>
</c:forEach>
</script>