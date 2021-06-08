package com.zjt.graduation.common.dto.role;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author hyh
 * @Date: 2020/10/1 15:57
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RoleRequestDTO", description="RoleRequestDTO")
public class RoleRequestDTO {

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private Long[] id;

    @ApiModelProperty(value = "角色名称")
    private String[] name;

    @ApiModelProperty(value = "是否可用")
    private Boolean[] avaliable;

}
