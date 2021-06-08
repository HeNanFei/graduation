package com.zjt.graduation.common.entity;

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
 * @since 2020-10-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Source对象", description="")
public class Source implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private Long id;

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

    @ApiModelProperty(value = "年级id")
    private Long gid;

    @ApiModelProperty(value = "资源类型")
    private String type;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "审核状态")
    private String status;

    @ApiModelProperty(value = "资源名称")
    @TableField("sourceName")
    private String sourceName;

    @ApiModelProperty(value = "审核人")
    @TableField("auditName")
    private String auditName;

    @ApiModelProperty(value = "上传人姓名")
    @TableField("uploadUserName")
    private String uploadUserName;

    @ApiModelProperty(value = "上传人id")
    @TableField("userId")
    private Long userId;

    private String videoId;

    private String requestId;


}
