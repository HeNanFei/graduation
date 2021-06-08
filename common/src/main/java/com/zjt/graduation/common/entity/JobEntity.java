package com.zjt.graduation.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("job_entity")
public class JobEntity extends BaseEntity implements Serializable {
    private Long id;

    @NotNull(message = "任务名称不能为空")
    private String jobName;

    @NotNull(message = "cron表达式不能为空")
    private String cronExpression;

    private Integer status;

    private String para;

    private String methodName;

    @NotNull(message = "任务Key不能为空")
    private String jobKey;

    private String className;
}
