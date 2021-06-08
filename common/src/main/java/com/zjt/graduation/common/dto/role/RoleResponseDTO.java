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
@ApiModel(value="RoleResponseDTO", description="RoleResponseDTO")
public class RoleResponseDTO {
    @ApiModelProperty(value = "id")
    private String name;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "id")
    private boolean avaliable;
}
