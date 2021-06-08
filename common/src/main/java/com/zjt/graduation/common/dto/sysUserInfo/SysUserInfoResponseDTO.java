package com.zjt.graduation.common.dto.sysUserInfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author hyh
 * @Date: 2020/10/21 14:35
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserInfoResponseDTO {
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "所属地区")
    private String region;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "账号状态")
    @TableField("auditStateId")
    private Integer auditStateId;

    @ApiModelProperty(value = "注册时间")
    @TableField("regisTime")
    private String regisTime;

}
