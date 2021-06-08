package com.zjt.graduation.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/18 11:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("system_student")
@EqualsAndHashCode

public class SystemStudent extends BaseEntity{
    private Long id;

    private String  studentName;

    private String birthPlace;

    private String graduateSchool;

    private String gender;

    private String phone;

    private String degree;

    private String subject;

    private String certificationNumber;

    private String className;

    private Long classId;

    private String email;
}
