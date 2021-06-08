package com.zjt.graduation.es.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.es.api.ArticleSearchApi;
import com.zjt.graduation.es.dao.EsArticleSearchDao;
import com.zjt.graduation.es.entity.Article;
import com.zjt.graduation.es.utils.ElasticUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/17 16:53
 */
@RestController
public class ArticleSearchController implements ArticleSearchApi {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    @Override
    public CommonResult listArticleByContent(EsArticleSearchDao esArticleSearchDao) {
        if(esArticleSearchDao.getCurrent() == 1){
            esArticleSearchDao.setCurrent(0);
        }
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        if(!StringUtils.isEmpty(esArticleSearchDao.getFieldName()) && !StringUtils.isEmpty(esArticleSearchDao.getFieldValue())){
            MultiMatchQueryBuilder multiMatchQueryBuilder =
                    QueryBuilders.multiMatchQuery(esArticleSearchDao.getFieldValue(), esArticleSearchDao.getFieldName()).minimumShouldMatch("100%").field(esArticleSearchDao.getFieldName(), 0);
            MatchPhraseQueryBuilder matchPhraseQueryBuilder = QueryBuilders.matchPhraseQuery(esArticleSearchDao.getFieldName(), esArticleSearchDao.getFieldValue());


            BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
            boolQueryBuilder.should(multiMatchQueryBuilder).must(matchPhraseQueryBuilder);

            if("content".equals(esArticleSearchDao.getFieldName())) {
                nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
            }else{

               /* BoolQueryBuilder boolQueryBuilder1 = new BoolQueryBuilder();
                boolQueryBuilder1.should(QueryBuilders.existsQuery("content"));

                nativeSearchQueryBuilder.withQuery(boolQueryBuilder1);*/
                nativeSearchQueryBuilder.withQuery(multiMatchQueryBuilder);
            }
            List<Article> searchTotal = elasticsearchRestTemplate.queryForList(nativeSearchQueryBuilder.build(), Article.class);


            Page<Article> articlePage = ElasticUtils.pageResult(esArticleSearchDao.getCurrent(), esArticleSearchDao.getSize(), searchTotal);
            articlePage.setTotal(Optional.ofNullable(searchTotal.size()).orElse(0));
            return CommonResult.success(articlePage);
        }


        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.mustNot(QueryBuilders.existsQuery("content"));
        NativeSearchQuery nativeQuery = nativeSearchQueryBuilder.build();
        List<Article> searchTotal = elasticsearchRestTemplate.queryForList(nativeQuery, Article.class);

        nativeSearchQueryBuilder.withPageable(ElasticUtils.pageable(Optional.ofNullable(esArticleSearchDao.getCurrent()).orElse(0),Optional.ofNullable(esArticleSearchDao.getSize()).orElse(10)));
        List<Article> searchWithoutTotal = elasticsearchRestTemplate.queryForList(nativeSearchQueryBuilder.build(), Article.class);


        Page<Article> articlePage = ElasticUtils.pageResult(esArticleSearchDao.getCurrent(), esArticleSearchDao.getSize(), searchWithoutTotal);
        articlePage.setTotal(Optional.ofNullable(searchTotal.size()).orElse(0));
        return CommonResult.success(articlePage);
    }

    @Override
    public CommonResult deleteArticleById(String id) {
        elasticsearchRestTemplate.delete(Article.class,null);
        return null;
    }


}
