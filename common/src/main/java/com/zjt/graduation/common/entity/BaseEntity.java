package com.zjt.graduation.common.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public abstract class BaseEntity {
    @ExcelIgnore
    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime dataCreateTime;

    @ExcelIgnore
    @TableField(fill = FieldFill.UPDATE)
    protected LocalDateTime dataChangeTime;

    @ExcelIgnore
    @TableField(fill = FieldFill.INSERT)
    protected Long dataCreateBy;

    @ExcelIgnore
    @TableField(fill = FieldFill.UPDATE)
    protected Long dataChangeBy;

    @ExcelIgnore
    @TableField(fill = FieldFill.INSERT)
    protected Long deleted;

}
