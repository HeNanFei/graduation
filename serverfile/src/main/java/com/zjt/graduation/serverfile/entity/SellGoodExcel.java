package com.zjt.graduation.serverfile.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.zjt.graduation.common.annota.AutoValidate;
import com.zjt.graduation.common.validate.Last;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellGoodExcel {

    @ExcelProperty(value = "商品名称")
    @AutoValidate(message = "wrong",groups = Last.class)
    private String goodName;

    @ExcelProperty(value = "商品数量")
    @Min(value = 1,message = "数量不得小于0")
    private Integer  number;

    @ExcelProperty(value = "开始时间")
    private String startTime;

    @ExcelProperty(value = "结束时间")
    private String   endTime;

    @ExcelProperty(value = "价格")
    @Min(value = 1,message = "价格不得小于0")
    private Integer  price;

    @ExcelProperty(value = "商品分类")
    @NotNull(message = "分类不得为空")
    private String  category;

    @ExcelProperty("商品简介")
    private String  introduction;

    @ExcelIgnore
    private Integer currentRowIndex;
}
