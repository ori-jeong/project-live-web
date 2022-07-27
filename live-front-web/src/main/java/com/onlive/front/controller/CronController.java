package com.onlive.front.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlive.common.service.AwsS3Service;
import com.onlive.common.service.LiveService;
import com.onlive.common.vo.LiveVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CronController {
    private final LiveService liveService;
    private final AwsS3Service awsS3Service;
    
    @Value("${onl.video.url}")
    private String url;
    @Value("${onl.video.ffmpeg}")
    private String ffmpegUrl;
    @Value("${onl.video.ffprobe}")
    private String ffprobeUrl;
    @Value("${onl.video.uploadFolderUrl}")
    private String uploadFolderUrl;

    @Scheduled(cron="0 0/1 * * * *")
    //@RequestMapping("/cron/video")
    public void liveVideoSave() throws IOException, ParseException{
        //현재 라이브 중인 라이브 상태 변경(대기->라이브중, 라이브중-> 종료)
        liveService.updateLiveStatus();
        //현재 라이브 중이면서 영상 파일 id가 없는 라이브 정보 가져오기
        List<LiveVo.LiveVideoVo> live = liveService.getLiveStreamInfo();
        List<LiveVo.LiveVideoVo> video = new ArrayList<LiveVo.LiveVideoVo>();
        
        FFmpeg ffmpeg = new FFmpeg(ffmpegUrl);
        FFprobe ffprobe = new FFprobe(ffprobeUrl);
        String uploadFolderPath = getFolder();          //날짜경로 만들기
        String uploadFolder = uploadFolderUrl;
        File uploadPath = new File(uploadFolder, uploadFolderPath); //폴더 경로 설정
        if (uploadPath.exists() == false) {                         //해당 경로에 폴더 존재 확인
            uploadPath.mkdirs();
        }
        LiveVo.LiveVideoVo liveVideoVo = new LiveVo.LiveVideoVo();
        for(int i =0;i<live.size();i++) {            
            String key = live.get(i).getSelStreamKey();
            String liveId = createVideoName(live.get(i).getLiveId());
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
            liveVideoVo.setLiveId(live.get(i).getLiveId());
            liveVideoVo.setVideoId(liveId.substring(1));
            liveVideoVo.setVideoPath(uploadFolderPath);
            liveVideoVo.setVideoName(liveId+".mp4");
            video.add(i, liveVideoVo);

            File file = new File(uploadFolderUrl+uploadFolderPath+liveVideoVo.getVideoName());
            awsS3Service.uploadFile(file, uploadFolderPath,liveId.substring(1)+".mp4"); //aws에 저장
            
        }
        if(video.size()!=0) {
            //영상 정보 저장하기(영상 일부만 저장)
            liveService.setLiveVideoInfo(video);
        }
    }
    
    //폴더경로생성
    private String getFolder() {
        //"yyyy-mm-dd" 년월일
        LocalDate  nowDate = LocalDate .now();
        DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "/"+ nowDate.format(dayFormat);
    }
    //파일 이름 생성
    private String createVideoName(String id) {
        LocalDateTime  nowDate = LocalDateTime .now();
        DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("yyMMddHHmm");
        return "/"+ nowDate.format(dayFormat)+"_"+id;
    }
    
}
