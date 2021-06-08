/*
package com.zjt.graduation.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.graduation.common.dto.article.ArticleRequestDTO;
import com.zjt.graduation.common.entity.Article;
import com.zjt.graduation.common.mapper.ArticleMapper;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

*/
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @Author hyh
 * @since 2020-08-02
 *//*

@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public List<Article> getAll() {
        return getBaseMapper().selectList(new QueryWrapper<Article>().isNotNull("aid"));
    }

    @Override
    public Page<Article> queryArticle(ComplexQueryRequest<ArticleRequestDTO> requestDTO) {
        Page page = requestDTO.getPage();
        QueryWrapper queryWrapper = requestDTO.queryWrapper("t");

        return baseMapper.getAll(page,queryWrapper);
    }
}
*/
