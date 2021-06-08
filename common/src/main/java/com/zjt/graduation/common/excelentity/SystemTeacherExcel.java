package com.zjt.graduation.common.excelentity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/18 11:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemTeacherExcel {
    @ExcelProperty(value = "教师姓名")
    private String  teacherName;

    @ExcelProperty(value = "籍贯")
    private String birthPlace;

    @ExcelProperty(value = "毕业院校")
    private String graduateSchool;

    @ExcelProperty(value = "性别")
    private String gender;

    @ExcelProperty(value = "手机号")
    private String phone;

    @ExcelProperty(value = "学历")
    private String degree;

    @ExcelProperty(value = "课程")
    private String subject;

    @ExcelProperty(value = "证书编号")
    private String certificationNumber;

    @ExcelProperty(value = "邮箱")
    private String email;


}
