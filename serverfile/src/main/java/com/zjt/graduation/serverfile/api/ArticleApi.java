package com.zjt.graduation.serverfile.api;

import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.serverfile.clazz.ArticleSearchDao;
import com.zjt.graduation.serverfile.entity.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/16 10:51
 */
@Api(tags = "Article Api")
@RequestMapping("/article/")
public interface ArticleApi {

    @ApiOperation(value = "create Article")
    @PostMapping("/creatArticle")
    CommonResult creatArticle(HttpServletRequest request, @RequestBody Article article) throws IOException;

    @ApiOperation(value = "listArticle")
    @GetMapping("/listArticle")
    CommonResult listArticle(@RequestBody ArticleSearchDao articleSearchDao)  throws IOException;

    @ApiOperation(value = "deleteArticle")
    @GetMapping("/deleteArticle")
    CommonResult deleteArticle(@RequestParam Long id )  throws IOException;


    @ApiOperation(value = "deleteArticle")
    @GetMapping("/updateArticle")
    CommonResult updateArticle(@RequestBody Article article )  throws IOException;


    @ApiOperation(value = "listByAttribute")
    @GetMapping("/listByAttribute")
    CommonResult listByAttribute(Integer current,Integer size,String attribute )  throws IOException;



}
