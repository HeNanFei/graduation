package com.zjt.graduation.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/20 8:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("file_record")
public class FileRecord extends BaseEntity implements Serializable {
    private Integer id;
    private String name;
    private Integer isImg;
    private String contentType;
    private Long size;
    private String path;
    private String url;
    private String source;
    private String description;
    private String grade;
}
