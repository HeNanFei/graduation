package com.zjt.graduation.mservice2.api;

import com.zjt.graduation.common.dto.article.ArticleRequestDTO;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "this is api test")
@RequestMapping("/mservice/article")
public interface ArticleApi {
    @ApiOperation(value = "获取所有文章")
    @RequestMapping("/getAll")
    CommonResult getAll();

    @ApiOperation(value = "条件获取文章")
    @RequestMapping("/queryArticle")
    CommonResult queryArticle(@RequestBody(required = false) ComplexQueryRequest<ArticleRequestDTO> requestDTO);


}
