package com.zjt.graduation.common.dto.sysUserInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysUserLoginResponseDTO", description="SysUserLoginResponseDTO")
public class SysUserLoginResponseDTO {
    @ApiModelProperty(value = "返回信息")
    private List<String> errorMsg;

    @ApiModelProperty(value = "token")
    private String token;

}
