
$(document).ready(function() {

    
    //페이지 입장 시 주소 선택 팝업 숨기기
    $('.order_addr_pop').hide();

    //주소 선택 팝업 보여주기 - 숨기기
    $('.order_address_change').click(function(){
        $('.order_addr_pop').show(); //주소 선택 팝업 보여주기
    })
    $('.order_addr_pop').click(function(){
        $('.order_addr_pop').hide();
    });
    
    //주소 변경하기
    $('.btn_addr_check').click(function(){
         addrNum = $(this).attr('class').split('btn_addr_check').join('').trim(); //주소Code
         
         var upperClass = $(this).parents('.order_addr_list');
         var name = $(upperClass).find('.addr_name').text();        //배송지 이름   
         var recipient = upperClass.find('.addr_recipient').text(); //수령자 이름
         var marker = upperClass.find('.addr_marker').text();       //기본배송지 마크표시
         var addr = upperClass.find('.addr_addr').text();           //배송지
         var phone = upperClass.find('.addr_phone').text();         //전화번호
         
         $('.addr_fir').
         $('.addr_content').find('.addr_name').text(name);
         $('.addr_content').find('.addr_recipient').text(recipient);
         if(marker == ""){
             $('.addr_content').find('.addr_marker').hide();
         }else{
             $('.addr_content').find('.addr_marker').show();
         }
         $('.addr_content').find('.addr_addr').text(addr);
         $('.addr_content').find('.addr_phone').text(phone);
    });
    
    //결제수단 선택
    $('.btn_method_payment').click(function(){
        $('.btn_method_payment').removeClass('btn_method_payment_click');
        paymentCode = $(this).children('.payment_btn_text').attr('class').split('payment_btn_text').join('').trim();
        $(this).addClass('btn_method_payment_click');
        
    })
    //결제하기 클릭
    $('.btn_payment').click(function(){  
       
        if(paymentCode == 'card'){
            paymentCode = 'html5_inicis';
        }
        if(paymentCode == 0){
            alert('결제수단을 선택해주세요.');
        }else{           
            var getId = new Object();
            getId.success = function(v){
                var orderIndex = v;
                var psTitle = $('.ps_title').text();
                var addrNum = $('.addr_fir').attr('class').split('addr_fir').join('').trim();
                var param = new Object();                
                param.orderPdSelVo = psList;
                param.orderPdVo = pdList;
                param.paymentVo = {'paymentType' : paymentCode,'totalPdPrice':totalPdPrice,'totalDelivery':totalDelivery,'totalPrice':totalPrice};                  
                param.orderId = orderIndex;
                param.addrIndex = addrNum;  //주소코드
                param.paymentStatus = 21     //결제상태
                //param.fromCart = fromCart;
                var option = new Object();
                option.data = JSON.stringify(param);
                option.contentType = "json";
                option.success = function(v){
                   location.href = "/paymentComplete/"+orderIndex+"/"+totalPrice;       
                }
                _ajax.ajaxData("/orderPaymentProcess?fromCart="+fromCart,"POST",option);  
            }
             _ajax.ajaxData("/order/getOrderId","POST",getId);
                         
            /*IMP.init('imp40385990');                                //가맹점 식별 코드
            IMP.request_pay({
                pg: paymentCode,                                    //결제 pg 선택 (받아온 값으로 변경)
                pay_method : 'card',                                //결제 방식 - 생략 가능
                merchant_uid : orderIndex,                          //상점에서 관리하는 주문번호
                name : psTitle,                                     //주문명
                amount : totalPrice,                                //실제 결제되는 가격
                //customer_uid : buyer_name + new Date().getTime(), //파라메터가 있어야 빌링키 발급 시도
                buyer_email : email,                                //구매자 이메일
                buyer_name : orderName,                             //구매자 이름
                buyer_tel : tel,                                    //구매자 전화번호
                buyer_addr : "서울특별시 강남구 신사동",                                // 주소
                buyer_postcode: "01181"
            },function(rsp){
                if(rsp.success){
                    var param = new Object();                
                    param.orderPdSelVo = psList;
                    param.orderPdVo = pdList;
                    param.paymentVo = {'paymentType' : paymentCode,'totalPdPrice':totalPdPrice,'totalDelivery':totalDelivery,'totalPrice':totalPrice};                  
                    param.orderId = orderIndex;
                    param.addrIndex = addrNum;  //주소코드
                    param.paymentStatus = 21     //결제상태
                    //param.fromCart = fromCart;
                    var option = new Object();
                    option.data = JSON.stringify(param);
                    option.contentType = "json";
                    option.success = function(v){
                       location.href = "/orderComplete/"+orderIndex+"/"+totalPrice;       
                    }
                    _ajax.ajaxData("/orderConfirm?fromCart="+fromCart,"POST",option);
                }else{
                    alert(rsp.error_msg);
                    location.reload();
                }
            })*/
        }

    });
    
 
});