<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://cdn.jsdelivr.net/npm/hls.js@latest"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script> 
<div id="shop_wrap">
    <div class="shop_content">
        <div class="shop_menulist" role="presentation">
            <div class="shop_tablist" role="tablist" style="transition-timing-function: cubic-bezier(0.1, 0.57, 0.1, 1); transition-duration: 0ms;">
                <a href="/" class="main_tab black_tab">검색결과</a>
            </div>
        </div>
        <div class="menu_tabpanel">
            <div class="infiniteScroll_wrap">
                <c:if test="${empty live}">
                    <h2 class="search_non_text">
                        검색 결과가 없습니다.
                    </h2>
                </c:if>
                <c:forEach var="live" items="${live}">
                <div class="videoCard_wrap videoVerticalList_item">
                    <c:choose>
                        <c:when test="${live.liveStatus eq 1}">
                            <a href="/live?fm=${live.liveId}" target="self" class="video_link">
                        </c:when>
                        <c:otherwise>
                            <a href="/replay?fm=${live.liveId}" target="self" class="video_link">
                        </c:otherwise>
                    </c:choose>
                    <div class="video_wrap">
                       <!-- 라이브 -->
                        <div class="video_container">
                            <!--  기본 이미지 -->
                            <img class="video_container_img videoload" draggable="false" src="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${live.uploadPath}/${live.fileId}">
                            <div class="video_player" >
                            <!-- video poster: 재생 전 보여줄 이미지  -->
                                <c:choose>
                                    <c:when test="${live.liveStatus eq 1}">
			                          <video class="VideoPlayer_video liveVideo"  autoplay muted loop poster="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${live.uploadPath}/${live.fileId}">
			                            
			                          </video>
			                        </c:when>
			                        <c:otherwise>
			                          <video class="VideoPlayer_video replayVideo" autoplay muted loop poster="https://s3.ap-northeast-2.amazonaws.com/onlshop.shop${live.uploadPath}/${live.fileId}">
			                           <source src="/img/upload/${live.videoPath}/${live.videoName}" type="video/mp4">
			                          </video>  
			                        </c:otherwise>
			                    </c:choose>
                            </div>
                            <!-- 영상 상단 -->
                            <span class="OnAirBadge_wrap">
 <%--                               <jsp:useBean id="now" class="java.util.Date" />
                                  <fmt:formatDate var="todayFormat" value="${now}" pattern="yyyy.MM.dd HH:mm" />
                                  <c:set var="liveStartDate" value="${live.liveStartDay} ${live.liveStartTime}" />
                                  <c:set var="liveEndDate" value="${live.liveStartDay} ${live.liveEndTime}" />  --%> 
                               <c:choose>
                                      <c:when test = "${live.liveStatus eq 1}">
                                       <span class="LiveBadge">
                                           <span class="LiveBadge_txt">LIVE</span>
                                       </span>    
                                      </c:when>
                                      <c:otherwise>
                                          <span class="video_viewer_type_ranking viewer-icon_span">
                                             <span class="video_viewer-icon"></span>
                                          </span>
                                      </c:otherwise>
                                  </c:choose>
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
                                      <strong><fmt:formatNumber value="${live.livePdVo.getPsPrice()}" pattern="#,###" /></strong>원
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
            </div>
        </div>
    </div>
</div>
<script>
//라이브 연결
<c:forEach var="live" items="${live}" varStatus='st'>
   <c:if test="${live.liveStatus eq 1}">
     var hls${st.index} = new Hls();
     var video${st.index} = $('.liveVideo')[${st.index}];
     var stream${st.index} = "${liveUrl}/${live.liveSellerVo.getSelStreamKey()}/index.m3u8"
     if(video${st.index}.canPlayType('application/vnd.apple.mpegurl')) {   // 우선 HLS를 지원하는지 체크
         video${st.index}.src = stream${st.index};
     }else if(Hls.isSupported()){  // HLS를 지원하지 않는다면 hls.js를 지원
         hls${st.index}.loadSource(stream${st.index});
         hls${st.index}.attachMedia(video${st.index});
         hls${st.index}.on(Hls.Events.MANIFEST_PARSED,()=>{
             video${st.index}.play(); //라이브 시작
         })
         hls${st.index}.on(Hls.Events.ERROR, function(data) {
             video${st.index}.src="";
         });
     }
  </c:if>
</c:forEach>
</script>