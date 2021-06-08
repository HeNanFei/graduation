package com.zjt.graduation.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/18 10:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("system_teacher")
@EqualsAndHashCode
@ApiModel
public class SystemTeacher extends BaseEntity{
    private Long id;

    private String  teacherName;

    private String birthPlace;

    private String graduateSchool;

    private String gender;

    private String phone;

    private String degree;

    private String subject;

    private String certificationNumber;

    private String email;
}
