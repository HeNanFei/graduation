package com.zjt.graduation.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/22 13:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
@TableName("notice_message")
public class NoticeMessage {
    private Long id;
    private String title;
    private String content;
    private Integer msgTypeId;
    private LocalDate createDate;
    private String creator;
    private Long creatorOid;
    private String attachmentPath;
    private String personOrClass;
    private Long personId;
    private Long classId;
    private String publishDate;
    private String fileReId;
    private String isPublic;
    private String privateMsg;
    private String attachFileName;

}
