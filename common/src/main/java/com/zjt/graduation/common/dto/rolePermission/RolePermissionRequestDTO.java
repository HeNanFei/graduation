package com.zjt.graduation.common.dto.rolePermission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RolePermissionRequestDTO", description="RolePermissionRequestDTO请求参数")
public class RolePermissionRequestDTO {
    @ApiModelProperty(value = "id")
    private Long[] id;

    @ApiModelProperty(value = "角色id")
    private Long[] roleId;

    @ApiModelProperty(value = "权限Id")
    private Long[] permissionId;

    @ApiModelProperty(value = "角色名称")
    private String[] roleName;

    @ApiModelProperty(value = "权限名称")
    private String[] permissionName;
}
