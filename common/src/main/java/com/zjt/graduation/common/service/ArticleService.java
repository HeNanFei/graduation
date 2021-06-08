package com.zjt.graduation.common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.article.ArticleRequestDTO;
import com.zjt.graduation.common.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjt.graduation.common.query.ComplexQueryRequest;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @Author hyh
 * @since 2020-08-02
 */
public interface ArticleService extends IService<Article> {
    List<Article>  getAll();

    Page<Article> queryArticle(ComplexQueryRequest<ArticleRequestDTO> requestDTO);
}
