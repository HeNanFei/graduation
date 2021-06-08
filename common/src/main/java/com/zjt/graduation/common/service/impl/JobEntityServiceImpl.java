package com.zjt.graduation.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.graduation.common.entity.JobEntity;
import com.zjt.graduation.common.mapper.JobEntityMapper;
import com.zjt.graduation.common.service.JobEntityService;
import org.springframework.stereotype.Service;

@Service
public class JobEntityServiceImpl extends ServiceImpl<JobEntityMapper, JobEntity> implements JobEntityService {
}
