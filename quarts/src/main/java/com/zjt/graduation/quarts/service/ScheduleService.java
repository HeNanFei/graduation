package com.zjt.graduation.quarts.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.entity.JobEntity;
import com.zjt.graduation.quarts.job.SelfJob;
import org.quartz.SchedulerException;

import java.text.ParseException;

public interface ScheduleService {
    public void executeTask(SelfJob selfJob) throws ParseException, SchedulerException;

    public Boolean pauseJob(String jobKey) throws SchedulerException;

    public Boolean createTask(JobEntity jobEntity);

    public Boolean startJob(String jobKey);

    public Boolean deleteJob(String jobkey);

    public Page<JobEntity> getAllCronJob(Page page);

    public void doJobWithoutParam();

    public void doJobWithParam(String param);


}
