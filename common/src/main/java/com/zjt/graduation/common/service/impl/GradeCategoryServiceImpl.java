package com.zjt.graduation.common.service.impl;

import com.zjt.graduation.common.entity.GradeCategory;
import com.zjt.graduation.common.mapper.GradeCategoryMapper;
import com.zjt.graduation.common.service.GradeCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.util.FieldUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @Author hyh
 * @since 2020-09-07
 */
@Service
public class GradeCategoryServiceImpl extends ServiceImpl<GradeCategoryMapper, GradeCategory> implements GradeCategoryService {
}
