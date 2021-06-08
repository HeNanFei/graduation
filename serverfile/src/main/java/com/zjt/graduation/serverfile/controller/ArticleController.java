package com.zjt.graduation.serverfile.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.controller.AbstractController;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.serverfile.api.ArticleApi;
import com.zjt.graduation.serverfile.clazz.ArticleSearchDao;
import com.zjt.graduation.serverfile.entity.Article;
import com.zjt.graduation.serverfile.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/16 11:02
 */
@RestController
public class ArticleController extends AbstractController implements ArticleApi {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public CommonResult creatArticle(HttpServletRequest request,Article article) throws IOException {
        if(!StringUtils.isEmpty(article.getContent())){
            String content = getContent(article.getContent());
            if(content.length()>180){
                article.setContent2(content.substring(0,180));
            }else{
                article.setContent2(content);
            }
        }
        List<Article> existArticle = articleMapper.selectList(new QueryWrapper<Article>().eq("title", article.getTitle()));
        if(CollectionUtils.isEmpty(existArticle)) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String userName = getUserName() == null ? "Nobody" : getUserName();
            article.setUserName(userName);
            article.setClick(0);
            article.setImgUrl(article.getImgUrl());
            article.setWrited(LocalDateTime.now().format(dateTimeFormatter));
            articleMapper.insert(article);
            return CommonResult.success("create success");
        }
        return CommonResult.success("article exist");
    }

    @Override
    public CommonResult listArticle(ArticleSearchDao articleSearchDao) throws IOException {
        Page page = new Page();
        page.setCurrent(Optional.ofNullable(articleSearchDao.getCurrent()).orElse(0));
        page.setSize(Optional.ofNullable(articleSearchDao.getSize()).orElse(10));
        String attribute = articleSearchDao.getAttribute();

        if(StringUtils.isEmpty(attribute)){
            return CommonResult.success(articleMapper.selectPage(page,new QueryWrapper<>()));
        }
        return CommonResult.success(articleMapper.selectPage(page,new QueryWrapper<Article>().eq("attribute",attribute)));
    }

    @Override
    public CommonResult deleteArticle(Long id) throws IOException {
        return CommonResult.success(articleMapper.deleteById(id));
    }

    @Override
    public CommonResult updateArticle(Article article) throws IOException {
        if(article.getId() != null){
            return  CommonResult.success(articleMapper.updateById(article));
        }
        return CommonResult.success(articleMapper.update(article,new QueryWrapper<Article>().eq("title",article.getTitle())));
    }

    @Override
    public CommonResult listByAttribute(Integer current, Integer size, String attribute) throws IOException {
        Page page = new Page();
        page.setCurrent(current);
        page.setSize(size);

        Page attribute1 = articleMapper.selectPage(page, new QueryWrapper<Article>().eq("attribute", attribute));
        return CommonResult.success(attribute1);
    }

    public static String getContent(String content){
        content = content.replaceAll( ".*?<body.*?>(.*?)<\\/body>", "$1");
        content=content.replaceAll("</?[a-zA-Z]+[^><]*>","");
        content.replaceAll("\n","");
        return content;
    }


}
