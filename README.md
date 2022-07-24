![header](https://capsule-render.vercel.app/api?type=waving&color=auto&height=300&section=header&text=PROJECT.%20Livecommerce%20&fontSize=60&animation=fadeIn&fontAlignY=38&desc=Livecommerce%20Website%20clone%20project&descAlignY=51&descAlign=62)


# PROJECT. 라이브 커머스 웹사이트 클론 프로젝트💻

> 2021.05.16 - 07.20  
> 개발인원 : 1명
>
> 라이브 커머스 서비스로 중점을 두어 프로젝트를 개발하였으며, 네이버 쇼핑라이브의 기능과 웹 디자인을 개인적으로 분석해 클론 코딩 프로젝트를 진행하였습니다.

# ❓ 목차

## 1. 프로젝트 소개    
> 1-1.  [프로젝트 제작 계기](#1-1-프로젝트-제작-계기)   
> 1-2.  [프로젝트 주요 기능](#1-2-프로젝트-주요-기능)  
> 1-3.  [개발 환경](#1-3-개발-환경)  
> 1-4.  [DB 모델링](#1-4-DB-모델링)  
> 1-5.  [실행 영상](#1-5-실행-영상)  

## 2. 프로젝트 주요 기능
### 2-1. 로그인 서비스  
> 2-1-1. [로그인에 따른 권한 처리](#2-1-1-로그인에-따른-권한-처리)  
> 2-1-2. [OAuth2와 JPA를 이용한 SNS 로그인](#2-1-2-OAuth2와-JPA를-이용한-SNS-로그인)  
### 2-2. 라이브 서비스
> 2-2-1. [Nginx 설정하기](#2-2-1-Nginx-설정하기)  
> 2-2-2. [Obs 사용하기](#2-2-2-Obs-사용하기)  
> 2-2-3. [라이브 영상 출력](#2-2-3-라이브-영상-출력)  
> 2-2-4. [FFmpeg로 라이브 영상 추출해 S3에 올리기](#2-2-4-FFmpeg-라이브-영상-추출해-S3에-올리기)  
> 2-2-5. [라이브 종료 후 추출한 영상 보여주기](#2-2-5-라이브-종료-후-추출한-영상-보여주기)
### 2-3. 채팅 서비스
> 2-3-1. [라이브 채팅방 설정](#2-3-1-라이브-채팅방-설정)  
> 2-3-2. [채팅방 인원수 표시](#2-3-2-채팅방-인원수-표시)
> 2-3-3. [판매자 채팅](#2-3-3-판매자-채팅-)
### 2-4. 상품 선택 및 장바구니
### 2-5. 구매페이지 - 결제 API를 이용한 구매 서비스
### 2-6. 마이페이지 - 구매 내역
### 2-7. 판매자 관리툴
> 2-7-1. 라이브 등록-[우리동네라이브] 지역 설정
## 3. 추후 개선할 사항

---
# 1. 프로젝트 소개
## 1-1. 프로젝트 제작 계기 및 참고 
* 라이브 서비스를 구현해보고 싶어서 처음에는 우리동네 홍보 스트리밍 서비스를 계획했지만 쇼핑과 결합된 라이브 커머스가 현재 큰 관심 중 하나라 생각하여 라이브 커머스 웹 프로젝트를 진행하기로 했습니다.

* 혼자 새롭게 기획하고 제작하기엔 많은 기간이 소요될 것 같아서 웹 디자인과 라이브 서비스는 네이버 쇼핑 라이브를 개인적으로 분석해 클론 코딩 하였으며, 장바구니, 구매페이지는 오늘의 쇼핑 웹 디자인을 참고 하였습니다. 

## 1-2. 프로젝트 주요 기능
1. 편의성과 소통성
* 실시간 영상과 실시간 채팅 기능으로 제품에 대한 정보를 좀 더 상세히 보여주면서 질문에 대한 답을 바로 제공합니다.

2. 효율성
* 실시간 방송이 끝나도 해당 영상을 다시 보여줌으로써 소비자에게 지속적인 서비스를 제공합니다.
* 또한 라이브를 보면서 구매할 수 있게 하단에 제품글을 누르면 제품 소개 및 구매 창이 팝업 형식으로 보여줄 수 있게 하였습니다.

3. 제품 관리 편리성
* 판매글을 올렸을 때 동일한 상품에 대한 상품명과 금액을 동일시 하기 위해 제품 등록과 판매글 등록을 구분하였습니다.
* 라이브 등록시 판매글을 선택해 동일한 제품을 계속 라이브 할 수 있습니다.

4. 지역 홍보
* 라이브 등록시 지역 설정 기능을 사용해 소비자가 '우리동네라이브' 탭에 접속하면 소비자 위지 정보와 동일한 판매 글을 보여줌으로써,
해당 지역 가게 홍보에도 도움이 되는 서비스를 제공합니다.

## 1-2. 개발 환경 🛠 
<img src="https://img.shields.io/badge/JAVA-FF4DAC?style=flat-square&logo=java&logoColor=white&width=300"> <img src="https://img.shields.io/badge/MySQL-B965DF?style=flat-square&logo=MySQL&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Boot-758bfd?style=flat-square&logo=SpringBoot&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Security-0083DE?style=flat-square&logo=Spring Security&logoColor=white"/> <img src="https://img.shields.io/badge/Apache Tomcat-21B88F?style=flat-square&logo=ApacheTomcat&logoColor=white"/> <img src="https://img.shields.io/badge/NGINX-007844?style=flat-square&logo=NGINX&logoColor=white"/>

<img src="https://img.shields.io/badge/FFmpeg-00BD8D?style=flat-square&logo=FFmpeg&logoColor=white"/> <img src="https://img.shields.io/badge/Socket.io-00B0D5?style=flat-square&logo=Socket.io&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon AWS-00A1FF?style=flat-square&logo=AmazonAWS&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon ECS-008CFF?style=flat-square&logo=Amazon ECS&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon S3-006BFF?style=flat-square&logo=Amazon S3&logoColor=white"/> 
<img src="https://img.shields.io/badge/Amazon RDS-4026FF?style=flat-square&logo=Amazon RDS&logoColor=white"/> 

<img src="https://img.shields.io/badge/HTML-FF911E?style=flat-square&logo=HTML5&logoColor=white"/> <img src="https://img.shields.io/badge/CSS3-FF7965?style=flat-square&logo=CSS3&logoColor=white"/> <img src="https://img.shields.io/badge/Javascript-FF4986?style=flat-square&logo=Javascript&logoColor=white"/> <img src="https://img.shields.io/badge/jQuery-FF26AC?style=flat-square&logo=jQuery&logoColor=white"/> <img src="https://img.shields.io/badge/Adobe Photoshop-B15AE9?style=flat-square&logo=Adobe Photoshop&logoColor=white"/> <img src="https://img.shields.io/badge/Adobe Illustrator-6E19DF?style=flat-square&logo=Adobe Illustrator&logoColor=white"/>

## 1-3. DB 모델링


## 1-4. 실행 영상

---

# 2. 프로젝트 주요 기능 설명
## 2-1. 로그인 서비스
> 로그인은 form 로그인과 OAuth2 로그인을 사용하였습니다.
> 로그인 아이디 저장 또는 자동 로그인을 선택해 적용할 수 있지만 SNS를 통한 로그인은 불가합니다.
> 또한 아이디-비밀번호 찾기 기능도 SNS를 통한 로그인은 접속한 SNS에서 가능합니다.

### 2-1-1. 로그인에 따른 권한 처리
* 유저 권한 정보를 저장하는 enum 클래스를 생성하고 DB에 저장된 유저 권한의 key 값과 비교해 유저의 권한 값을 부여해준다.
* 자동 로그인 처리시에도 사용된다.
```java
@Getter
@RequiredArgsConstructor
public enum UserRole {
    MEMBER("ROLE_MEMBER","11","정회원"), 
    EMPLOYEE("ROLE_EMPLOYEE","12","기업회원"),
    ADMIN("ROLE_ADMIN","13","관리자"), 
    UNKNOWN("UNKNOWN","",""),
    STOP("ROLE_STOP","99","블럭계정");
    
    private final String role;
    private final String key;
    private final String title;
    ...
}
```

```java
   ... 
  /* 유저 권한 정보 넣기 */
  UserRole userRole =UserRole.fromRole(user.getUserRole());
  ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

  if(UserRole.MEMBER.getKey().equals(user.getUserRole())) {                         //UserRole의 key값과 DB에 저장된 유저의 Role 값을 비교해
      authorities.add(new SimpleGrantedAuthority(UserRole.MEMBER.getRole()));       //일치하는 UserRole 값을 부여해준다
  }else if(UserRole.EMPLOYEE.getKey().equals(user.getUserRole())) {
      authorities.add(new SimpleGrantedAuthority(UserRole.EMPLOYEE.getRole()));
  }else if(UserRole.ADMIN.getKey().equals(user.getUserRole())){
      authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getRole()));
  }else if(UserRole.STOP.getKey().equals(user.getUserRole())) {
      authorities.add(new SimpleGrantedAuthority(UserRole.STOP.getRole()));
  }else{
      authorities.add(new SimpleGrantedAuthority(UserRole.UNKNOWN.getRole()));
  }
  ... 
```

### 2-1-2. OAuth2와 JPA를 이용한 SNS 로그인
* OAuth2UserService를 상속 받는 클래스를 생성해 로그인 하려는 SNS(네이버, 카카오)를 구분해 계정 정보를 받아와 
UserVo 형태로 값을 부여했습니다. 
```java
...
//카카오 계정 정보 가져오기
private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        // 받아 온 kakao 정보 안에 kakao_account 담기
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        // kakao_account안에 profile이라는 JSON객체 정보를 담는다
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");
        return OAuthAttributes.builder()
                .userId((attributes.get("id"))+"@k")
                .userUname((String) kakaoProfile.get("nickname"))
                .userNickname((String) kakaoProfile.get("nickname"))
                .userPlatform("kakao")
                .userRole(UserRole.MEMBER.getKey())
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
...

```
* 넘어오는 값과 DB 값을 비교해 없으면 바로 회원 가입 처리 하였으며 DB처리에는 JPA를 사용했습니다.
```java
...
//회원가입
@Modifying 
@Query(value="INSERT INTO USER_INFO (USER_ID,USER_PW,USER_UNAME,USER_NICKNAME,USER_PLATFORM,USER_ROLE)VALUES(#{userId},#{userPw},#{userUname},#{userNickname},#{userPlatform},#{userRole}",nativeQuery = true)
int insertUserInfo(@Param("userId")String userId,@Param("userPw")String userPw,@Param("userUname")String userUname
            ,@Param("userNickname")String userNickname,@Param("userPlatform")String userPlatform,@Param("userRole")String userRole);
```
* UserVo 형태로 값을 넣어 유저 정보를 return 하여 form로그인과 OAuth2로그인시 저장하는 계정 정보를 동일한 코드로 사용할 수 있게 했습니다.
* OAuth2User와 UserDetails를 상속 받아 UserVo형태로 값을 저장합니다.
```java
public class UserVo implements OAuth2User,UserDetails{
...
    @Builder
    public UserVo(String userId,String userUname, String userNickname, String userPhone, String userRole,Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.userUname = userUname;
        this.userNickname = userNickname;
        this.userPhone = userPhone;
        this.userRole = userRole;
        this.authorities = authorities;
    }
...
}
```
## 2-2. 라이브 서비스
> OBS와 연결할 서버를 위해 NGINX-RTMP를 사용하였으며 HTML5의 Video태그로 스트림을 받아 영상을 재생시킬 수 있게 HLS를 사용했습니다. 
> 실시간 영상을 종료 후에도 보여줄 수 있게 FFmpeg(미디어 인코딩/디코딩)와 FFprobe(멀티미디어 스트림 정보 출력)를 이용해 
> 라이브 영상을 추출하고 추출한 영상을 AWS S3에 저장했습니다.

### 2-2-1. Nginx-RTMP 설정하기
* ubuntu에 원하는 버전의 Nginx-RTMP를 다운 받아 컴파일러해 설치하고 RTMP소스를 HLS로 바꾸기 위해 환경 설정을 수정합니다.
* Nginx-RTMP 파일 
  * https://github.com/jh-2022-github/nginx-rtmp-module 
* conf 파일에 대한 자세한 설명은 아래 주소 참고 
  * https://github.com/dreamsxin/nginx-rtmp-wiki/blob/master/Directives.md#record_path

### 2-2-2. Obs 사용하기
* 설정 -> 방송 탭에서 접속할 서버와 방송 열쇠를 입력합니다.
- 서버: `rtmp://IP주소 또는 도메인 주소/live`   
- 방송 열쇠: `판매자에게 부여된 스트림키` 

### 2-2-3. 라이브 영상 출력
* hls.js를 사용해 현재 라이브중인 .m3u8파일을 가져와 video 태그에 주소를 넣어 보여줍니다.
* 라이브 종료시 에러창이 뜨지 않게 라이브 종료를 감지해 video 태그의 영상 주소 값을 지우고 실행주인 hls를 종료합니다.
```javascript
    //라이브 연결
    var video = document.getElementById('video');
    var videoSrc =url+"/hls/"+stream+"/index.m3u8";
    var hls = new Hls();
    if(status){
        if(video.canPlayType('application/vnd.apple.mpegurl')) {   // 우선 HLS를 지원하는지 체크
            video.src = videoSrc;
        }else if(Hls.isSupported()){                         // HLS를 지원하지 않는다면 hls.js를 지원
            hls.loadSource(videoSrc);                       //라이브 영상 파일 주소 불러오기
            hls.attachMedia(video);                         //비디오 태그에 넣기
            hls.on(Hls.Events.MANIFEST_PARSED,()=>{
                video.play();                               //라이브 시작
                $('.LiveBadge_live').removeClass('blind');  //라이브뱃지 보여주기
            })
            hls.on(Hls.Events.ERROR, function(data) {       //라이브 종료 감지
                liveEnd();                                  //종료 처리
            });
        }
    }else{
        liveEnd();
    }
   function liveEnd(){
   	...
        video.src ="";
        hls.destroy();              //hls 종료
    }
```
### 2-2-4. ffemeg,FFfrobe로 라이브 영상 추출해 S3에 올리기
* 크론식을 사용해 1분마다 라이브를 감지해 라이브 시작 시간 또는 종료 시간에 맞춰 라이브 상태를 변경해줍니다.
* 용량에 따른 비용 추가를 막기 위해 영상파일은 1분만 저장하게 했으며,
* 현재 라이브 중이면서 비디오 파일이 없는 라이브만 mp4파일로 변환해 AWS S3에 저장합니다.
```java
@Scheduled(cron="0 */1 * * * *")
public void liveVideoSave() throws IOException{
      ...
      // ubuntu에 설치한 ffmpeg, ffprobe의 경로를 넣어서 사용한다
      FFmpeg ffmpeg = new FFmpeg(ffmpegUrl);
      FFprobe ffprobe = new FFprobe(ffprobeUrl);
      ...
     // 현재 라이브 중이면서 저장된 영상이 없는 라이브만 실행한다.
     for(int i =0;i<live.size();i++) {            
            String key = live.get(i).getSelStreamKey();
            String liveId = createVideoName(live.get(i).getLiveId());
            // 변환 설정
            FFmpegBuilder builder = new FFmpegBuilder()
                    .setInput(url+"/hls/"+key+"/index.m3u8") // 파일 경로
                    .overrideOutputFiles(true)                                  // 오버라이드
                    .addOutput(uploadFolder+uploadFolderPath+"/"+liveId+".mp4") // 저장경로
                    .setVideoCodec("libx264")                                   // 비디오코덱
                    .disableSubtitle()                                          // 서브타이틀 제거
                    .setAudioChannels(2)                                        // 오디오채널(1: 모노, 2:스테레오)
                    .addExtraArgs("-t", "00:01:00")                             // 인코딩할 시간 설정
                    //(-ss "00:00:00"으로 설정하면 녹화 처음부터 저장되고 -t를 설정하지 않으면 방송된 영상 전체 저장) 
                    .addExtraArgs("-c:a", "aac")                                // 오디오 코덱
                    .addExtraArgs("-b:a", "192k")                               // 오디오 음질값
                    .setVideoResolution(1280, 720)                              // 동영상 해상도
                    .setVideoBitRate(1464800)                                   // 비디오 비트레이트
                    .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)               // ffmpeg 빌터 실행 허용
                    .done();
            FFmpegExecutor executor = new FFmpegExecutor(ffmpeg,ffprobe);             
            executor.createJob(builder).run();
            ...
            //위치한 영상 주소를 File에 넣어 S3에 저장한다
            File file = new File(uploadFolderUrl+uploadFolderPath+liveVideoVo.getVideoName());
            awsS3Service.uploadFile(file, uploadFolderPath,liveId.substring(1)+".mp4");
            
        }
```

### 2-2-5. 라이브 종료 후 추출한 영상 보여주기
* 라이브 영상을 저장해 라이브 종료 후에도 계속 보여줄 수 있게 S3에 있는 영상을 주소를 입력해 지속적인 서비스를 제공합니다.
```html
<video class="VideoPlayer_video replayVideo" autoplay muted loop poster="https://s3.ap-northeast-2.amazonaws.com/S3버킷명/저장된 경로/파일명>
      <source src="https://s3.ap-northeast-2.amazonaws.com/S3버킷명/저장된 경로/파일명" type="video/mp4">
</video>
```
## 2-3. 채팅 서비스
> 브라우저에 상관 없이 여러 개의 채팅방을 개설해 메세지를 효율적으로 전달할 수 있게 SockJS와 STOMP를 사용하였습니다.
> 채팅방의 누적 뷰 수는 채팅방 입장 시 쿼리문으로 값을 가져와 현재 채팅방에 있는 모든 유저들에게 보여줍니다. 
> 판매자는 채팅 입력 페이지가 따로 존재하며 글씨 색을 다르게 하여 소비자에게 정보를 눈에 띄게 전달합니다.

### 2-3-1. 라이브 채팅방 설정
* stomp 연결 경로를 지정하고 SockJS를 추가해줍니다.
* 애플리케이션 내부에서 메세지 주고 받을 수 있게 경로를 지정해줍니다.
```java
@Configuration
@EnableScheduling
@EnableWebSocketMessageBroker
//메세지 브로거에 대한 설정을 해주는 config
public class StompWebSocketConfig  implements WebSocketMessageBrokerConfigurer{
    
    @Value("${onl.chat.url}")
    private String chatUrl;
     
    /*client에서 websocket연결할 때 사용할 API 경로를 설정해주는 메서드*/
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/chat")  // 새로운 커넥션 생성시 사용됨 ex)new SockJS("/stomp/chat");
                .setAllowedOrigins(chatUrl) // 주소입력을 *(전체허용)로 하면 보안상의 문제 발생할 수 있음
                .withSockJS();              // SockJS를 추가해 모든 브라우저에서 사용할 수 있게 한다
    }
    /*애플리케이션 내부에서 사용할 path를 지정할 수 있음*/
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/pub"); //메시지 보낼 때 관련 경로 설정
        registry.enableSimpleBroker("/sub");                //메세지 받을 때 관련 경로
    }
}

```
### 2-3-2. 채팅방 인원수 표시
* 채팅방 입장시 뷰 수를 +1 update 하는 쿼리문을 넣어주고 다시 뷰 수를 불러와 값을 전달합니다. 
```java
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDto message) {
	...	
	LiveVo liveVo = new LiveVo();
	//뷰 수 저장하기
        liveService.setLiveView(liveVo);
	//뷰 수 가져오기
        int view = liveService.getLiveView(liveVo);
        //뷰 수 전달
        sendingOperations.convertAndSend("/sub/chat/count/" + message.getChatRoomId(),view);
	...
    }
```
### 2-3-3. 판매자 채팅
* 판매자는 라이브 관리페이지 안에서 채팅을 관리할 수 있습니다.
* 판매자 채팅 글은 다른 색으로 표시하여 소비자와 구분지어 보여줍니다.

* 움짤만 표시

## 2-4 상품 선택 및 장바구니
> 상품 라이브 영상을 보면서 제품을 고를 수 있게 구매창은 iframe을 팝업 형태로 설정했습니다.
> 장바구니 페이지에서는 상품의 수량 변경과 삭제, 선택 기능이 있고 선택 버튼은 상품이아닌 판매글 기준으로 선택이 가능합니다.
> 또한 판매자로 구분지어 배송비를 보여주며 선택한 상품의 금액 총 합과 배송비 총 합, 결제할 총 금액을 보여줍니다.

## 2-5. 구매페이지 - 결제 API를 이용한 구매 서비스
> my page에서 배송지 관리에 주소를 여러개 등록해 놓으면 구매 페이지에서 주소를 변경해 주문할 수 있습니다.
> Iamport API를 사용해 신용카드, 카카오페이, 토스페이를 사용해 결제가 가능합니다.
> 현재는 테스트 결제 상태입니다.

## 2-6. 마이페이지 - 구매 내역
* 상단에 주문 상태별 수는 한 달 기준으로 출력되며 숫자를 누르면 해당 상품 리스트를 보여줍니다.
* 날짜를 누르면 해당 날짜에 주문한 상품 리스트가 출력됩니다.

## 2-7 판매자 관리툴
> 라이브 관리, 상품 관리, 판매글 관리, 판매 내역 관리, 설정 탭으로 이루어져 있으며 각 해당 탭에서 등록, 수정, 삭제가 가능합니다.
> 상품 관리와 판매글 관리를 나누어 동일한 제품의 제품명과 가격에 혼란을 주지 않습니다.
> 설정 탭에서는 판매자의 프로필 사진과 이름을 변경할 수 있으며, 스트림키를 제공합니다.

### 2-7-1. 라이브 등록-[우리동네라이브] 지역 설정
* 라이브 등록시 보여줄 지역을 설정하면 '우리동네라이브' 탭에서 소비자와 동일한 지역의 라이브를 보여줄 수 있는 서비스를 제공합니다.

---

# 3. 추후 개선할 사항
* 채팅 로그를 저장(NoSql사용)해 이전 대화도 볼 수 있게 하기
* 채팅 ID를 판매자별 1개가 아닌 라이브 등록시 채팅 ID 1개를 부여해서 채팅 로그를 관리할 수 있게 하기
* 제품에 대한 할인율 적용하기
* 구매 후 취소 요청시 취소 처리 완료 까지 구현하기
* 상품 등록시 입력한 재고량을 이용해 품절 표시하기(현재 품절 표시 기능 없고 판매 중지로 상품 자체를 보여주지 않고 있음)








