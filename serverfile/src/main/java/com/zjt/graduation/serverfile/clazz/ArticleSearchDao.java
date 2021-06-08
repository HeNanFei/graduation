package com.zjt.graduation.serverfile.clazz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/16 16:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSearchDao {

    private Integer current;

    private Integer size;

    private String attribute;
}
