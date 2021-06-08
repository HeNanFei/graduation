package com.zjt.graduation.es.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/16 10:57
 */
@Document(indexName = "article", type = "_doc",shards = 1,replicas = 0)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class Article implements Serializable {
    @Field(name = "aid",type = FieldType.Auto)
    private Long aid;
    @Field(name = "content",type = FieldType.Auto,analyzer = "ik_max_word")
    private String content;
    @Field(name = "userName",type = FieldType.Auto,analyzer = "ik_max_word")
    private String userName;
    @Field(name = "writed",type = FieldType.Auto)
    private String writed;
    @Field(name = "title",type = FieldType.Auto,analyzer = "ik_max_word")
    private String title;
    @Field(name = "uid",type = FieldType.Auto)
    private Long uid;
    @Field(name = "status",type = FieldType.Auto)
    private Integer status;
    @Field(name = "attribute",type = FieldType.Auto)
    private String attribute;
    @Field(name = "click",type = FieldType.Auto)
    private Integer click;
    @Field(name = "imgUrl",type = FieldType.Auto)
    private String imgUrl;
    @Field(name = "contentBlank",type = FieldType.Auto)
    private String contentBlank;
    @Field(name = "id",type = FieldType.Long)
    private Long id;

    @Field(name = "dataCreateTime",type = FieldType.Text)
    private LocalDateTime dataCreateTime;

    @Field(name = "dataChangeTime",type = FieldType.Text)
    private LocalDateTime dataChangeTime;

    @Field(name = "dataCreateBy",type = FieldType.Auto)
    private Long dataCreateBy;

    @Field(name = "dataChangeBy",type = FieldType.Auto)
    private Long dataChangeBy;

    @Field(name = "deleted",type = FieldType.Auto)
    private Long deleted;
}
