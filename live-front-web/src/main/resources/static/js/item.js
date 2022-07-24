let products = [];
let item_price, item_name;
let item_id_result,item_price_result, c_item_name;
let psIndex;

$(document).ready(function() {
    var ft_result = false;
    //구매하기 버튼 클릭시 구매창과 버튼 표시
    $('.click_be').click(function(){
        $('.click_be').css("display","none");
        $('.fix_a_btn_area').css("display","");
        $('.fix_filter_wrap').css("display","");
    })
    $('.fix_filter_head').click(function(){
        $('.click_be').css("display","");
        $('.fix_a_btn_area').css("display","none");
        $('.fix_filter_wrap').css("display","none");
    })
    // 구매아이템 리스트 열기
    $('.filter_must').click(function(){
        $('.ft_ct_ul').toggleClass('bp_bc');
    }) 
    //상품 선택시 리스트에 추가
    $('.ft_ct_li').click(function(){
        //let item_name = ($(this).text()).trim(); //상품 이름
        //상품id 가져오기
        var item_id = $(this).attr('class').split('ft_ct_li').join(' ').trim();
        getItemInfo(item_id);
        //상품 id로 해당 상품의 이름과 가격 가져오기

        var isValid = false;
        //선택값이 리스트에 존재하는지 확인
        $('.item_name').each(function(index,item){
            let inner_name = (item.innerText).trim(); //선택된 상품 리스트의 이름값 가져오기
            if(item_name == inner_name){
                alert('이미 선택된 항목입니다.');
                isValid = true;
                return false; 
            }
        })
        if(!isValid){
            let select_li = $('.ft_select_ul');
            let li = '<li class="ft_select_li '+item_id+'"><div class="item_name">'+item_name+'</div>';
            li+='<div class="item_num_box"><div class="item_num_left"><div class="item_num_btn_area '+item_id+'">';
            li+='<button type="button" class="count_btn count_minus" disabled></button>'
            li+='<input type="number" maxlength="5" class="item_count" value="1">';
            li+='<button type="button" class="count_btn count_plus"></button></div></div>';
            li+='<div class="item_num_right"><span class="item_amount">'+item_price+'</span>원</div></div>';
            li+='<button type="button" class="item_x"><i class="item_x_icon"></i></button></li>';
            select_li.append(li);
            
            if(!ft_result){
                ft_blind_toggle();
                ft_result = true;
            }
            result_sum(true);             
        }

    })
    //x버튼 (선택한 상품 삭제)
    $(document).on('click','.item_x',function(){
        $(this).parent('li').remove();
        result_sum(false);
        //선택 상품을 담는 ul에 값이 없으면 class명의 blind 삭제
        var ulVal = $('.ft_select_ul').val();
        if(ulVal == ''){
            ft_blind_toggle();
        }
    })
    
    //-버튼
    $(document).on('click','.count_minus',function(){
        let minus = parseInt($(this).next().val());
        item_id_result = $(this).parent().attr('class').split('item_num_btn_area').join('').trim();
        if(minus > 1){
            //수량 수정
            minus = minus - 1;
            $(this).next().val(minus);
            //가격 수정
            getItemPrice(item_id_result);
            $(this).parent().parent().next().children().first().text(_format.numberFormatComma(item_price_result*minus));
            result_sum();
        }
        if(minus == 1){
            $(this).attr("disabled",true);
        }
    })
    //+버튼
    $(document).on('click','.count_plus',function(){
        let plus = parseInt($(this).prev().val());
        item_id_result = $(this).parent().attr('class').split('item_num_btn_area').join('').trim();
        if(plus < 100){
            //수량 수정
            plus = plus + 1;
            $(this).prev().val(plus);
            //가격 수정
            getItemPrice(item_id_result);
            $(this).parent().parent().next().children().first().text(_format.numberFormatComma(item_price_result*plus));
            result_sum();
        }
        if(plus != 1){
            $(this).prev().prev().attr("disabled",false);
        }
    });
    
    //장바구니로 이동
    $('.btn_bk').click(function(){
        var pdIds = [];
        var cartList =[];
        $('.item_num_btn_area').each(function(){
            pdIds.push($(this).attr('class').split('item_num_btn_area').join('').trim());
        })
        $('.item_count').each(function(index,item){
            cartList.push({pdId:pdIds[index],pdCount:item.value});
        })
        if(cartList.length != 0 ){
            let param = new Object();
            param.psIndex = psIndex;
            param.cartPdVo = cartList;
            
            var option = new Object();
            option.data = JSON.stringify(param);
            console.log(param);
            option.contentType = "json";
            option.success = function(v){
                console.log(v.result);
                if(v.result){
                   if(confirm(v.message)){
                     parent.location.href = "/cart";
                   }  
                }else{
                    parent.location.href = "/cart";
                }
            }
            _ajax.ajaxData("/cart/setCart","POST",option); 
        }else{
            alert("상품을 선택해주세요.")
        }
    });
    //바로 구매로 이동
    $('.btn_buyPage').click(function(){
        var payForm = $('#payForm');
        var pdList = $('.ft_select_li');
        if(pdList.length != 0){
            var psIndex = $('.product_wrap').attr('class').split('product_wrap').join('').trim();
            $('#payForm').append($('<input />',{type:'hidden',name:'psIndex', value:psIndex}));
            for (var i = 0; i<pdList.length; i++){
                var pdId = $(pdList[i]).attr('class').split('ft_select_li').join('').trim();
                var pdCount =  $(pdList[i]).find('.item_count').val();
                $('#payForm').append($('<input />',{type:'hidden',name:'pdId', value:pdId}));
                $('#payForm').append($('<input />',{type:'hidden',name:'pdCount', value:pdCount}));
                //console.log(psId+" : "+pdId+" : "+pdCount);
            }
            
            payForm.submit();
        }else{
            alert("상품을 선택해주세요.")
        }

    });
});

function ft_blind_toggle(){
    $('.ft_select_area').toggleClass('blind');
    $('.ft_result').toggleClass('blind');
}
// 해당 상품 정보
function getItemInfo(item_id){
    products.map(item => {
        if(item.id === item_id){
            item_price = _format.numberFormatComma(item.price);
            item_name = item.name;
        }
    })
}
//해당 상품의 가격
function getItemPrice(item_id_result){
    products.map(item => {
        if(item.id === item_id_result){
            item_price_result = item.price;
        }
    })
}
//총 수량, 총 금액
function result_sum(){
    let itemCount = 0;
    let itemAmount = 0;
    // 총 수량   
    $('.item_count').each(function(index,item){
        itemCount +=parseInt(item.value);
    })
    //총 금액    
    $('.item_amount').each(function(index,item){
        let num = _format.numberFormat(item.innerText);
        itemAmount +=parseInt(num);
    })    

    $('.sum_count').text(itemCount);
    let sum = _format.numberFormatComma(itemAmount);        
    $('.sum_item_amount').text(sum);
}
