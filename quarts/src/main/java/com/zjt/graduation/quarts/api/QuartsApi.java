package com.zjt.graduation.quarts.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.istack.internal.NotNull;
import com.zjt.graduation.common.annota.AvoidSubmit;
import com.zjt.graduation.common.entity.JobEntity;
import com.zjt.graduation.common.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.SchedulerException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "定时任务相关APi")
@RequestMapping("/quarts/task")
public interface QuartsApi {
    @ApiOperation("创造任务")
    @PostMapping("createTaks")
    @AvoidSubmit
    public CommonResult createTask(@Validated JobEntity jobEntity);

    @ApiOperation("停止任务")
    @PostMapping("pasuseJob")
    public CommonResult pauseJob(@RequestParam @NotNull String jobKey) throws SchedulerException;

    @ApiOperation("查询任务")
    @PostMapping("listJob")
    public CommonResult listJob();

    @ApiOperation("执行一次任务")
    @PostMapping("executeJob")
    public CommonResult executeJob();

    @ApiOperation("开始任务")
    @PostMapping("startJob")
    public CommonResult createTask(@RequestParam @NotNull String jobKey);

    @ApiOperation("删除任务")
    @PostMapping("deleteJob")
    public CommonResult deleteJob(@RequestParam @NotNull String jobKey);

    @ApiOperation("任务分页")
    @PostMapping("jobPage")
    public CommonResult jobPage(@RequestBody Page page);

}
