package com.zjt.graduation.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author hyh
 * @Date: 2020/12/2 18:00
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class Dto {
    private Integer[] aid;


    private String[] content;

    private String[] username;

    private String[] writed;

    private String[] title;

    private Integer[] uid;

    private String[] status;

    private String[] attribute;

    private Integer[] click;

    @TableField("imgUrl")
    private String[] imgUrl;

    private String[] content2;

}
