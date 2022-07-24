//package com.onlive.common.component;
//
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.onlive.common.service.LiveService;
//
//
//@Component
//public class EventScheduler implements Job {
//   private static Logger logger = LoggerFactory.getLogger(EventScheduler.class);
//   @Autowired
//   private LiveService liveService ;
//    @Override
//    public void execute(JobExecutionContext context) throws JobExecutionException {
//        logger.info("::::::aaaaaaaaaaaa:::::");
//        //현재 라이브 중인 라이브 상태 변경(대기->라이브중, 라이브중-> 종료)
//        //int a = liveService.updateLiveStatus();
//        //logger.info("처리 : "+a);
//    }
//}
// 