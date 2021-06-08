package com.zjt.graduation.common.validate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/12 14:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidResult {
    private String errorInfor;

    private String rowIndex;

    private String columnName;
}
