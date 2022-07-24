//package com.onlive.common.component;
//
//import javax.annotation.PostConstruct;
//
//import org.quartz.CronScheduleBuilder;
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.SchedulerFactory;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.quartz.impl.StdSchedulerFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ScheduleManager {
//    // quartz
//    private SchedulerFactory schedulerFactory;
//    private Scheduler scheduler;
//   
//    @PostConstruct
//    public void start() throws SchedulerException {
//        schedulerFactory = new StdSchedulerFactory();
//        scheduler = schedulerFactory.getScheduler();
//        scheduler.start();
//        
//        // job 지정
//        JobDetail job = JobBuilder.newJob(EventScheduler.class).withIdentity("testJob").build();
//        
//        // trigger 지정
//        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")).build();
//        
//        scheduler.scheduleJob(job, trigger);
//    }
//    
//    @Bean
//    public JobDetail updateLiveStatus() {
//        return JobBuilder.newJob().ofType(EventScheduler.class)
//                .storeDurably()
//                .withIdentity("updateLiveStatus")
//                .withDescription("check 1")
//                .build();
//    }
//    @Bean
//    public CronTriggerFactoryBean updateLiveStatusTrigger(JobDetail updateLiveStatus) {
//        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
//        trigger.setJobDetail(updateLiveStatus);
//        trigger.setCronExpression("0 */1 * * * *");
//        return trigger;
//    }
//}
