package com.zjt.graduation.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @Author hyh
 * @since 2020-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gradeCategory")
@ApiModel(value="GradeCategory对象", description="")
public class GradeCategory implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private Long id;

    @ApiModelProperty(value = "年级分类")
    @TableField("gradeName")
    private String gradeName;

    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;

    @ApiModelProperty(value = "修改人")
    @TableField("dataChangeBy")
    private String dataChangeBy;

    @ApiModelProperty(value = "修改时间")
    @TableField("dataChangeTime")
    private Date dataChangeTime;

    @ApiModelProperty(value = "创建人")
    @TableField("dataCreateBy")
    private String dataCreateBy;

    @ApiModelProperty(value = "创建时间")
    @TableField("dataCreateTime")
    private Date dataCreateTime;


}
