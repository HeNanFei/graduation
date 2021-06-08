package com.zjt.graduation.quarts.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.entity.JobEntity;
import com.zjt.graduation.common.service.JobEntityService;
import com.zjt.graduation.quarts.job.SelfJob;
import com.zjt.graduation.quarts.service.ScheduleService;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.List;


/**
 * Demo class
 *
 * @Author hyh
 * @date 2020/12/21
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @PostConstruct
    public void doAllTaks(){
        List<JobEntity> taskList = jobEntityService.list();
        if(!CollectionUtils.isEmpty(taskList)){
            taskList.stream().filter(n -> n.getStatus() == 0 && !StringUtils.isEmpty(n.getCronExpression()))
            .forEach(n ->{
                SelfJob selfJob = new SelfJob();
                selfJob.setCronExpression(n.getCronExpression());
                selfJob.setJobKey(n.getJobKey());
                selfJob.setMethodName(n.getMethodName());
                selfJob.setPara(n.getPara());
                selfJob.setClassName(n.getClassName());
                try{
                    executeTaks(scheduler, selfJob);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });

        }
    }

    @Autowired
    private JobEntityService jobEntityService;

    @Autowired
    private Scheduler scheduleService(Scheduler scheduler) {
        return scheduler;
    }


    @Autowired
    private Scheduler scheduler;



    @Override
    public void executeTask(SelfJob selfJob) throws ParseException, SchedulerException {
        //easyDoWithoutParams(scheduler,jobClass,jobKey);
    }

    @Override
    public Boolean pauseJob(String jobKey) throws SchedulerException {
        JobKey jobKey1 = JobKey.jobKey(jobKey);
        boolean existJobkey = scheduler.checkExists(jobKey1);
        if(existJobkey){
            scheduler.pauseJob(jobKey1);
            scheduler.deleteJob(jobKey1);
            JobEntity jobEntity = jobEntityService.getOne(new QueryWrapper<JobEntity>().eq("jobKey", jobKey));
            jobEntity.setStatus(1);
            jobEntityService.updateById(jobEntity);
            return true;
        }
        return true;
    }

    @Override
    public Boolean createTask(JobEntity jobEntity) {
        if(jobEntityService.count(new QueryWrapper<JobEntity>().eq("jobKey",jobEntity.getJobKey())) > 0){
            return false;
        }
        jobEntity.setStatus(0);
        boolean save = jobEntityService.save(jobEntity);
        try{
            if(save) {
                SelfJob selfJob = new SelfJob();
                selfJob.setCronExpression(jobEntity.getCronExpression());
                selfJob.setJobKey(jobEntity.getJobKey());
                selfJob.setClassName(jobEntity.getClassName());
                selfJob.setMethodName(jobEntity.getMethodName());
                selfJob.setPara(jobEntity.getPara());
                executeTaks(scheduler, selfJob);
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean startJob(String jobKey) {
        try{
            JobEntity jobEntity = jobEntityService.getOne(new QueryWrapper<JobEntity>().eq("jobKey", jobKey));
            jobEntity.setStatus(0);
            SelfJob selfJob = new SelfJob();
            selfJob.setJobKey(jobEntity.getJobKey());
            selfJob.setClassName(jobEntity.getClassName());
            selfJob.setMethodName(jobEntity.getMethodName());
            selfJob.setPara(jobEntity.getPara());
            selfJob.setCronExpression(jobEntity.getCronExpression());
            executeTaks(scheduler,selfJob);
            jobEntityService.updateById(jobEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteJob(String jobkey) {
        JobEntity job = jobEntityService.getOne(new QueryWrapper<JobEntity>().eq("jobKey", jobkey));
        job.setStatus(1);
        try{
            if(scheduler.deleteJob(JobKey.jobKey(jobkey)) && jobEntityService.removeById(job.getId())){ return true; }
            }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Page<JobEntity> getAllCronJob(Page page) {
        return jobEntityService.page(page, new QueryWrapper<>());
    }

    public static void excuteJobOneTime(Scheduler scheduler, SelfJob selfJob) throws ParseException, SchedulerException {
        if(!StringUtils.isEmpty(selfJob.getCronExpression()) && CronExpression.isValidExpression(selfJob.getCronExpression())) {
            JobDetail jobDetail = JobBuilder.newJob(selfJob.getClass()).withIdentity(JobKey.jobKey(selfJob.getJobKey())).build();
            CronExpression cronExpression = new CronExpression(selfJob.getCronExpression());
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            Trigger tr = TriggerBuilder.newTrigger().withIdentity(selfJob.getJobKey()).forJob(jobDetail).build();  //只能触发一次

            scheduler.scheduleJob(jobDetail, tr);
        }else {
            throw new RuntimeException("please check the cron expression");
        }
    }

    public static void executeTaks(Scheduler scheduler, SelfJob selfJob) throws ParseException, SchedulerException {
        if(!StringUtils.isEmpty(selfJob.getCronExpression()) && CronExpression.isValidExpression(selfJob.getCronExpression())) {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.putAll(BeanMap.create(selfJob));
            JobDetail jobDetail = JobBuilder.newJob(selfJob.getClass()).setJobData(jobDataMap).withIdentity(JobKey.jobKey(selfJob.getJobKey())).build();
            CronExpression cronExpression = new CronExpression(selfJob.getCronExpression());
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            //Trigger tr = TriggerBuilder.newTrigger().withIdentity(selfJob.getJobKey()).forJob(jobDetail).build();  //只能触发一次

            CronTriggerImpl cronTrigger = new CronTriggerImpl();
            cronTrigger.setName((String) jobDataMap.get("jobKey"));
            cronTrigger.setCronExpression(selfJob.getCronExpression());
            if(!scheduler.checkExists(JobKey.jobKey((String) jobDataMap.get("jobKey")))){
                scheduler.scheduleJob(jobDetail, cronTrigger);
            }

        }else {
            throw new RuntimeException("please check the cron expression");
        }
    }




    @Override
    public void doJobWithoutParam() {
        List<JobEntity> list = jobEntityService.list();
        System.out.println(list);
    }

    @Override
    public void doJobWithParam(String param) {
        System.out.println(param);
    }

    public void doJobWithParams(String param) {
        System.out.println(param);
    }

}