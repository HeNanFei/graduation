package com.zjt.graduation.es.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/17 16:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsArticleSearchDao {
    private String fieldName;

    private String fieldValue;

    private Integer current;

    private Integer size;
}
