/*
package com.zjt.graduation.serverfile.quarts.job;

import com.zjt.graduation.serverfile.quarts.listener.MyJobListener;
import com.zjt.graduation.serverfile.quarts.listener.SecondJobListener;
import org.quartz.*;
import org.quartz.impl.matchers.KeyMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;

*/
/**
 * @Author hyh
 * @Date: 2020/10/25 19:01
 * @Version 1.0
 *//*

@Configuration
public class QuartzConfig {
    @Bean
     public JobDetail getJobDetail() {

         return JobBuilder.newJob(MyJob.class)//myjob我们的业务类
                 .withIdentity("DateTimeJob")//可以给该JobDetail起一个id
                 //每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
                 .usingJobData("msg", "Hello Quartz")//关联键值对
                 .storeDurably()//即使没有Trigger关联时，也不需要删除该JobDetail
                 .build();
     }
    @Bean
    public Trigger myTrigger() throws ParseException {
        //定时触发器
        CronTriggerImpl cronTrigger = new CronTriggerImpl();
        cronTrigger.setCronExpression("0/6 * * * * ?");
        cronTrigger.setName("cronTast");
        cronTrigger.setJobName("DateTimeJob");
        return cronTrigger;
    }

    @Bean
    public Scheduler myScheduler(Scheduler scheduler) throws SchedulerException, ParseException {
        JobDetail jobDetail = getJobDetail();
        //任务监听器
        ListenerManager listenerManager = scheduler.getListenerManager();
                listenerManager.addJobListener(new MyJobListener(), KeyMatcher.keyEquals(jobDetail.getKey()));
                listenerManager.addJobListener(new SecondJobListener(),KeyMatcher.keyEquals(new JobKey("jobTwoKey")));
                //schedule 调度器
        scheduler.scheduleJob(SecondJob.getJobDetail2Back(),SecondJob.job2Trigger());

        scheduler.start();
        return scheduler;
    }

}
*/
