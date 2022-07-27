![logo](https://user-images.githubusercontent.com/86868936/180720947-d2a9c43c-d7f9-43cf-ae7f-7b56c6727002.png)

# PROJECT. 라이브 커머스 웹사이트 클론 프로젝트💻

> 📅2021.05.16 - 07.20  
> 😃개발인원 : 1명


# 📌목차

## 💡1. 프로젝트 소개    
> 1-1.  [프로젝트 제작 계기 및 참고](#1-1-프로젝트-제작-계기-및-참고)   
> 1-2.  [프로젝트 주요 기능](#1-2-프로젝트-주요-기능)  
> 1-3.  [개발 환경](#1-3-개발-환경)  
> 1-4.  [아키텍처 다이어그램](#1-4-아키텍처-다이어그램)  
> 1-5.  [DB 모델링](#1-5-DB-모델링)  
> 1-6.  [사이트 주소 및 시연 영상](#1-6-사이트-주소-및-시연-영상)


## 💡2. 프로젝트 주요 기능
### 2-1. 로그인 서비스  
> 2-1-1. [로그인에 따른 권한 처리](#2-1-1-로그인에-따른-권한-처리)  
> 2-1-2. [OAuth2와 JPA를 이용한 SNS 로그인](#2-1-2-oauth2와-jpa를-이용한-sns-로그인)  
### 2-2. 라이브 서비스
> 2-2-1. [NGINX 설정하기](#2-2-1-NGINX-설정하기)  
> 2-2-2. [OBS 사용하기](#2-2-2-OBS-사용하기)  
> 2-2-3. [라이브 영상 출력](#2-2-3-라이브-영상-출력)  
> 2-2-4. [FFmpeg, FFprobe로 라이브 영상 추출해 S3에 올리기](#2-2-4-ffmpeg-ffprobe로-라이브-영상-추출해-s3에-올리기)  
> 2-2-5. [라이브 종료 후 추출한 영상 보여주기](#2-2-5-라이브-종료-후-추출한-영상-보여주기)
### 2-3. 채팅 서비스
> 2-3-1. [라이브 채팅방 설정](#2-3-1-라이브-채팅방-설정)  
> 2-3-2. [채팅방 인원수 표시](#2-3-2-채팅방-인원수-표시)  
> 2-3-3. [판매자 채팅](#2-3-3-판매자-채팅)
### 2-4. 메인/카테고리
> 2-4-1. [[홈] 탭](#2-4-1-홈-탭)  
> 2-4-2. [[우리동네라이브] 탭](#2-4-2-우리동네라이브-탭)
### 2-5. 상품 선택 및 장바구니
> 2-5-1. [상품 선택 및 장바구니](#2-5-1-상품-선택-및-장바구니)
### 2-6. 구매 서비스
> 2-6-1. [결제 API를 이용한 구매 서비스](#2-6-1-결제-api를-이용한-구매-서비스)
### 2-7. 마이페이지
> 2-7-1. [주문 목록](#2-7-1-주문-목록)
### 2-8. 판매자 관리툴
> 2-8-1. [라이브 등록: [우리동네라이브] 지역 설정](#2-8-1-라이브-등록-우리동네라이브-지역-설정)  
> 2-8-2. [판매글 등록: 상품 추가](#2-8-2-판매글-등록-상품-추가)  
> 2-8-3. [판매 내역 관리: 주문 취소](#2-8-3-판매-내역-관리-주문-취소)
## 💡3. 개발 후기
> 3-1. [추후 개발 목록](#3-1-추후-개발-목록)  
> 3-2. [개발 과정](#3-2-개발-과정)  
> 3-3. [개발 후기](#3-3-개발-후기)

---
<br>
<br>

# 📑**1. 프로젝트 소개**
<br>

## 🤗**1-1. 프로젝트 제작 계기 및 참고**
* 라이브 서비스를 구현해 보고 싶어서 처음에는 <u>우리 동네 홍보 스트리밍 서비스</u>를 계획했지만,  
 쇼핑과 결합된 라이브 커머스가 현재 큰 관심 중 하나라 생각하여 <u>라이브 커머스 웹 프로젝트</u>를 진행하기로 했습니다.
* 혼자 새롭게 기획하고 제작하기엔 많은 기간이 소요될 것 같아 **네이버 쇼핑 라이브** 사이트를 개인적으로 분석해 클론 코딩하였습니다.

* 프로젝트 내 상품 이미지와 영상 출처는  네이버 쇼핑라이브 사이트 입니다.
<br>
## 📌**1-2. 프로젝트 주요 기능**
 ### ⭕ **편의성과 소통성**
  * 실시간 스트리밍 영상과 채팅 서비스로 비대면이지만 판매자와 소비자 간의 실시간 소통이 가능하며,  
 직접 매장에 가지 않아도 상품을 상세하게 알 수 있고 즉시 구매가 가능합니다.

### ⭕ **효율성**
  * 실시간 방송이 끝나도 해당 영상을 다시 보여줌으로써 소비자에게 지속적인 서비스를 제공합니다.
  * 또한 라이브를 보면서 구매할 수 있게 하단에 제품글을 누르면 제품 소개 및 구매 창이 팝업 형식으로 구성되어 있습니다.

### ⭕ **제품 관리 편리성**
  * 판매글을 올렸을 때 동일한 상품에 대한 상품명과 금액을 동일시 하기 위해 제품 등록과 판매글 등록을 구분하였습니다.
  * 라이브 등록시 등록한 판매글을 선택해 동일한 판매글로 언제든지 라이브를 할 수 있습니다.

### ⭕ **지역 홍보**
  * 소비자가 '우리동네라이브' 탭에 접속하면 소비자 위지 정보와 동일한 판매 글을 보여줌으로써,  
해당 지역 가게 홍보에도 도움이 되는 서비스를 제공합니다.

<br>

## 🧰**1-3. 개발 환경** 
### **Front-end**
<img src="https://img.shields.io/badge/HTML-FF911E?style=flat-square&logo=HTML5&logoColor=white"/> <img src="https://img.shields.io/badge/CSS3-FF7965?style=flat-square&logo=CSS3&logoColor=white"/> <img src="https://img.shields.io/badge/Javascript-FF4986?style=flat-square&logo=Javascript&logoColor=white"/> <img src="https://img.shields.io/badge/jQuery-FF26AC?style=flat-square&logo=jQuery&logoColor=white"/> 

* 웹 문서의 뼈대를 구축해 디자인하고 이벤트를 넣어 웹 페이지를 구현/ 관리합니다.

### **Back-end**
<img src="https://img.shields.io/badge/JAVA-0DCCEB?style=flat-square&logo=java&logoColor=white&width=300">  <img src="https://img.shields.io/badge/Spring Boot-008CFF?style=flat-square&logo=SpringBoot&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Security-5C2DEB?style=flat-square&logo=Spring Security&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Data JAP-9E6AEB?style=flat-square&logo=Spring Data JPA&logoColor=white"/> <img src="https://img.shields.io/badge/Mybatis-B73FEB?style=flat-square&logo=&logoColor=white"/> <img src="https://img.shields.io/badge/Apache Tomcat-E16AEB?style=flat-square&logo=ApacheTomcat&logoColor=white"/> 
* 웹 애플리케이션 서버와 보안을 구축하고 라이브러리들의 버전 관리를 자동적으로 관리합니다.
* 기능별 모듈 분리로 유연한 구조를 가지고 있습니다.
* 일부분은 **JPA**로 객체 지향 로직으로 개발해 보다 효율적으로 관리합니다. 

### **Database**
<img src="https://img.shields.io/badge/MySQL-008CFF?style=flat-square&logo=MySQL&logoColor=white"/>

* AWS RDS를 프리티어로 사용하기 위해 **MySQL**을 사용합니다.

### **Cloud Infrastructure**
<img src="https://img.shields.io/badge/Amazon AWS-00A1FF?style=flat-square&logo=AmazonAWS&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon ECS-008CFF?style=flat-square&logo=Amazon ECS&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon S3-006BFF?style=flat-square&logo=Amazon S3&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon RDS-4026FF?style=flat-square&logo=Amazon RDS&logoColor=white"/> 
* **EC2**로 서버를 구축해 프로젝트를 배포합니다.
* **S3**과 **RDS**로 프로젝트 내의 파일과 DB를 관리합니다.

### **Streaming Service**
<img src="https://img.shields.io/badge/NGINX-2DD151?style=flat-square&logo=NGINX&logoColor=white"/> <img src="https://img.shields.io/badge/FFmpeg-2DB581?style=flat-square&logo=FFmpeg&logoColor=white"/> <img src="https://img.shields.io/badge/HLS-2D5B81?style=flat-square&logo=&logoColor=white"/>

* **NGINX**의 외부 모듈 RTMP을 이용해 스트리밍 서버를 구축합니다.
* **FFmpeg**로 스트리밍 영상을 인코딩해 재생 가능한 영상 파일로 저장합니다.
* **HLS**를 이용해 스트리밍 서버에 접속해 웹에 오디오 및 비디오를 배포합니다.

### **Chatting Service**
<img src="https://img.shields.io/badge/SockJS-FF4986?style=flat-square&logo=&logoColor=white"/> <img src="https://img.shields.io/badge/STOMP-FF911E?style=flat-square&logo=&logoColor=white"/>

* **SockJS**로 WebSocket을 사용합니다.
* **STOMP**를 서브 프로토콜로 사용해 멀티 채팅 기능을 제공합니다.

<br>

## 🦄**1-4. 아키텍처 다이어그램**
![onl-System-Architecture-Diagram3](https://user-images.githubusercontent.com/86868936/181180955-e0682750-2e66-42c9-8f43-715e939910a3.png)



<br>

## 💾**1-5. DB 모델링**
![onlshop](https://user-images.githubusercontent.com/86868936/180734739-7bad65b1-4a66-4372-8c62-3d28f1fc503d.png)

<br>

## 🌏**1-6. 사이트 주소 및 시연 영상**

🌏구현 사이트 주소 : https://onlshop.shop

❓시연 영상 : 준비중


---
<br>
<br>

# 📌2. 프로젝트 주요 기능 설명
<br>

## 👉**2-1. 로그인 서비스**
* 로그인은 form 로그인과 OAuth2 로그인을 사용하였습니다.
* 로그인 아이디 저장 또는 자동 로그인을 선택해 적용할 수 있지만 SNS를 통한 로그인은 불가합니다.
* 또한 아이디-비밀번호 찾기 기능도 SNS를 통한 로그인은 접속한 SNS에서 가능합니다.

![login](https://user-images.githubusercontent.com/86868936/180745722-3a9e3a27-1ee0-472f-9a28-b703615027ac.png)

<br>

### 💚**2-1-1. 로그인에 따른 권한 처리**
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
<br>

### 💙**2-1-2. OAuth2와 JPA를 이용한 SNS 로그인**
* OAuth2UserService 통해 가져온 OAuth2User의 attribute를 담을 클래스를 생성합니다.
* 로그인 하려는 SNS(네이버, 카카오)를 구분해 계정 정보를 받아와 UserVo같은 형태로 값을 넣어 OAuth2UserService를 상속 받는 클래스로 전달합니다.
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
* 넘어오는 SNS 계정 정보와 DB 값을 비교해 데이터가 없으면 JPA로 유저 데이터를 DB에 등록해 회원가입 처리합니다.
```java
...
//회원가입
@Modifying 
@Query(value="INSERT INTO USER_INFO (USER_ID,USER_PW,USER_UNAME,USER_NICKNAME,USER_PLATFORM,USER_ROLE)VALUES(#{userId},#{userPw},#{userUname},#{userNickname},#{userPlatform},#{userRole}",nativeQuery = true)
int insertUserInfo(@Param("userId")String userId,@Param("userPw")String userPw,@Param("userUname")String userUname
            ,@Param("userNickname")String userNickname,@Param("userPlatform")String userPlatform,@Param("userRole")String userRole);
```
* UserVo에 값을 넣어 유저 정보를 return 하여 form로그인과 OAuth2로그인시 저장하는 계정 정보를 동일한 코드로 사용할 수 있게 했습니다.
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
<br>

## 🎥 **2-2. 라이브 서비스**
* OBS와 연결할 서버를 위해 NGINX에 RTMP를 추가해 사용합니다.
* HTML5의 Video태그로 스트림을 받아 영상을 재생시킬 수 있게 HLS를 사용합니다. 
* 실시간 영상을 종료 후에도 보여줄 수 있게 FFmpeg(미디어 인코딩/디코딩)와 FFprobe(멀티미디어 스트림 정보 출력)를 이용해 라이브 영상을 추출하고 추출한 영상을 AWS S3에 저장합니다.

![live_gif](https://user-images.githubusercontent.com/86868936/180750910-a41f9a70-0f82-4a23-871b-cca2f23753c1.gif)

<br>

### 🍒**2-2-1. NGINX 설정하기**
* ubuntu에 원하는 버전의 NGINX를 다운 받아 컴파일러해 설치하고 RTMP, FFmpeg, HLS 사용을 위한 환경 설정을 수정합니다.
* NGINX-RTMP 파일 
  * https://github.com/jh-2022-github/nginx-rtmp-module 
* NGINX의 conf 파일에 대한 자세한 설명은 아래 주소 참고 
  * https://github.com/dreamsxin/nginx-rtmp-wiki/blob/master/Directives.md#record_path

<br>

### 🍑**2-2-2. OBS 사용하기**
* 설정 -> 방송 탭에서 접속할 서버와 방송 열쇠를 입력합니다.

<img width="565" alt="obs" src="https://user-images.githubusercontent.com/86868936/180751129-a12d6ad5-b37e-445b-8b92-67bd091c8c80.png">

- 서버: `rtmp://IP주소 또는 도메인 주소/live`   
- 방송 열쇠: `판매자에게 부여된 스트림키` 

<br>

### 🍋**2-2-3. 라이브 영상 출력**
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
<br>

### 🍏**2-2-4. FFmpeg, FFprobe로 라이브 영상 추출해 S3에 올리기**
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
<br>

### 🍉**2-2-5. 라이브 종료 후 추출한 영상 보여주기**
* 라이브 영상을 저장해 라이브 종료 후에도 계속 보여줄 수 있게 S3에 있는 영상을 주소를 입력해 지속적인 서비스를 제공합니다.
```java
<video class="VideoPlayer_video replayVideo" autoplay muted loop poster="https://s3.ap-northeast-2.amazonaws.com/S3버킷명/저장된 경로/파일명>
      <source src="https://s3.ap-northeast-2.amazonaws.com/S3버킷명/저장된 경로/파일명" type="video/mp4">
</video>
```
<br>

## 👨‍👩‍👧‍👦**2-3. 채팅 서비스**
* 브라우저에 상관 없이 여러 개의 채팅방을 개설해 메세지를 효율적으로 전달할 수 있게 SockJS와 STOMP를 사용합니다.
* 채팅방의 누적 뷰 수는 채팅방 입장 시 +1로 등록하고 수정된 뷰 수 데이터를 가져옵니다.
* 판매자는 채팅 입력 페이지가 따로 존재하며 소비자의 글씨 색과는 다르게 하여 구분지어 줍니다.

![라이브및채팅](https://user-images.githubusercontent.com/86868936/180862087-4a4bbdcf-e5b0-4dfa-8054-ca205c5a3354.png)

<br>

### ⚽**2-3-1. 라이브 채팅방 설정**
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
<br>

### 🙌**2-3-2. 채팅방 인원수 표시**
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
<br>

### 😎**2-3-3. 판매자 채팅**
* 판매자는 라이브 관리페이지 안에서 채팅을 관리할 수 있습니다.
* 판매자 채팅 글은 다른 색으로 표시하여 소비자와 구분지어 보여줍니다.
```javascript
client.connect({}, function(frame){
        //subscribe(path, callback)으로 메세지 받기
        client.subscribe("/sub/chat/room/"+roomId,function(chat){   // 서버 연결
            var content = JSON.parse(chat.body);
            var writer = content.writer;
            var msg = content.message;
            var str = '';    
            if(writer == "master_user_seller"){             
                str = "<div class='Comments_master'>";   //판매자 채팅
                str +="<span class='Comment_comment_master'>"+msg+"</span>";
                str +="</div>";            	
            }else{                                    
                str = "<div class='Comment_user'>";     //회원 채팅
                str +="<strong class='Comment_id'>"+writer+"</strong>";
                str +="<span class='Comment_comment'>"+msg+"</span>";
                str +="</div>";            	
            }
        ...
        });   
```
<br>


## 🏠**2-4. 메인/카테고리**
* **[홈]** 탭에서는 현재 실시간 방송 중인 라이브를 보여주는**⚡NOW⚡**
  , 현재 날짜 이후 등록된 라이브의 **📆라이브 예고**
  , 현재 날짜 이전 주의 라이브 뷰 수 **TOP 10✨**으로 구성되어있습니다.
* **[라이브]** 탭에서는 현재 실시간 방송중인 라이브를 보여줍니다.
* **[우리동네라이브]** 탭에서는 유저의 지역과 라이브 등록에 설정한 지역과 일치하는 라이브를 보여줍니다. 
* 나머지 카테고리 탭은 등록된 라이브의 카테고리에 맞춰 라이브를 보여줍니다. 

<br>

### 🌞**2-4-1. [홈] 탭**
* **⚡NOW⚡**: 등록된 시작 날짜와 시간 <= 현재 날짜와 시간 >= 등록된 종료 날짜와 시간 에 해당되고 라이브 상태가 라이브 중이면 데이터를 가져옵니다. 
```
SELECT 
...
FROM LIVE_INFO LI
...
WHERE CONCAT(DATE_FORMAT(LI.LIVE_START_DAY,'%Y-%m-%d'),DATE_FORMAT(LI.LIVE_START_TIME,' %H:%i')) <=  DATE_FORMAT(now(),'%Y-%m-%d %H:%i') 
	         AND CONCAT(DATE_FORMAT(LI.LIVE_START_DAY,'%Y-%m-%d'),DATE_FORMAT(LI.LIVE_END_TIME,' %H:%i')) >= DATE_FORMAT(now(),'%Y-%m-%d %H:%i')]]>
	         AND LIVE_STATUS = '1'
```
* **📆라이브 예고**: 등록된 시작 날짜 > 현재 날짜에 해당되는 라이브 데이터를 4개만 가져옵니다. 
```
SELECT 
...
FROM LIVE_INFO LI
...
WHERE DATE_FORMAT(LI.LIVE_START_DAY,'%Y-%m-%d') > DATE_FORMAT(now(),'%Y-%m-%d')
Limit 4
```
* **TOP 10✨**:  현재 날짜의 이전 주 7일(일-토)에 뷰 수가 제일 높은 순으로 데이터 10개 가져오기
```
SELECT 
...
FROM LIVE_INFO LI
...
WHERE LI.LIVE_START_DAY >=  now() - INTERVAL DAYOFWEEK(now())+6 DAY
	AND LI.LIVE_START_DAY < now() - INTERVAL DAYOFWEEK(now()) DAY  
ORDER BY LI.LIVE_VIEW desc
Limit 10

```

<br>

### 🏘️**2-4-2. [우리동네라이브] 탭**
*  **[우리동네라이브]** 탭을 클릭하면 위치 정보 동의 여부 확인 후 카카오 지도 API로 현재 접속한 지역 코드를 전송해 해당 지역으로 등록된 라이브를 보여줍니다.
 ```
//카카오 지도 API
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=카카오JavaScript 키&libraries=services"></script>
```
```
SELECT
...
FROM LIVE_INFO LI 
	JOIN REGION_ADDR RA
	  ON LI.REGION_1_CODE = RA.REGION_1_CODE
WHERE RA.REGION_1_NAME LIKE '%서울%'			#접속자의 지역에 일치하는 라이브 보여주기
```
<br>

## 🛒**2-5. 상품 선택 및 장바구니**
* 상품 라이브 영상을 보면서 제품을 고를 수 있게 상품 선택은 iframe을 활용해 팝업으로 보여줍니다.
* 장바구니 페이지에서는 상품의 수량 변경과 삭제, 선택 기능이 있고 선택 버튼은 판매글 기준으로 선택이 가능합니다.
* 또한 판매자로 구분지어 배송비를 보여주며 선택한 상품의 금액 총 합과 배송비 총 합, 결제할 총 금액을 보여줍니다.

![장바구니-2](https://user-images.githubusercontent.com/86868936/180862189-0ce7d3db-68b4-4be9-9b48-72dd78288b21.png)

<br>

### 🎁**2-5-1. 상품 선택 및 장바구니**
* 장바구니 테이블의 유저ID와 판매글 ID, 상품ID를 PK로 생성해 한 유저의 같은 판매글의 같은 상품이 중복으로 등록되지 않게 합니다.
```
create table cart(
    user_id      varchar(60)  not null,           #유저ID
    ps_index     varchar(60)  not null,           #판매글ID
    pd_id        varchar(60)  not null,           #상품ID
    pd_count     int          not null,           #상품 수량
    cart_date    DATETIME     DEFAULT now(),      #등록날짜     
   primary key(user_id,ps_index,pd_id)
);
```

<br>

## 💸**2-6. 구매 서비스**
* my page에서 배송지 관리에 주소를 여러 개 등록해 놓으면 구매 페이지에서 주소를 변경해 주문할 수 있습니다.
* 결제 API를 사용해 신용카드, 카카오페이, 토스페이를 사용해 결제가 가능합니다. (현재는 테스트 결제 상태입니다.)

![구매](https://user-images.githubusercontent.com/86868936/180862238-400d7960-c8b9-4990-a682-9d1ecd513c7b.png)

<br>

### 💳**2-6-1. 결제 API를 이용한 구매 서비스**
* Iamport API를 사용해 선택한 결제 방식으로 결제를 진행해 성공하면 데이터 등록 후 결제 완료 페이지로 이동합니다.
* 결제에 실패하면 실패 alert창을 띄우고 페이지를 새로고침합니다.
```javascript

IMP.init('imp40385990');                                //가맹점 식별 코드
            IMP.request_pay({
                pg: paymentCode,                        //결제 pg 선택 (받아온 값으로 변경)
                pay_method : 'card',                    //결제 방식 - 생략 가능
                merchant_uid : orderIndex,              //상점에서 관리하는 주문번호
                name : psTitle,                         //주문명
                amount : totalPrice,                    //실제 결제되는 가격
                buyer_email : email,                    //구매자 이메일
                buyer_name : orderName,                 //구매자 이름
                buyer_tel : tel,                        //구매자 전화번호
                buyer_addr : "주소등록",                   
                buyer_postcode: "우편번호"
            },function(rsp){
                if(rsp.success){
                    var param = new Object();                
                    param.orderPdSelVo = psList;
                    param.orderPdVo = pdList;
                    param.paymentVo = {'paymentType' : paymentCode,'totalPdPrice':totalPdPrice,'totalDelivery':totalDelivery,'totalPrice':totalPrice};                  
                    param.orderId = orderIndex;
                    param.addrIndex = addrNum;   //주소코드
                    param.paymentStatus = 21     //결제상태
                    var option = new Object();
                    option.data = JSON.stringify(param);
                    option.contentType = "json";
                    option.success = function(v){
                       //결제 성공하면 성공 페이지로 이동
                       location.href = "/orderComplete/"+orderIndex+"/"+totalPrice;   
                    }
                    _ajax.ajaxData("/orderConfirm?fromCart="+fromCart,"POST",option);
                }else{
                    //결제 실패시 alert창 -> 페이지 새로고침
                    alert(rsp.error_msg);
                    location.reload();
                }
            })
```

<br>

## 😀**2-7. 마이페이지**
* 마이페이지는 구매내역, 배송지 관리, 회원정보 수정, 비밀번호 변경 서비스를 제공합니다.

![마이페이지](https://user-images.githubusercontent.com/86868936/180857120-7a3d6691-df0f-4ce3-bd45-455621a7ecb1.png)

<br>

### 📃**2-7-1. 주문 목록**
* 상단에 주문 상태 별 수는 <u>한 달 기준</u>으로 출력되며 숫자를 누르면 해당 상품 리스트를 보여줍니다.
* 표시된 날짜를 누르면 해당 날짜에 주문한 상품 리스트가 출력됩니다.
```
SELECT
...
FROM   ORDERS
...
WHERE  USER_ID = '유저ID'       #유저ID                           
  AND  DATE_FORMAT(DATE_ADD(ORD.ORDER_DATE,interval 1 month),'%Y-%m-%d') #현재 날짜 기준으로부터 한달 전 까지
  AND  payment_status = '21'   #결제 상태
  AND  order_status IS NULL    #주문 상태
```

<br>

## 🛠️**2-8. 판매자 관리툴**
* 라이브 관리, 상품 관리, 판매글 관리, 판매 내역 관리, 설정 탭으로 이루어져 있으며 각 해당 탭에서 등록, 수정, 삭제가 가능합니다.
* 상품 관리와 판매글 관리를 나누어 동일한 상품의 상품명과 가격에 혼란을 주지 않습니다.
* 라이브 관리에서 방송 시간을 설정할 수 있으며 라이브 지역을 선택해 **[우리동네라이브]** 탭에 등록할 수 있습니다.
* 설정 탭에서는 판매자의 프로필 사진과 이름을 변경할 수 있으며, 스트림키를 제공합니다.  

![관리툴](https://user-images.githubusercontent.com/86868936/180913182-a1e2f30e-159b-4b5b-a510-cba91bc0cf08.png)

<br>

### 🍔**2-8-1. 라이브 등록: [우리동네라이브] 지역 설정**
* 라이브 등록에서 **[우리동네라이브]지역 설정**에 지역을 선택해 등록합니다.
* 미선택시 '전국'이 기본값이 됩니다.  
![image](https://user-images.githubusercontent.com/86868936/180940478-f5eb0ecb-8445-4ef2-afef-3a621c907485.png)

<br>

### 🍭**2-8-2. 판매글 등록: 상품 추가**
* 같은 상품을 여러 판매글로 등록시 '판매 상품 추가' 목록에서 상품을 체크한 후 등록하면 해당 상품에 대해 등록한 여러 판매글에 동일한 정보를 제공합니다.  
![상품 추가](https://user-images.githubusercontent.com/86868936/180941774-3cc6df93-1975-4853-8d97-3f6c96021566.png)
* foreach 문을 사용해 판매글에 추가되는 상품 list를 PRODUCT_POST_ADD table에 등록합니다.  

```
<insert id="createAddPdList" >
        /*AdminMapper.createAddPdList*/
        INSERT INTO PRODUCT_POST_ADD(
            SEL_ID
            ,PS_INDEX	# 판매글 ID
            ,PD_ID		# 상품 ID
        )VALUES
	<foreach collection="pdPostAddVo" item="item"  separator=",">
        (
            #{selId}
            ,#{psIndex}
            ,#{item.pdId}                            
        )
	</foreach>  
</insert>
```
<br>

### 🍟**2-8-3. 판매 내역 관리: 주문 취소**
* 선택한 주문을 취소시 같은 판매글의 하나의 상품만 취소 될 때 배송비를 제외한 상품 금액만 취소합니다.
* 선택한 주무번호(orderId)와 판매글ID(psIndex)로 총 개수(주문개수 아니고 주문 코드개수)를 구합니다.
* 총 개수에 주문 개수를 빼서 1이상(해당 판매글에 주문한 상품이 하나 이상 존재하면)이면 배송비를 추가하지 않습니다.
* 0이면 해당 상품 주문 금액에 배송비를 추가해 최종 금액으로 취소 처리합니다.
* 주문이 취소된 상품은 결제 상태 및 주문 상태를 변경할 수 없습니다.  
<img width="688" alt="상품취소" src="https://user-images.githubusercontent.com/86868936/180945547-cf1ada37-bdda-4228-b591-80f9e63b572c.png">

```java
//선택한 주무번호(orderId)와 판매글ID(psIndex)로 총 개수(주문개수 아니고 주문 코드개수)를 구하고
//총 개수에 주문 개수를 빼서 1이상이면 배송비를 추가하지 않고
//0이면 배송비를 보여주며 해당 상품 주문 금액에 배송비를 추가해 최종적으로 보여준다
//orderId, cancelOrderPrice 출력
List<OrderVo.CancelOrderVo> cancelOrderList = new ArrayList<OrderVo.CancelOrderVo>();
List<OrderVo.CancelOrderVo> svo = adminMapper.getCancelTotalPrice(salesVo);
for(int i =0;i<svo.size();i++) {
  OrderVo.CancelOrderVo cancelOrderVo = new OrderVo.CancelOrderVo();
  cancelOrderVo.setCancelOrderId(cancelOrderId);
  cancelOrderVo.setOrderId(svo.get(i).getOrderId());
  cancelOrderVo.setCancelOrderPrice(svo.get(i).getCancelOrderPrice());
  cancelOrderList.add(cancelOrderVo);
}
//취소 db 등록
adminMapper.insertCancelOrderSeller(cancelOrderList);
//주문 취소로 수정
return adminMapper.cancelOrderProcess(salesVo);
```

---
<br>

# 🎊**3. 개발 후기**
<br>

## 🎢**3-1. 추후 개발 목록**
* **채팅 로그를 저장**(NoSql사용)해 이전 대화도 볼 수 있게 하기
* 채팅 ID를 판매자별 1개가 아닌 **라이브당 채팅 ID 1개를 부여**해서 채팅 로그를 관리할 수 있게 하기
* 제품에 대한 할인율 적용하기
* 구매 후 취소 요청시 취소 처리 완료 까지 구현하기
* 상품 등록시 입력한 재고량을 이용해 **품절 표시**하기(현재 품절 표시 기능 없고 '판매 중지'로 처리함)
* 판매글에 **지정 구매 금액 이상시 배송비 무료 기능** 추가하기
* 장바구니/ 구매페이지의 배송비 처리를 다른 방식으로 수정하기
* 판매글 등록시 상품 추가 단에 **[상품 찾기]** 기능 추가하기

<br>

## 🤓**3-2. 개발 과정**

### [개발 전]
* 라이브 서비스에 관심이 생겨 처음에는 간단하게 영상 출력과 지도 위치만 표시하는 **우리동네 가게 홍보 사이트**를 기획했는데 <u>네이버쇼핑라이브</u> 사이트를 보고 해당 사이트를 참고해 라이브커머스 웹서비스를 제작하려고 하게 되었습니다.
* 하지만 스트리밍 서비스와 JPA, 멀티 모듈 등의 새로 알게 된 기능 학습에 생각보다 시간을 많이 소요하게 되어 기획과 디자인까지 새로 구상하면 더 오래 걸릴 듯 하여 클론 코딩을 하게 되었습니다.

### [스트리밍 개발]
* 스트리밍 서비스에 대한 지식이 전무해 트위치와 유튜브, 카카오TV, 아프리카TV를 참고했는데 카카오TV, 아프리카TV는 따로 프로그램을 설치하기에 패스하고 트위치와 유튜브가 OBS로 라이브를 한다는 것을 알게 되었습니다.
* OBS에서 웹으로 송출 할 때 NGINX로 서버를 구축한다 까지는 바로 알 수 있었지만 OBS에 주소 설정하고 NGINX를 실행해도 방송 연결이 되지 않았습니다. 그래서 다른 관점으로 검색해 NGINX에 RTMP가 적용된 git주소를 알게 되었고 다운 받아 실행하니 연결이 되었습니다.
* 그 후 NGINX 환경 설정 및 연결 주소를 확인해 임시 저장되는 스트리밍 영상 주소를 hls로 받아와 웹으로 송출하고 해당 영상을 저장하기까지 구현하게 되었습니다.
* hls와 ffmpeg 사용은 큰 문제 없이 적용했습니다.

### [SNS로그인-JPA 사용]
* SNS로그인 사용을 위해 이전 프로젝트와 동일한 방식으로 구현할까 하다 현재는 어떤 방식으로 처리하는지 궁금해서 검색해보니 SNS로그인은 OAuth2를 사용하고 JPA를 사용해 데이터를 처리하는 방식이 요즘 선호하는 방식이라는 것을 알게 되었습니다.
* OAuth2와 JPA를 이용한 SNS로그인 처리에 대한 글은 생각보다 많았고 JPA 사용은 크게 어렵지 않았습니다. 하지만 회원가입 처리나 [form 로그인-Mybatis]와 혼용해 로그인 했을 때 Authentication에 동일한 데이터 형식의 유저 정보를 담는 방법이 어디에도 없어 이 때부터 다시 머리 싸매기 시작했습니다. 
* UserVo가 OAuth2User를 상속 받고 UserVo에 빌더를 추가해 UserVo 형태로 데이터를 받을 수 있게 해 로그인한 유저 정보를 동일한 형태로 보여지게 하였습니다.

### [장바구니/구매 배송비 처리]
* 이전 국비 수업에서 쇼핑몰 사이트 팀 프로젝트를 진행 했을 때 장바구니 페이지를 담당했었는데 그 때는 판매자가 1인이라 배송비 처리에 크게 어려움이 없었는데 이번 프로젝트에서는 업체 별로 배송비를 합쳐 보여주고 최종 배송비 표시까지 
* 현재 구현된 방법이 좋은 방법은 아닌듯하지만 일단 같은 판매자끼리 묶었을 때 배송비를 합해서 마지막에 보여주게 처리했으며,  판매자가 바뀌면 배송비 합계 처리를 초기화하고 다시 합하는 구조로 설계했습니다.
* 배송비만 따로 합계를 표시하는 부분은 js에서 처리하였는데 추후에 개선해야 할 것 같습니다.   

### [상품/판매글 분리]
* 처음부터 상품과 판매글을 분리해서 등록해 상품을 관리할 수 있기 계획했는데 네이버스토어를 참고해보니 상품등록이 판매글 등록이였다. 처음에는 제가 생각한 방식도 나쁘지 않다고 생각했지만 올린 상품중 판매중인 상품이 너무 많으면 찾기가 어렵다는 단점이 있었습니다.
* 상품을 판매 중지로 설정해 보이지 않게 하는 방법도 있지만 판매글 등록에 상품 추가 단락에 상품 찾기 기능을 넣어 가독성과 편의성을 높여야 할 것 같습니다.

### [주문 취소 처리]
* 프로젝트에 너무 많은 시간을 소요해 주문 취소 처리는 그냥 상태 변경만 하려고 했는데 해당 부분을 처리하다 보니 취소 금액 처리까지 하게 되었습니다.
* 일단 처음에 상품 주문시 주문번호만 생성한 상태여서 선택한 상품 주문 취소 처리가 아주 복잡하게 진행될게 보여 주문상품번호를 추가해 데이터 처리를 쉽게 처리하였지만 가장 큰 문제인 배송비가 또 발목을 잡았습니다.
* 이 부분을 어떻게 처리하는지 알 수 없어서 주문 취소할 상품의 같은 주문 번호에 해당 상품을 제외한 데이터가 존재하면 배송비를 포함하지 않는 방식으로 처리하였습니다. 나중에 좋은 방식을 찾게 되면 수정할 예정입니다.

### [AWS]
* 프로젝트가 어느 정도 마무리를 짓게 되어 AWS를 통해 웹 서버를 구축을 시작했습니다.
* 이전에 AWS로 웹 서버를 구축하고 배포했기에 여기서 온갖 문제가 발생할 줄은 몰랐습니다. 

* **첫번째 문제-[git으로 배포하기]**
	* 해당 방법으로 자세하게 올린 글을 발견해 따라서 진행했는데 ubuntu에 CodeDeploy  Agent 설치가 안된다. 이유가 'ubuntu 22.04는 ruby 3.x를 지원하는데 codeDeploy 패키지는 ruby 2.x 설치 확인을 하기 때문' 이였다.
무슨 열정인지 참 이걸 해결해보겠다고 하루 종일 찾았다. 바보같이.. 결국 해결 법을 찾아 설치했다.
	* 다음으로 Github Actions Workflow로 deploy.yml을 작성했는데 AWS S3에 올라가질 않는다.. 오류난 부분을 지우고 해보니 올라가는데 CodeDeploy 실행이 되지 않았다. 알고보니 게시글은 gradle, 내 프로젝트는 maven 이였고 오류난 부분도 그 부분이였다.
	* maven 버전으로 올렸지만 배포 스크립트에서 내 프로젝트를 못 찾았다. 배포 스크립트가 해당 프로젝트 내에 있어야 하는데 내 프로젝트는 common과 front로 나뉜 상태에서 root에 폴더로 올라간 상태였다. 하다하다 안되서 포기하고 이전 방식으로 진행했다.
* **두번째 문제-[멀티 모듈 설계]**
	* 이전 방식으로 배포하려고 war 파일로 빌드 했는데 안된다.  front에서 common 클래스들 심볼이 없다는 오류가 발생했다. 검색해도 안나온다. 그래서 그냥 두 파일을 합쳤다. 
	* 합친 상태에서 계속 진행하다 멀티 모듈을 알게 되었고 내가 프로젝트를 나눈건 부모 프로젝트 없이 jar로만 연결해서 안되었던 것이다. 알아보지도 않고 이전 회사에서 나눠서 개발한 것을 보고 입사 초반에 혼자 비슷한 형태로 만들어 본 것이 독이였다.
	* 여러 글과 유튜브 영상을 참고해서 멀티 모듈로 생성했지만 빌드 오류가 자꾸 발생해 오류에 따라 여차저차 수정하다보니  멀티모듈로 빌드 파일이 생성되었다.
* **세번째 문제-[Oracle 프리티어 버전 없음]**
	* DB는 Oracle을 사용했는데 이전 프로젝트 배포할 때 Oracle 프리티어 버전이 높은 버전이여서 최신 버전으로 개발했는데 오라클에서 클라우드 서비스를 시작해서 그런지 프리티어 버전이 아예 사라졌다.
	* 당황했지만 RDS를 사용하지 않고 내 컴퓨터에서 ubuntu에 설치된 오라클로 접속하려고 했는데 안된다.
	* 최후의 수단으로 DB를 MySQL로 변경했다. 그로 인해 쿼리를 변경하는데 날짜 데이터 처리에 문제가 생겼다. 처음엔 날짜 쿼리가 문제인가 싶어서 계속 수정했는데 DB 서버 시간을 바꾼 기억이 없어 확인해보니 UTC로 되어있었다.
	* 서버 시간을 'Asia/Seoul'변경해도 끄고 다시 키면 UTC로 되돌아온다. Springboot에서 DB주소 설정에도 추가했지만 안된다. 그래서 임시로 현재 날짜 비교 쿼리에 now() 대신에 Service에서 현재 날짜를 구해 넣는 방식으로 바꾸었다.
	* 현재 이 글을 쓰면서 문득 든 생각이 RDS에서 서버 시간을 안 바꾼것 같아 확인해보니 역시나.. 'Asia/Seoul'으로 변경하지 않았다. RDS에서 time_zone을 변경하고 DB 서버 시간도 변경해주고 현재 날짜를 받는 쿼리를 다시 수정했다.

<br>

## 🎉**3-3. 개발 후기**
 5개월 다닌 직장 퇴사 후 잠시 다른 일을 했지만 중간에 네이버 부스트코스에서 인공지능 기초 코칭 스터디에 참여도 하고 개발에 완전히 손은 놓지 않으려 했다. 하지만 잠시가 몇 개월로 이어지면서 잘못됨을 느껴 하고 있던 일을 정리하고 다시 개발을 시작하기 위해 이번 프로젝트를 진행하게 되었다.

  이번 프로젝트로 정말 많은 것을 배울 수 있었다. 하지만  혼자서 검색에 의존해 배우기엔 이해하기 너무 힘들었고 너무 많은 시간을 소요했다. 생소한 것을 많이 접해 꽤 만족한 프로젝트였는데 README를 작성하니 많이 부족해 보인다. 
 그리고 타 사이트를 클론 코딩해서 그런지 나만의 웹이라는 느낌이 부족해 노력에 비해 내가 느끼는 만족감이 살짝 부족하다. 

추가로 git 관리를 안해서 잔디밭이 없다. 바보다. 나는 바보다. 완전 바보다. 잔디밭이 없어 볼 때마다 눈물 난다. 
앞으로 핸드폰 말고 잔디밭을 꾸미는데 신경써야겠다.

  이제 면접과 코딩 테스트를 준비 해야 하는데 나이가 많고 면접에 약한 편이라 어떻게 될지 걱정이 이만저만이 아니다.  
 그래도 입사할 때까지 열심히 준비하다 보면 좋은 결과가 올 것이라 생각한다. 

 이제 회사에 입사해 토이 프로젝트를 시작하고 싶고 팀 프로젝트도 해보고 싶다. 혼자 개발하는 것이 편하긴 하지만 git관리도 안하게 되고 일정도 자꾸 바뀌게 된다. 또 프로젝트 개발에 문제점과 개선점을 혼자 고민해봤자 새로운 대안이 나오긴 힘들었고 나의 부족한 능력을 신입인 내가 알아서 채워야 한다는 것이 참 어려웠다. 

이번 프로젝트가 내가 다시 개발자로 전환하는 좋은 계기가 되었으면 좋겠다.
