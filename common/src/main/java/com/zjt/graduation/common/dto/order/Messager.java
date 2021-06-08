package com.zjt.graduation.common.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/13 10:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Messager {
    private Long userId;

    private Long orderId;
}
