package com.zjt.graduation.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.graduation.common.entity.SystemStudent;
import com.zjt.graduation.common.mapper.SystemStudentMapper;
import com.zjt.graduation.common.service.SystemStudentService;
import org.springframework.stereotype.Service;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/18 17:17
 */
@Service
public class SystemStudentServiceImpl extends ServiceImpl<SystemStudentMapper, SystemStudent>  implements SystemStudentService { }
