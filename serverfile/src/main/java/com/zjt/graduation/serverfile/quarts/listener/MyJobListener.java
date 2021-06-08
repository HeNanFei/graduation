package com.zjt.graduation.serverfile.quarts.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

/**
 * @Author hyh
 * @Date: 2020/10/26 8:50
 * @Version 1.0
 */

public class MyJobListener extends JobListenerSupport {


    @Override
    public String getName() {
        return "DateTimeJob";
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("jobExecutionVetoed");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("执行中");
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {

        System.out.println("上下文");
    }
}
