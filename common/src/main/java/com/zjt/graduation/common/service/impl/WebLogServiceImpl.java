package com.zjt.graduation.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.graduation.common.config.aspect.WebLog;
import com.zjt.graduation.common.mapper.WebLogMapper;
import com.zjt.graduation.common.service.WebLogService;
import org.springframework.stereotype.Service;

@Service
public class WebLogServiceImpl extends ServiceImpl<WebLogMapper, WebLog> implements WebLogService {
}
