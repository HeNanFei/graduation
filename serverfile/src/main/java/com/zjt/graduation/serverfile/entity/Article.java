package com.zjt.graduation.serverfile.entity;

import com.zjt.graduation.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/16 10:57
 */
@Document(indexName = "article")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="Article", description="ArticleDetail")
public class Article extends BaseEntity {

    private Long aid;
    private String content;
    private String userName;
    private String writed;
    private String title;
    private Long uid;
    private Integer status;
    private String attribute;
    private Integer click;
    private String imgUrl;
    private String content2;
    private Long id;
}
