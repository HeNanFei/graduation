package com.zjt.graduation.serverfile.quarts.job;

import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * @Author hyh
 * @Date: 2020/10/26 15:30
 * @Version 1.0
 */
@Component
public class SecondJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(1/0);

        System.out.println("job Two is running");
    }
    public static JobDetail getJobDetail2Back(){
        JobBuilder jobTwoKey = JobBuilder.newJob(SecondJob.class).storeDurably().withIdentity(new JobKey("jobTwoKey"));
        return jobTwoKey.build();
    }
    public static Trigger job2Trigger() throws ParseException {
        CronTriggerImpl cronTrigger = new CronTriggerImpl();
        cronTrigger.setCronExpression("0/8 * * * * ?");
        cronTrigger.setJobKey(new JobKey("jobTwoKey"));
        cronTrigger.setName("secondTrigger");
        return cronTrigger;
    }
}
