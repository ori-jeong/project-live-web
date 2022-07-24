$(document).ready(function() {     
    
    //음소거 버튼
    $('.SoundButton').click(function(){
        if($('#video').prop('muted')){
            $('#video').prop('muted', false);
            $('.SoundButton_icon').css('background-position','-120px -60px');
        }else{
            $('#video').prop('muted', true);
            $('.SoundButton_icon').css('background-position','-90px -60px');
         }
    })
    //화면 닫기
    $(".CloseButton").on("click", function(e){
        self.close();                       // 자기 자신 창 닫기
        var referrer = document.referrer;  //이전 경로 담기
        if(referrer ==''){
            referrer = "/";               //이전 경로 없으면 메인화면으로 설정
        }
         window.open(referrer,'_self').close();
    });
 
    //팝업창 열기
    $(".TagItem_wrap").click(function(){
        $(".ItemModal_section").css('display','');
    })
    $(".ModelTitle_btn").click(function(){
        $(".ItemModal_section").css('display','none');
    })
 
});
