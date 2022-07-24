let item_id,item_price;
var deliverys = [];
var products = [];
var isVal =false;
    var startX,move,pageX,newX;
    var resultX=0;
    var windowWidth =0;
    var tagWidth=0;
    let thisClass;
$(document).ready(function() {    

    banner_resize();
    
    //창크기를 감지해 banner 높이 줄이기
    $( window ).resize(function() {
        windowWidth = $( window ).width();      //브라우저 넓이
        //메뉴 스크롤 너비 조절
        if(resultX!=0 && resultX<=0){
            resultX=resultX+3;
           $(".shop_tablist").css('transform','translate('+resultX+'px, 0px) translateZ(0px)') 
        }
         banner_resize();
    });
    function banner_resize(){
        windowWidth = $( window ).width();
        //banner 높이 조절
        if(windowWidth<=770){
            var height=Math.ceil(windowWidth*57.3/100);
            if(height<=430){
               $('.banner_view').css("height",(windowWidth-height));
            }
        }   
    }
    $('.loc_live').click(function(){      
        //위치 정보 가져와 저장
        if(navigator.geolocation) { // HTML5의 geolocation으로 사용할 수 있는지 확인
            navigator.geolocation.getCurrentPosition(position => {  // GeoLocation을 이용해서 접속 위치 가져오기
              const lat = position.coords.latitude;   //위도
              const lon = position.coords.longitude;  //경도
              getLocation(lat, lon);
            }, error => { //위치 정보 허용 X= 기본값: 서울
            getLocation(37.56826,126.977829);
                //getLocation(36.337587,127.392517);
            })
        } else { //사용 불가 브라우저= 기본값: 서울
            getLocation(37.56826,126.977829);
        }
    })
    function getLocation(lon,lat){ 
        var geocoder = new kakao.maps.services.Geocoder();
        var coord = new kakao.maps.LatLng(lon, lat);    //경도,위도 순서로 넣을 것!
        var callback = function(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                console.log(result[0].address);                            //주소 전체
                console.log(result[0].address.region_1depth_name);         //서울
                var lct=result[0].address.region_1depth_name;
                location.href="/shop?category=99&location="+lct;
            }
        }
        geocoder.coord2Address(coord.getLng(), coord.getLat(), callback)
    }
    /* 메뉴 횡스크롤 애니메이션*/
/*    $(".shop_tablist").mousedown(function(e){
      //mousedown : 마우스를 클릭하면 발행, 클릭 중인 상태
        windowWidth = $( window ).width();
        tagWidth = $(".shop_tablist").width(); 
        if(windowWidth<tagWidth){
            e.preventDefault();
            move = true;
            pageX = e.pageX;
            if(startX==undefined){
                startX=0;
            }
        }
    });
    $(".shop_menulist").mousemove(function(e){
        //mousemove: 마우스를 움직였을 때 발행, 움직이는 상태
        if(move){
            newX = e.pageX; //현재 스크롤의 x 위치
            $(".shop_tablist").scrollLeft(startX-pageX+newX);
            resultX=startX-pageX+newX;
            windowWidth = $( window ).width(); //브라우저 넓이
            tagWidth = $(".shop_tablist").width();     
            var maxX=windowWidth-tagWidth;
            if(resultX>=0){resultX =0; } // 더 이상 앞으로 이동 금지
            if(resultX<=maxX){resultX=maxX; } // 더 이상 뒤로 이동 금지
            $(".shop_tablist").animate({
                '-webkit-transform':'translate('+resultX+'px, 0px) translateZ(0px)'});
            $(".shop_tablist").css('transform','translate('+resultX+'px, 0px) translateZ(0px)')

        }
    });*/
    $("html").mouseup(function(e){
        //mouseup : 마우스 버튼을 떼었을 때 발생
        //startX=resultX; //스크롤이 끝난 지점을 시작지점으로 바꿔준다
        //move = false;   //움직임 멈추기
        down = false;
        mdown = false;
        ndown = false;
        e.stopPropagation();
    });
    /*메뉴 횡이동 애니메이션*/
    var mx,mleft,mdown;
    $(".shop_menulist").mousedown(function(e){
      e.preventDefault();
      mdown = true;
      mx = e.pageX;
      mleft = $(this).scrollLeft();
    });
    
    $(".shop_menulist").mousemove(function(e){
      if(mdown){
        var newX = e.pageX;
        $(".shop_menulist").scrollLeft(mleft - newX + mx);
      }
    });
    /*top10 횡이동 애니메이션*/
    var nx,nleft,ndown;
    $(".livenow_tab_wrap").mousedown(function(e){
      e.preventDefault();
      ndown = true;
      x = e.pageX;
      nleft = $(this).scrollLeft();
    });
    
    $(".livenow_tab_wrap").mousemove(function(e){
      if(ndown){
        var newX = e.pageX;
        $(".livenow_tab_wrap").scrollLeft(nleft - newX + x);
      }
    });
    /*top10 횡이동 애니메이션*/
    var x,left,down;
    $(".TopTen_tab_wrap").mousedown(function(e){
      e.preventDefault();
      down = true;
      x = e.pageX;
      left = $(this).scrollLeft();
    });
    
    $(".TopTen_tab_wrap").mousemove(function(e){
      if(down){
        var newX = e.pageX;
        $(".TopTen_tab_wrap").scrollLeft(left - newX + x);
      }
    });


    //document.getElementById('vid').play();  
    //검색창 숨기기
    var search_input = $('.header_search_wrap');
    var search_icon =$('.search_a');
    var innerWidth = window.innerWidth;
    window.onresize = function(){
        //화면크기 변할 때 실행
        innerWidth = window.innerWidth;
        innerWidth <= "768" ? search_input.hide() : search_input.show()
        innerWidth >= "768" ? search_icon.hide() : search_icon.show()
    }
    //첫 입장시 실행
    innerWidth <= "768" ? search_input.hide() : search_input.show()
    innerWidth >= "768" ? search_icon.hide() : search_icon.show()
    
    //로그아웃
    $(".h_btn_logout").click(function(){
        if(confirm("로그아웃 하시겠습니까?")==true){
            $("#logoutForm").submit();
        }
    });
    //툴페이지로 이동
    $('.btn_tool_move').click(function(){
        location.href='/tool/live';
    })
        //툴페이지로 이동
    $('.btn_seller_insert').click(function(){
        location.href='/seller_insert';
    })
});
    //검색
    function search(){
        location.href = "/search?query="+ $('.searchInput').val();
    }
    //