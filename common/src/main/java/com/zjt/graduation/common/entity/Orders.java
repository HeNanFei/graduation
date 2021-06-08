package com.zjt.graduation.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/13 8:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders")
public class Orders extends BaseEntity implements Serializable {
    private Long id;
    private Long sellGoodId;
    private String goodName;
    private Integer status;
    private String  statusText;
    private LocalDateTime purchaseTime;
    private String  userName;
    private BigDecimal price;
    private Long payRecordId;
    private String gender;
    private Integer number;
}
