package com.zjt.graduation.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/18 9:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("system_class")
@EqualsAndHashCode
public class SystemClass extends BaseEntity{
    private Long id;

    private String className;

    private Long teacherId;

    private String teacherName;

    private LocalDateTime createTime;

    private Integer number;

    private String subject;

    private String grade;
}
