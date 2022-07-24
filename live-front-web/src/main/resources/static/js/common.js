_common = {
    
}
_format = {
    numberFormatComma : function(v) {
        v = v * 100;
        v = Math.floor(v)/100;
        let formatNum = v.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        return formatNum;
    }
    ,numberFormat : function(v) {
        let formatNum = v.replace(/,/g, "");
        return formatNum;
    }

}

_validate ={
    userId : function(v){
        const regex = /^[-_0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]+\.[a-zA-Z]{2,3}$/;
        if(regex.test(v)){
            return true;
        }else{
            return false;
        }
    }
    ,userPw : function(v){
        const regex = /^(?=.*[A-Za-z])(?=.*\d)[!@#$%^&*()-_+=\.0-9a-zA-Z]{8,16}$/;
        if(regex.test(v)){
            return true;
        }else{
            return false;
        }
    }
    ,phoneHyphen : function(v){
        if (v.length === 10) {
            v=v.replace(/-/g, '').replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
        }else{
            v=v.replace(/-/g, '').replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
        }
        return v;
    }
    ,userPhone : function(v){
        const regex =  /^01([0|1|6|7|8|9])-?([0-9]{4})-?([0-9]{4})$/;
        if(regex.test(v)){
            return true;
        }else{
            return false;            
        }
    }
}
_ajax={
    ajaxData : function(rurl,mt,option){
        //async true : 비동기 , false : 동기(서버응답)
        if(option.contentType!="json"){
            option.contentType="application/x-www-form-urlencoded; charset=utf-8";
        }else{
            option.contentType="application/json; charset=utf-8";    //json전송
        }
        console.log(option.contentType);
        $.ajax({
            url:rurl
            , data : option.data
            , async : true
            , method : mt
            , cache : false
            //, dataType : "json"
            , contentType : option.contentType
            , beforeSend : function (xhr){
                let csrfHeader = $("meta[name='_csrf_header']").attr("content");
                let csrfToken = $("meta[name='_csrf']").attr("content");
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },success : function (val){
                if(option.success != undefined){
                    option.success(val);        
                }
                    
            },error : function(err){
                _message.error(err);
            }
        });
        //return $.ajax(ajaxParam);
    }
    ,ajaxAlert : function(rurl,mt,option){
        //application/x-www-form-urlencoded
        //서버에 전송되는 HTTP 메시지가 하나의 큰 쿼리 스트링이고 이름/값 쌍들이 "&"로 구분되어 있으며, 이름과 값은 "="으로 구분된다
        if(option.contentType!="json"){
            option.contentType="application/x-www-form-urlencoded; charset=utf-8";
        }else{
            option.contentType="application/json; charset=utf-8";    //json전송
        }
        //async true : 비동기 , false : 동기(서버응답)
        $.ajax({
            url:rurl
            , data : option.data
            , async : true
            , method : mt
            , cache : false
            , dataType : "json"
            , contentType : option.contentType
            , beforeSend : function (xhr){
                let csrfHeader = $("meta[name='_csrf_header']").attr("content");
                let csrfToken = $("meta[name='_csrf']").attr("content");
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },success : function (val){
                if(val.message!=null){
                    alert(val.message);
                }
                if(val.result){
                    option.success(val);
                }            
            },error : function(err){
                _message.error(err);
            }
        });
    }
    ,ajaxConfirm : function(rurl,mt,option){
        if(option.contentType!="json"){
            option.contentType="application/x-www-form-urlencoded; charset=utf-8";
        }else{
            option.contentType="application/json; charset=utf-8";    //json전송
        }
        $.ajax({
            url:rurl
            , data : option.data
            , async : true
            , method : mt
            , cache : false
            , dataType : "json"
            , contentType : option.contentType
            , beforeSend : function (xhr){
                let csrfHeader = $("meta[name='_csrf_header']").attr("content");
                let csrfToken = $("meta[name='_csrf']").attr("content");
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },success : function (val){
                if(confirm(val.message)){
                    parent.location.href = option.href;
                }
            },error : function(err){
                _message.error(err);
            }
        });
    }
    ,ajaxAlertAyFalse : function(rurl,mt,option){
        //application/x-www-form-urlencoded
        //서버에 전송되는 HTTP 메시지가 하나의 큰 쿼리 스트링이고 이름/값 쌍들이 "&"로 구분되어 있으며, 이름과 값은 "="으로 구분된다
        if(option.contentType!="json"){
            option.contentType="application/x-www-form-urlencoded; charset=utf-8";
        }else{
            option.contentType="application/json; charset=utf-8";    //json전송
        }
        //async true : 비동기 , false : 동기(서버응답)
        $.ajax({
            url:rurl
            , data : option.data
            , async : false
            , method : mt
            , cache : false
            , dataType : "json"
            , contentType : option.contentType
            , beforeSend : function (xhr){
                let csrfHeader = $("meta[name='_csrf_header']").attr("content");
                let csrfToken = $("meta[name='_csrf']").attr("content");
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },success : function (val){
                if(val.message!=null){
                    alert(val.message);
                }
                if(val.result){
                    option.success(val);
                }            
            },error : function(err){
                _message.error(err);
            }
        });
    }
    ,ajaxMultipart : function(rurl,mt,option){
        $.ajax({
            url:rurl
            , data : option.data
            , type : mt
            , processData: false        //기본값은 true
            , contentType: false        // multipart/form-data타입을 사용하기위해 false 로 지정
            , dataType : "json"
            , async : false
            , enctype: 'multipart/form-data'
            , beforeSend : function (xhr){
                let csrfHeader = $("meta[name='_csrf_header']").attr("content");
                let csrfToken = $("meta[name='_csrf']").attr("content");
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },success : function (val){
                if(val.result){
                    option.success(val);
                }
            },error : function(err){
                _message.error(err);
            }
        });
    }
}
_message={
    error : function(xhr){
        let errorMsg = "처리중 오류가 발생하였습니다.";
        let errorCode;
        if (xhr) {
            if (xhr.status === 400) {
                errorCode = "400";
            } else if (xhr.status === 401) {
                errorCode = "401";
            } else if (xhr.status === 403) {
                errorCode = "403";
            } else if (xhr.status === 404) {
                errorCode = "404";
            } else if (xhr.status === 500) {
                errorCode = "500";
            } else if (xhr.status === 503) {
                errorCode = "503";
            } else {
                errorCode = xhr.status;
            }
        } else {
            errorCode = "ETC";
        }
        alert(errorMsg +"["+errorCode+"]");        
    }
}
_create={
    input : function(vName, vValue){
        var formInput = document.createElement("input");
        formInput.type = "hidden";
        formInput.name = vName;
        formInput.value = vValue;
        return formInput;
    }
}
_cookie ={
    get:function(key){
        var idVal = key + "=";
        // ';' 마다 자르기
        var val = document.cookie.split(';');
        for(var i=0; i<val.length;i++){
            var c = val[i]; // c : rememberId=winecode06@naver.com
            //c의 첫번째 단어가 빈칸이면 그 다음번째부터 끝까지(c값의 길이) 출력 => 앞에 공백을 제외하고 가져오기
            while (c.charAt(0)==' ') c = c.substring(1,c.length);
            //c 안에 rememberId= 값이 없으면(즉 아이디 값이 있으면)  //rememberId= 을 제외한 유저 아이디 값 가져오기
            if (c.indexOf(idVal) == 0) return c.substring(idVal.length,c.length);          
        }
    }
    ,set:function(key,value){
        var expires ="";
        var date = new Date();                              //현재 날짜
        date.setDate(date.getDate()+(30));                  //날짜 넣기(현재 날짜 + (한 달))
        expires = "; expires ="+date.toUTCString();         //toUTCString : UTC시간대를 사용해 날짜를 문자열로 변환
        console.log(key+"="+value+expires+"; path=/");
        document.cookie = key+"="+value+expires+"; path=/"; //document.cookie에 값 넣기
    }, remove: function(key){
        document.cookie = key + "=; expires=0; path=/";     //document.cookie 값 삭제
    }
}