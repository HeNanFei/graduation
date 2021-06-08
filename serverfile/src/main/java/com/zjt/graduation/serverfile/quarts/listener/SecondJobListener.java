/*
package com.zjt.graduation.serverfile.quarts.listener;

import com.zjt.graduation.serverfile.eventabout.MyEvent;
import com.zjt.graduation.serverfile.utils.ApplicationContextUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

*/
/**
 * @Author hyh
 * @Date: 2020/10/26 16:18
 * @Version 1.0
 *//*

@Component
public class SecondJobListener extends JobListenerSupport{

    

    @Override
    public String getName() {
        return "secondJoblistner";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        super.jobToBeExecuted(context);
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        ApplicationContext applicationContext = ApplicationContextUtils.getContext();
        if(jobException != null){
            applicationContext.publishEvent(new MyEvent(this));
            //jobException.setUnscheduleFiringTrigger(true);
            //jobException.refireImmediately();
            //jobException.setRefireImmediately(false);
            //jobException.setUnscheduleAllTriggers(true);
            jobException.setUnscheduleFiringTrigger(true);
            //jobException.unscheduleAllTriggers();
        }
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        super.jobExecutionVetoed(context);
    }
}
*/
