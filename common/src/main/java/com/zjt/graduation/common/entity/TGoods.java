package com.zjt.graduation.common.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tgoods")
public class TGoods extends BaseEntity{
    @ExcelProperty("选项1")
    private Integer id;
    @ExcelProperty("选项2")
    private String platform_goods_code;
    @ExcelProperty("选项3")
    private String goods_code;
    @ExcelProperty("选项4")
    private String name;
    @ExcelProperty("选项5")
    private String title;
    @ExcelProperty("选项6")
    private String brand_code;
    @ExcelProperty("选项7")
    private String brand_name;
    @ExcelProperty("选项8")
    private String category_code;
    @ExcelProperty("选项9")
    private String category_name;
    @ExcelProperty("选项10")
    private String second_category_code;
    @ExcelProperty("选项11")
    private String second_category_name;
    @ExcelProperty("选项12")
    private String three_category_code;
    @ExcelProperty("选项13")
    private String three_category_name;
    @ExcelProperty("选项14")
    private String lables;
    @ExcelProperty("选项15")
    private String labelName;
    @ExcelProperty("选项16")
    private String weight;
    @ExcelProperty("选项17")
    private Integer is_recipe;
    @ExcelProperty("选项18")
    private Integer status;
    @ExcelProperty("选项19")
    private String thumbnail;
    @ExcelProperty("选项20")
    private String video_path;
    @ExcelProperty("选项21")
    private String lib_id;
    @ExcelProperty("选项22")
    private Integer goods_type;
    @ExcelProperty("选项23")
    private String batch_no;
    @ExcelProperty("选项24")
    private String key_word;
    @ExcelProperty("选项25")
    private String city;
    @ExcelProperty("选项26")
    private Integer type;
    @ExcelProperty("选项27")
    private String create_time;
    @ExcelProperty("选项28")
    private Integer company_id;
    @ExcelProperty("选项29")
    private String company_name;
    @ExcelProperty("选项30")
    private String platformCategoryCode;
    @ExcelProperty("选项31")
    private String platformSecondCategoryCode;
    @ExcelProperty("选项32")
    private String platformThreeCategoryCode;
    @ExcelProperty("选项33")
    private String platformCategoryName;
    @ExcelProperty("选项34")
    private String platformSecondCategoryName;
    @ExcelProperty("选项35")
    private String platformThreeCategoryName;
    @ExcelProperty("选项36")
    private String platformContentsCode;
    @ExcelProperty("选项37")
    private String platformSecondContentsCode;
    @ExcelProperty("选项38")
    private String platformContentsName;
    @ExcelProperty("选项39")
    private String platformSecondContentsName;
    @ExcelProperty("选项40")
    private String minPrice;
    @ExcelProperty("选项41")
    private String maxPrice;
    @ExcelProperty("选项42")
    private Integer repertorySum;
    @ExcelProperty("选项43")
    private Integer isRecycle;
    @ExcelProperty("选项44")
    private Integer salesNum;
    @ExcelProperty("选项45")
    private String medicineType;
    @ExcelProperty("选项46")
    private String pro_enterprises;
    @ExcelProperty("选项47")
    private String goods_codes;
    @ExcelProperty("选项48")
    private Integer presell_type;
    @ExcelProperty("选项49")
    private String earnest;
    @ExcelProperty("选项50")
    private String low_price;
    @ExcelProperty("选项51")
    private String start_av_time;
    @ExcelProperty("选项52")
    private String end_av_time;
    @ExcelProperty("选项53")
    private String presell_starttime;
    @ExcelProperty("选项54")
    private String presell_endtime;
    @ExcelProperty("选项55")
    private String recycle_time;
    @ExcelProperty("选项56")
    private Integer isOpenLabel;
    @ExcelProperty("选项57")
    private String remark;
}
