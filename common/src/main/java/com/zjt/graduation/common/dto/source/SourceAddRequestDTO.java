package com.zjt.graduation.common.dto.source;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author hyh
 * @LocalDateTime: 2020/10/4 15:18
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SourceAddRequestDTO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "年级id")
    private Long gid;

    @ApiModelProperty(value = "资源类型")
    private String type;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "审核状态")
    private String status;

    @ApiModelProperty(value = "资源名称")
    private String sourceName;

    @ApiModelProperty(value = "审核人")
    private String auditName;

    @ApiModelProperty(value = "上传人姓名")
    private String uploadUserName;

    @ApiModelProperty(value = "上传人id")
    private Long userId;

    @ApiModelProperty(value = "video")
    private MultipartFile videos;

    @ApiModelProperty(value = "images")
    private MultipartFile images;

    private String videoId;

}
