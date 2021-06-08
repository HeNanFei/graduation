package com.zjt.graduation.common.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SysUserInfo")
@ApiModel(value="SysUserInfo对象", description="用户信息表")
public class SysUserInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private Long id;

    @ExcelProperty("用户名")
    @ApiModelProperty(value = "用户名")
    private String username;

    @ExcelProperty("密码")
    @ApiModelProperty(value = "密码")
    private String password;

    @ExcelProperty("邮箱")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ExcelProperty("手机号")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @ExcelProperty("所属地区")
    @ApiModelProperty(value = "所属地区")
    private String region;

    @ExcelProperty("性别")
    @ApiModelProperty(value = "性别")
    private String sex;

    @ExcelProperty("账号状态")
    @ApiModelProperty(value = "账号状态")
    @TableField("auditStateId")
    private Integer auditStateId;

    @ExcelProperty("注册时间")
    @ApiModelProperty(value = "注册时间")
    @TableField("regisTime")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date regisTime;


    @ExcelProperty("身份类别")
    @ApiModelProperty(value = "身份类别")
    @TableField("type")
    private String type;




}
