package com.zjt.graduation.common.excelentity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/18 11:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemStudentExcel {
    @ExcelProperty(value = "学生姓名")
    private String  studentName;

    @ExcelProperty(value = "籍贯")
    private String birthPlace;

    @ExcelProperty(value = "所在学校")
    private String graduateSchool;

    @ExcelProperty(value = "性别")
    private String gender;

    @ExcelProperty(value = "电话")
    private String phone;

    @ExcelProperty(value = "年级")
    private String degree;

    @ExcelProperty(value = "课程")
    private String subject;

    @ExcelProperty(value = "证书编号")
    private String certificationNumber;

    @ExcelProperty(value = "所属班级")
    private String className;

    @ExcelProperty(value = "邮箱")
    private String email;




}
