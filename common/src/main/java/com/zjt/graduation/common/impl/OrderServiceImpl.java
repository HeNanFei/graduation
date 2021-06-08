package com.zjt.graduation.common.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.graduation.common.entity.Orders;
import com.zjt.graduation.common.mapper.OrderMapper;
import com.zjt.graduation.common.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/13 8:41
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {

}
