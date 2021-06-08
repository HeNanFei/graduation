package com.zjt.graduation.quarts.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.istack.internal.NotNull;
import com.zjt.graduation.common.annota.AvoidSubmit;
import com.zjt.graduation.common.entity.JobEntity;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.common.service.JobEntityService;
import com.zjt.graduation.quarts.api.QuartsApi;
import com.zjt.graduation.quarts.service.ScheduleService;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller implements QuartsApi {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private JobEntityService jobEntityService;

    @Override
    public CommonResult createTask(@RequestBody @Validated JobEntity jobEntity){
        if(scheduleService.createTask(jobEntity)) {
            return CommonResult.success("create success");
        }
        return CommonResult.failed();
    }

    @Override
    public CommonResult pauseJob(@RequestParam @NotNull  String jobKey) throws SchedulerException {
        if(scheduleService.pauseJob(jobKey)){
            return CommonResult.success("the job is paused");
        }
        return CommonResult.failed();
    }
    @AvoidSubmit
    @Override
    public CommonResult listJob(){
        return CommonResult.success(jobEntityService.list());
    }

    @Override
    public CommonResult executeJob() {
        return null;
    }

    @Override
    public CommonResult createTask(@RequestParam @NotNull String jobKey) {
        return CommonResult.success(scheduleService.startJob(jobKey));
    }

    @Override
    public CommonResult deleteJob(String jobKey) {
        return CommonResult.success(scheduleService.deleteJob(jobKey));
    }

    @Override
    public CommonResult jobPage(Page page) {
        return CommonResult.success(scheduleService.getAllCronJob(page));
    }


}
