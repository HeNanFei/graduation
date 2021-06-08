package com.zjt.graduation.common.excelentity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysUserInfoExcel {
    @ExcelProperty(value = "用户名")
    @ApiModelProperty(value = "用户名")
    private String username;

    @ExcelProperty(value = "密码")
    @ApiModelProperty(value = "密码")
    private String password;

    @ExcelProperty(value = "邮箱")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ExcelProperty(value = "手机号")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @ExcelProperty(value = "所属地区")
    @ApiModelProperty(value = "所属地区")
    private String region;

    @ExcelProperty(value = "性别")
    @ApiModelProperty(value = "性别")
    private String sex;

    @ExcelProperty(value = "账号状态")
    @ApiModelProperty(value = "账号状态")
    @TableField("auditStateId")
    private Integer auditStateId;

    @ExcelProperty(value = "注册时间")
    @ApiModelProperty(value = "注册时间")
    @TableField("regisTime")
    private String regisTime;
}
