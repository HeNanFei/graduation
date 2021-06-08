package com.zjt.graduation.quarts.job;

import com.zjt.graduation.common.entity.BaseEntity;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractJob extends BaseEntity implements Job {
    ThreadLocal<Map<String,Object>> localDateTimeThreadLocal = new ThreadLocal<>();

    private String jobKey;

    private String cronExpression;

    private String para;

    private String methodName;

    private String className;

    protected void beforeDoexecute(){
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("startTime",LocalDateTime.now());
        localDateTimeThreadLocal.set(dataMap);
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        beforeDoexecute();

        doTask(context,context.getJobDetail());

        afterDoExecute();
    }

    protected void afterDoExecute(){
        Map<String, Object> dataMap = localDateTimeThreadLocal.get();
        LocalDateTime startTime =(LocalDateTime) dataMap.get("startTime");
        LocalDateTime endTime = LocalDateTime.now();
        Duration between = Duration.between(startTime, endTime);
       // CronLogService cronLogService = ApplicationContextUtils.getBean(CronLogService.class);

    }

    public abstract void doTask(JobExecutionContext context,JobDetail jobDetail);

    public String getJobKey() {
        return jobKey;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }


    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "AbstractJob{" +
                "localDateTimeThreadLocal=" + localDateTimeThreadLocal +
                ", jobKey='" + jobKey + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", para='" + para + '\'' +
                ", methodName='" + methodName + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
