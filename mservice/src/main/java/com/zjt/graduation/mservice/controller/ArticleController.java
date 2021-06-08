/*
package com.zjt.graduation.mservice.controller;


import com.zjt.graduation.common.controller.BaseController;
import com.zjt.graduation.common.dto.article.ArticleRequestDTO;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.common.service.ArticleService;
import com.zjt.graduation.mservice.api.ArticleApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

*/
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @Author hyh
 * @since 2020-08-02
 *//*

@RestController
public class ArticleController extends BaseController implements ArticleApi {
    private static Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    private ArticleService articleService;
    @Override
    public CommonResult getAll() {
        int i = 0/1;
        int i2 =1 /0;
        logger.info("info");
        logger.error("error");
        logger.debug("debug");
        return CommonResult.success(articleService.getAll());
    }

    @Override
    public CommonResult queryArticle(ComplexQueryRequest<ArticleRequestDTO> requestDTO) {
        return CommonResult.success(articleService.queryArticle(requestDTO));
    }
}
*/
