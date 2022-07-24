var pdName, pdPrice, pdStrock, pdStatus
var psTitle, upload, psPrice, psDeliverySel, psDelivery, psContent,psPostStatus;
var textarea;
var isVal = true;
var pdStatus='';
var uploadFileId = '';
let regex = new RegExp("(.)\.(jpg|png|JPG|PNG|image/png)$");
var maxSize = 5242880; //5MB
var maxSize20 = 20971520; //5MB
let formData = new FormData();
$(document).ready(function() {
    //로그아웃
    $(".h_btn_logout").click(function(){
        if(confirm("로그아웃 하시겠습니까?")==true){
            $("#logoutForm").submit();
        }
    });

    /* 공통 기능*/
    //미입력 css 적용된 class 삭제
    $('.layout_input').on('keyup',function(){
         $(this).removeClass('red_act');
    });
    $('._filter').click(function(){
         $(this).removeClass('red_act');
    });
    $('#psContentIframe').contents().find('body').on('keyup',function(){
         $('#psContentIframe').removeClass('red_act');
    });
    //가로 스크롤 마우스 휠에 적용
    $(".admin_list_body").on('mousewheel',function(e){       
        var wheelDelta = e.originalEvent.wheelDelta;        
        if(wheelDelta > 0){         
            //console.log("up");         
             $(this).scrollLeft(-wheelDelta + $(this).scrollLeft());     
        }else{      
            //console.log("down");            
            $(this).scrollLeft(-wheelDelta + $(this).scrollLeft());     
        }
    });
        var x,left,down;
    $(".admin_menulist").mousedown(function(e){
      e.preventDefault();
      down = true;
      x = e.pageX;
      left = $(this).scrollLeft();
    });
    
    $(".admin_menulist").mousemove(function(e){
      if(down){
        var newX = e.pageX;
        $(".admin_menulist").scrollLeft(left - newX + x);
      }
    });
/*   var wideFlag = false; // 마우스 누르고 있을 때만 
    $('.ag-header-cell-resize').on("mousedown", function(e){
        
      wideFlag = true;
      console.log("1 :"+wideFlag);
    });
    $(".cell_size").on('mousemove', function (e) {
        var width =  $(this).outerWidth();
        console.log(width);
        console.log(e.clientX);
        var a = (width-e.clientX);
        console.log("a : "+a);
        var sizeClass = $(this).attr('class').split('cell_size').join('').trim();
      if (wideFlag == true && a > 50 &&a < 180) { // 최소, 최대 영역 지정
        $('.'+sizeClass).css('width', a);
      };
    }).on('mouseup', function (e) {
      wideFlag = false;
    });*/
    //$('.ag-header-cell-resize').unbind("mousedown");
    //$(".sale_cell").unbind("mousemove");
    //$(".sale_cell").unbind("mouseup");


    /* 설정 페이지*/
    //프로필 사진 등록
    let fileId='';
    $('#profileImg').on('change', function(){
        var files =$("input[type='file']")[0].files; 
        var filename;
        if(files[0].size >= maxSize){
            alert("파일 사이즈 초과");
            return false;
        }else if(regex.test(files[0].name)==false){
            alert("해당 종류의 파일은 업로드할 수 없습니다.");
            return false;
        }else{
            if(window.FileReader){
               filename = files[0].name;
            } else {
               filename = files[0].val().split('/').pop().split('\\').pop();
            }
            formData.set('file',files[0]);
        }   
        
        var option = new Object();
        option.data = formData
        option.success = function(v){
            uploadFileId = v.data.fileId;
            var path= v.data.uploadPath;
            $('.profile_img').attr("src","https://s3.ap-northeast-2.amazonaws.com/onlshop.shop"+path+"/"+uploadFileId);
        }
        _ajax.ajaxMultipart("/uploadFileAws","POST",option); 
    })
    //판매자 정보 수정
    $('.btn_changeInfo').click(function(){
        var selName = $('#selName').val();
        var option = new Object();
        option.data = {'selName':selName,'fileId':uploadFileId}
        option.success = function(v){
            location.reload();
        }
       _ajax.ajaxAlert("/tool/setting/update_info_process","POST",option); 
    })
    //키 보여주기 숨기기
    $('.btn_input_type').click(function(){
        var text = $('.btn_input_type').text();
        if(text == '표시'){
            $('#stream').attr('type','text');
            $('.btn_input_type').text('숨기기');
        }else{
            $('#stream').attr('type','password');
            $('.btn_input_type').text('표시');
        }
    });  
    //키 복사
    $('.btn_copy').click(function(){
        console.log($('#stream').attr("type"))
        if($('#stream').attr("type") == "text"){
          $('#stream').select();
          document.execCommand("Copy");
        }else{
          $('#stream').attr('type','text');
          $('#stream').select();
          document.execCommand("Copy");
          $('#stream').attr('type','password');            
        }
    });
    //키 초기화
    $('.btn_clean').click(function(){
        var option = new Object();
        option.success = function(v){
            $('#stream').attr('value',v);
            alert("초기화 되었습니다.");
        }
        _ajax.ajaxData("/tool/setting/updateKey","POST",option);
        
    });

    /*상품 리스트 페이지 --------------------------------*/
    //수정 페이지 접속
    $('.btn_pd_update').click(function(){
        var id = $(this).parents('.pd_content_area').find('.pd_cell_2').text().trim();
        location.href="/tool/products/create?pdId="+id;
    });
    //전체 선택
    $('.isChek').click(function(){
       const checkboxes=document.querySelectorAll("input[type='checkbox']");
        checkboxes.forEach((checkbox)=>{
            checkbox.checked = this.checked
        })

    })
    //선택 삭제
    $('.chk_deletePd').click(function(){
        var cnt = document.querySelectorAll("input[id='pdId']:checked");
        if(cnt.length==0){
            alert("선택한 상품이 없습니다.");
        }else if(confirm("선택한 상품을 삭제 하시겠습니까?")==true){
            let param = new Object();
            var pdIdList = [];
            cnt.forEach((checkbox)=>{
                pdIdList.push({'pdId':checkbox.getAttribute("value")});
            })
            param.productIdList = pdIdList;
            
            var option = new Object();
            option.data = JSON.stringify(param)
            option.contentType = 'json'
            option.success = function(v){
                if(v.result){
                    location.reload();
                }else{
                    alert(data.message);
                    location.reload();
                }
            }
            _ajax.ajaxAlert("/tool/products/delete_process","POST",option); 
        }
    });
   
    /*상품 등록 페이지 ----------------------------------------------------*/
/*    //상품 전시 상태 리스트 보여주기
    $('.pd_status_filter').click(function(){
        $('.ft_ct_ul').toggleClass('bp_bc');
    });
    //상품 전시 상태 리스트 선택 값 보여주기
    $('.ft_ct_li').click(function(){
        var status = $(this).text().trim();
        if(status == '미전시'){
            $('.ft_control_btn').attr('class','ft_control_btn off');
        }else{
            $('.ft_control_btn').attr('class','ft_control_btn on');
        }
         
         $('.ft_control_btn>span').text(status);
         
    });*/
    //select 선택
    $("select[name=pdStatus]").change(function(){
        pdStatus = $("select[name=pdStatus]").val();
    });
    //가격 콤마 설정
    $('#pdPrice').on('keyup',function(){
        var price = _format.numberFormat($(this).val());
        var priceComma = _format.numberFormatComma(price);
        $(this).val(priceComma);
    });
    //상품 등록
    $('.btn_product_create').click(function(){
        var result = pdInputValCheck();
        if(result){
            var option = new Object();
            option.data = {'pdName':pdName,'pdPrice':pdPrice,'pdStrock':pdStrock,'pdStatus':pdStatus};
            option.success = function(v){
                if(v.result){
                    alert(v.message);
                    location.href ="/tool/products";
                }else{
                    alert(data.message);
                }
            }
             _ajax.ajaxData("/tool/products/create_process","POST",option);
        }

    })
    //상품 수정
    $('.btn_product_update').click(function(){
        var result = pdInputValCheck();
        var pdId = $('#product_id').val();
        if(result){
            var option = new Object();
            option.data = {'pdId':pdId,'pdName':pdName,'pdPrice':pdPrice,'pdStrock':pdStrock,'pdStatus':pdStatus};
            option.success = function(v){
                if(v.result){
                    alert(v.message);
                    location.href ="/tool/products";
                }else{
                    alert(data.message);
                }
            }
             _ajax.ajaxData("/tool/products/update_process","POST",option);
        }

    })
    /*판매글 관리 페이지*/
    $('.iChek, .isChek').on('click',function(){
         $('.pdAddList_area').removeClass('red_act');
    });
    //선택 삭제
    $('.chk_deletePost').click(function(){
        var cnt = document.querySelectorAll("input[id='psId']:checked");
        if(cnt.length==0){
            alert("선택한 판매글이 없습니다.");
        }else if(confirm("선택한 판매글을 삭제 하시겠습니까?")==true){
            let param = new Object();
            var postIdList = [];
            cnt.forEach((checkbox)=>{
                postIdList.push({'psIndex':checkbox.getAttribute("value")});
            })
            param.pdPostAddVo = postIdList;
            var option = new Object();
            option.data = JSON.stringify(param)
            option.contentType = 'json'
            option.success = function(v){
                location.reload();
            }
            _ajax.ajaxAlert("/tool/pdpost/delete_process","POST",option); 
        }
    });
    /*판매글 등록-수정 페이지---------------------------------------------*/
    //select  카테고리 선택
    $("select[name=psCate]").change(function(){
        psCate = $("select[name=psCate]").val();
    });
    //배송 방법 선택
    $("select[name=psDeliverySel]").change(function(){
        psDeliverySel = $(this).val().trim();
        if(psDeliverySel !=0){
             $('#psDelivery').attr('disabled',false);
        }else{
            $('#psDelivery').val('');
            $('#psDelivery').attr('disabled',true);
        }
    });
    //상품 체크시 red_act css 삭제
    //가격 콤마 설정
    $('#psPrice, #psDelivery').on('keyup',function(){
        var price = _format.numberFormat($(this).val());
        var priceComma = _format.numberFormatComma(price);
        $(this).val(priceComma);
    });

    //파일 업로드
    $('.btn_post_img').on('change', function(){
         var files =$("input[type='file']")[0].files; 
         var filename;
        if(files[0].size >= maxSize){
            alert("파일 사이즈 초과");
            return false;
        }else if(regex.test(files[0].name)==false){
            alert("해당 종류의 파일은 업로드할 수 없습니다.");
            return false;
        }else{
            if(window.FileReader){
               filename = files[0].name;
            } else {
               filename = files[0].val().split('/').pop().split('\\').pop();
            }
            formData.set('file',files[0]);
        }       
        $('#fild_id').val('');
        $('.uploadName').removeClass('red_act');
        $(this).siblings('.uploadName').val(filename);

    });
    function uploadImg(){
        var result = false;
        //이미지 업로드
        var option = new Object();
        option.data = formData
        option.success = function(v){
            uploadFileId = v.data.fileId;   //전송된 파일 id 값 저장
             result = true;
        }
        _ajax.ajaxMultipart("/uploadFileAws","POST",option);
        //formData에 파일이 들어갔는지 확인하는 코드
        /*for (var pair of formData.entries()) {
         console.log(pair[0]+ ', ' + pair[1]); 
        }*/
        return result;
    }
    //판매글 올리기
    $('.btn_post_create').click(function(){
        var result = postInputValCheck();
        if(result){
            var result_img = uploadImg();
            if(result_img){
                //체크한 상품 값 가져오기
                var chkAddPdLsit =[];
                var chkAddPd=$("input[id='addPdVal']:checked");
                for(var i=0;i<chkAddPd.length;i++){
                   chkAddPdLsit.push({'pdId':$(chkAddPd[i]).val()}); 
                }
                //전송 할 값 하나로 저장
                var param = new Object();
                param = {'psTitle':psTitle,'psPrice':psPrice,'fileId':uploadFileId,
                'psDeliveryOpt':psDeliverySel,'psDelivery':psDelivery,'psContent':psContent,'psPostStatus':psPostStatus}
                param.pdPostAddVo= chkAddPdLsit;
                               
                var option = new Object();
                option.data = JSON.stringify(param);
                option.contentType = "json"
                option.success = function(v){
                    if(v.result){
                        alert(v.message);
                        location.href ="/tool/pdpost";
                    }else{
                        alert(v.message);
                    }
                }
               _ajax.ajaxData("/tool/pdpost/create_process","POST",option);
            }
        }
    })
    //판매글 수정하기
    $('.btn_post_update').click(function(){
        var result = postInputValCheck();
        var postId = $('#post_id').val();
        var fildId = $('#fild_id').val();
        var result_img = true;
        if(result){
            if(fildId ==''){
                result_img = uploadImg();              
            }else{
                uploadFileId = fildId;
            }          
            if(result_img){
                //체크한 상품 값 가져오기
                var chkAddPdLsit =[];
                var chkAddPd=$("input[id='addPdVal']:checked");
                for(var i=0;i<chkAddPd.length;i++){
                   chkAddPdLsit.push({'pdId':$(chkAddPd[i]).val()}); 
                }
                //전송 할 값 하나로 저장
                var param = new Object();
                param = {'psIndex':postId,'psTitle':psTitle,'psPrice':psPrice,'fileId':uploadFileId,
                'psDeliveryOpt':psDeliverySel,'psDelivery':psDelivery,'psContent':psContent,'psPostStatus':psPostStatus}
                param.pdPostAddVo= chkAddPdLsit;
                
                var option = new Object();
                option.data = JSON.stringify(param);
                option.contentType = "json"
                option.success = function(v){
                    if(v.result){
                        alert(v.message);
                        location.href ="/tool/pdpost";
                    }else{
                        alert(v.message);
                    }
                }
               _ajax.ajaxData("/tool/pdpost/update_process","POST",option);
            }
        }
    })
    
    
    /*라이브 등록-수정 페이지*/
    //이미지 업로드
     $('.btn_title_img').on('change', function(){
        var files =$("input[type='file']")[0].files; 
        var filename;
        if(files[0].size >= maxSize20){
            alert("파일 사이즈 초과");
            return false;
        }else if(regex.test(files[0].name)==false){
            alert("해당 종류의 파일은 업로드할 수 없습니다.");
            return false;
        }else{
            if(window.FileReader){
               filename = files[0].name;
            } else {
               filename = files[0].val().split('/').pop().split('\\').pop();
            }
            formData.set('file',files[0]);
        }   
        $('.uploadName').removeClass('red_act');
        
        var option = new Object();
        option.data = formData
        option.success = function(v){
            uploadFileId = v.data.fileId;
            var path= v.data.uploadPath;
            $('.btn_img').removeClass('red_act');
            $('.title_img').attr("src","https://s3.ap-northeast-2.amazonaws.com/onlshop.shop"+path+"/"+uploadFileId);
        }
        _ajax.ajaxMultipart("/uploadFileAws","POST",option); 
    })
    
    //라이브 등록
    $('.btn_live_create').click(function(){
        var result =liveInputValCheck();
        if(result){
            var option = new Object();
            option.data = {'liveTitle':liveTitle,'cateIndex':liveCate,'liveStartDay':liveDay,'liveStartTime':liveDay+" "+liveStartTime
                            ,'liveEndTime':liveDay+" "+liveEndTime,'psIndex':psPostSel,'fileId':uploadFileId,'region1Code':region1Code};
            option.success = function(v){
                if(v.result){
                    alert(v.message);
                    location.href ="/tool/live";
                }else{
                    alert(v.message);
                }
            }
            console.log(option.data);
            _ajax.ajaxData("/tool/live/create_process","POST",option);
        }
    })
    //라이브 수정
    $('.btn_live_update').click(function(){
        if(uploadFileId == ''){
            uploadFileId ='non';
        }
        var result =liveInputValCheck();
        //var fildId = $('#fild_id').val();
        var liveId = $('#live_id').val();
        if(result){
            var option = new Object();
            option.data = {'liveId':liveId,'fildId':uploadFileId,'liveTitle':liveTitle,'cateIndex':liveCate,'liveStartDay':liveDay
                            ,'liveStartTime':liveDay+" "+liveStartTime,'liveEndTime':liveDay+" "+liveEndTime,'psIndex':psPostSel,'region1Code':region1Code};
            option.success = function(v){
                if(v.result){
                    alert(v.message);
                    location.href ="/tool/live";
                }else{
                    alert(v.message);
                }
            }
            _ajax.ajaxData("/tool/live/update_process","POST",option);
        }
    })
    //라이브 삭제
    $('.btn_live_delete').click(function(){
        if(confirm("선택한 라이브를 삭제 하시겠습니까?")==true){
            var liveId = $(this).attr('class').split('btn_live_delete').join('').trim();
            var option = new Object();
            option.data ={'liveId':liveId};
            option.success = function(v){
                if(v.result){
                    alert(v.message);
                    location.reload();
                }else{
                    alert(v.message);
                }
            }
            _ajax.ajaxData("/tool/live/delete_process","POST",option);
        }
    })
    
    /*판매 내역 관리 */
    //주문 취소
    $('.chk_cancelOrder').click(function(){
        var cnt = document.querySelectorAll("input[id='orderPdId']:checked");
        if(cnt.length==0){
            alert("선택한 주문건이 없습니다.");
        }else if(confirm("선택한 주문건을 주문 취소 하시겠습니까?")==true){
            let param = new Object();
            param.salesIdsVo = chk_sale_cancelData(cnt);
            param.orderStatus = '29';
            if(param.salesIdsVo.length != 0){
                var option = new Object();
                option.data = JSON.stringify(param)
                option.contentType = 'json'
                option.success = function(v){
                    location.reload();
                }
                _ajax.ajaxAlert("/tool/sale/cancel_order_process","POST",option); 
            }else{
                alert('배송중인 상품은 주문 취소가 불가능합니다.');
            }
        }
    })
    //발주 확인(배송 준비중으로 변경 - 취소요청시 판매자 승인거쳐야함)
    $('.chk_sendCheck').click(function(){
        var cnt = document.querySelectorAll("input[id='orderPdId']:checked");
        if(cnt.length==0){
            alert("선택한 판매 내역 없습니다.");
        }else if(confirm("선택한 주문건에 대해 발주 확인 처리를 진행하시겠습니까?")==true){
            let param = new Object();
            param.salesIdsVo = chk_sale_data(cnt);
            if(param.salesIdsVo.length != 0){
                param.orderStatus = '30';
                var option = new Object();
                option.data = JSON.stringify(param)
                option.contentType = 'json'
                option.success = function(v){
                    location.reload();
                }
               _ajax.ajaxAlert("/tool/sale/change_order_status_process","POST",option); 
            }else{
                alert('배송중인 상품은 주문 상태 변경이 불가능합니다.');
            }
        }
        
    })
    //발송 지연(배송 지연)
    $('.chk_sendHold').click(function(){
        var cnt = document.querySelectorAll("input[id='orderPdId']:checked");
        if(cnt.length==0){
            alert("선택한 판매 내역 없습니다.");
        }else if(confirm("선택한 주문 건에 대해 발송 지연 처리를 진행하시겠습니까?")==true){
            let param = new Object();
            param.salesIdsVo = chk_sale_data(cnt);
            if(param.salesIdsVo.length != 0){
                param.orderStatus = '31';
                var option = new Object();
                option.data = JSON.stringify(param)
                option.contentType = 'json'
                option.success = function(v){
                    location.reload();
                }
                _ajax.ajaxAlert("/tool/sale/change_order_status_process","POST",option); 
            }else{
                alert('배송중인 상품은 주문 상태 변경이 불가능합니다.');
            }
        }
    })    
    //발송 처리(배송중)
        $('.chk_sendPro').click(function(){
        var cnt = document.querySelectorAll("input[id='orderPdId']:checked");
        if(cnt.length==0){
            alert("선택한 판매 내역 없습니다.");
        }else if(confirm("선택한 주문 건에 대해 발송 처리를 진행하시겠습니까? \n발송 처리시 결제 및 주문 상태를 변경할 수 없습니다.")==true){
            let param = new Object();
            param.salesIdsVo = chk_sale_data(cnt);
            if(param.salesIdsVo.length != 0){
                param.orderStatus = '32';
                var option = new Object();
                option.data = JSON.stringify(param)
                option.contentType = 'json'
                option.success = function(v){
                    location.reload();
                }
                _ajax.ajaxAlert("/tool/sale/change_order_status_process","POST",option); 
            }else{
                alert('이미 배송처리된 상품입니다.');
            }
        }
    }) 
})
function chk_sale_cancelData(cnt){
    var arr = [];
    var param =[];
    cnt.forEach((checkbox)=>{
        arr=checkbox.getAttribute("value").split('-');  // - 기준으로 잘라서 배열로 저장
        if(arr[3] =='32'){ //상품 배송중이면 취소 불가
            return false;
        }

        param.push({'orderId':arr[0],'orderPdId':arr[1],'psIndex':arr[2]});

    })
    return param;
}
function chk_sale_data(cnt){
    var arr = [];
    var param =[];
    cnt.forEach((checkbox)=>{
        arr=checkbox.getAttribute("value").split('-');  // - 기준으로 잘라서 배열로 저장
        if(arr[3] =='32'){ //상품 배송중이면 취소 불가
            return false;
        }
        param.push({'orderId':arr[0],'orderPdId':arr[1]});

    })
    return param;
}
//상품 등록-수정 빈칸 확인
function pdInputValCheck(){
    pdName = $('#pdName').val();
    pdPrice = _format.numberFormat($('#pdPrice').val());
    pdStrock = $('#pdStrock').val();
    if(pdName==''){
        $('#pdName').addClass('red_act');
        isVal = false;
    }else{isVal = true;}
    if(pdPrice==''){
        $('#pdPrice').addClass('red_act');
        isVal = false;
    }else{isVal = true;}
    if(pdStrock == '' ){
        $('#pdStrock').addClass('red_act');
        isVal = false;
    }else{isVal = true;}
    if(pdStatus == 'non'){
        $('#fontName').addClass('red_act');
        isVal = false;
    }else{isVal = true;}
    return isVal;
}
//판매글 등록-수정 빈칸 확인
function postInputValCheck(){    
    /*psCate = $('#psCate').val();*/
    psTitle = $('#psTitle').val();
    upload = $('.uploadName').val();
    psPrice = _format.numberFormat($('#psPrice').val());
    psDeliverySel = $('#psDeliverySel').val();
    psDelivery = _format.numberFormat($('#psDelivery').val());
    psContent = $('#psContentIframe').contents().find('body').html();
    psPostStatus = $('#pdPostOnOff').val();
/*    if(psCate=='non'){
        $('#psCate').addClass('red_act');
        isVal = false;
    }*/
    if(psTitle==''){
        $('#psTitle').addClass('red_act');
        isVal = false;
    }else{
         isVal = true;
    }
    if(upload == '파일선택' ){
        $('.uploadName').addClass('red_act');
        isVal = false;
    }else{isVal = true;}
    if(psPrice == ''){
        $('#psPrice').addClass('red_act');
        isVal = false;
    }else{isVal = true;}
    if(psDeliverySel == 'non'){
        $('#psDeliverySel').addClass('red_act');
        isVal = false;
    }else if(psDeliverySel != 0){
        if(psDelivery == ''){
            $('#psDelivery').addClass('red_act');
            isVal = false;
        }else{isVal = true;}
    }else{isVal = true;}
    if(psContent == ''){
        $('#psContentIframe').addClass('red_act');
        isVal = false;
    }else if(psContent.length>3000){
        alert("글자수 및 태그 수를 초과하였습니다.");
        $('#psContentIframe').addClass('red_act');
        isVal = false;
    }else{isVal = true;}
    if($("input[type='checkbox']:checked").length == 0){
        $('.pdAddList_area').addClass('red_act');
        isVal = false;
    }else{isVal = true;}
    if(psPostStatus == 'non'){
        $('#pdPostOnOff').addClass('red_act');
        isVal = false;
    }else{isVal = true;}
    
    return isVal;
}
var liveTitle,liveCate,liveDay,liveStartTime,liveEndTime,psPostSel,region1Code
function liveInputValCheck(){
    liveTitle = $('#liveTitle').val();
    liveCate = $('#liveCate').val();
    liveDay = $('#live_day').val();
    liveStartTime = $('#live_startTime').val();
    liveEndTime = $('#live_endTime').val();
    psPostSel = $('#psPostSel').val();
    region1Code = $('#region1Code').val();
    if(liveTitle ==''){
       $('#liveTitle').addClass('red_act');
       isVal = false;
    }else{isVal = true;}
    if(liveCate =='non'){
       $('#liveCate').addClass('red_act');
       isVal = false;
    }else{isVal = true;}
    if(uploadFileId ==''){
       $('.btn_img').addClass('red_act');
       isVal = false;
    }else{isVal = true;}    
    if(psPostSel =='non'){
       $('#psPostSel').addClass('red_act');
       isVal = false;
    }else{isVal = true;}
    
    return isVal;
}