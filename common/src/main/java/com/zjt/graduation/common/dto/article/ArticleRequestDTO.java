package com.zjt.graduation.common.dto.article;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Article请求对象", description="")
public class ArticleRequestDTO {
    @ApiModelProperty(name = "aid")
    private Integer[] aid;

    @ApiModelProperty(name = "content")
    private String[] content;

    @ApiModelProperty(name = "username")
    private String[] username;

    @ApiModelProperty(name = "writed")
    private String[] writed;

    @ApiModelProperty(name = "title")
    private String[] title;

    @ApiModelProperty(name = "uid")
    private Integer[] uid;

    @ApiModelProperty(name = "status")
    private String[] status;

    @ApiModelProperty(name = "attribute")
    private String[] attribute;

    @ApiModelProperty(name = "click")
    private Integer[] click;

    @ApiModelProperty(name = "imgUrl")
    private String[] imgUrl;

    @ApiModelProperty(name = "content2")
    private String[] content2;
}
