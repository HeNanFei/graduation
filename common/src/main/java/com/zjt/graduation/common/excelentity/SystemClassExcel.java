package com.zjt.graduation.common.excelentity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/18 10:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemClassExcel {

    @ExcelProperty(value = "班级名称")
    private String className;

    @ExcelProperty(value = "教师名称")
    private String teacherName;

    @ExcelProperty(value = "课程")
    private String subject;

    @ExcelProperty(value = "年级")
    private String grade;
}
