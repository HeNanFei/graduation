package com.zjt.graduation.es.api;

import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.es.dao.EsArticleSearchDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/17 16:42
 */
@Api(tags = "Article about")
@RequestMapping("/article/search")
public interface ArticleSearchApi {

    @ApiOperation(value = "getAllArticle")
    @PostMapping("/getAllArticle")
    public CommonResult listArticleByContent(@RequestBody  EsArticleSearchDao esArticleSearchDao);


    @ApiOperation(value = "deleteArticleById")
    @PostMapping("/deleteArticleById")
    public CommonResult deleteArticleById(String id);




}
