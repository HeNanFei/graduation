package com.zjt.graduation.common.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/22 10:08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDao {
    private Integer current;

    private Integer size;

    private String fieldName;

    private String fieldValue;
}
