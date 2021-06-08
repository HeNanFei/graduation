package com.zjt.graduation.es;

import com.zjt.graduation.es.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.StringQuery;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EsApplicationTests {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public  void doIt(){
        StringQuery stringQuery = new StringQuery("article");

        elasticsearchRestTemplate.queryForList(stringQuery,Article.class);
    }
   /* @Test
    void contextLoads() {


        MultiMatchQueryBuilder field = QueryBuilders.multiMatchQuery("冷面杀手", "username").minimumShouldMatch("100%").field("username", 0);

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        nativeSearchQueryBuilder.withQuery(field);
        NativeSearchQuery build = nativeSearchQueryBuilder.build();

        org.springframework.data.elasticsearch.core.SearchHits<Article> search = elasticsearchRestTemplate.search(build, Article.class);
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(field);
        elasticsearchRestTemplate.search(nativeSearchQuery,Article.class);

        IndexCoordinates article = IndexCoordinates.of("article");
        elasticsearchRestTemplate.indexOps(article);
        Article article1 = new Article();
        article1.setUserName("9");
        elasticsearchRestTemplate.save(article1);

        System.out.println(search);
    }
*/
  /*  @Test
    public void doSomething(){
        MultiMatchQueryBuilder field = QueryBuilders.multiMatchQuery("老何", "content").minimumShouldMatch("100%").field("content", 0);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        NativeSearchQuery build = nativeSearchQueryBuilder.withQuery(field).build();
        SearchHits<Article> search = elasticsearchRestTemplate.search(build, Article.class);
        System.out.println(search);
    }
*/
   /* @Test
    public void termQuery(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("content","");
        NativeSearchQuery build = nativeSearchQueryBuilder.withQuery(termQueryBuilder).build();
        SearchHits<Article> search = elasticsearchRestTemplate.search(build, Article.class);

    }*/
  /*  @Test
    public void matchQuery(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("attribute","world");
        NativeSearchQueryBuilder nativeSearchQueryBuilder1 = nativeSearchQueryBuilder.withQuery(matchQueryBuilder);
        NativeSearchQuery build = nativeSearchQueryBuilder1.build();
        SearchHits<Article> search = elasticsearchRestTemplate.search(build, Article.class);

        MultiMatchQueryBuilder field = QueryBuilders.multiMatchQuery("content", "content").minimumShouldMatch("100%").field("content", 0);
        nativeSearchQueryBuilder1.withQuery(field);
        NativeSearchQuery build1 = nativeSearchQueryBuilder1.build();
        SearchHits<Article> search1 = elasticsearchRestTemplate.search(build1, Article.class);
        System.out.println(search);
    }
    */
    
   /* @Test
    public void afraid(){
        RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder("click");
        rangeQueryBuilder.lt(80);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        NativeSearchQuery build = nativeSearchQueryBuilder.withQuery(rangeQueryBuilder).build();
        build.setPageable(ElasticUtils.pageable(1,5));
        SearchHits<Article> search = elasticsearchRestTemplate.search(build, Article.class);

        Page page = new Page();
        page.setCurrent(1);
        page.setSize(5);
 Page<Article> articlePage = ElasticUtils.pageResult(page, search);

        List<SearchHit<Article>> searchHits = search.getSearchHits();
        for (int i = 0; i < searchHits.size(); i++) {
            SearchHit<Article> articleSearchHit = searchHits.get(i);
            Article article = articleSearchHit.getContent();
        }

    }*/




    @Test
    public void lll(){

        List<Article> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Article article = new Article();
            article.setUserName((String.valueOf(i)));
            article.setContent("9");
            article.setId(Long.valueOf(i));
            article.setAid(2L);
            article.setAttribute("aaaa");
            article.setClick(1);
            article.setContentBlank("blank");
            article.setImgUrl("lasst");
            article.setStatus(2);
            article.setTitle("title");
            article.setWrited(String.valueOf(LocalDateTime.now()));
            article.setDeleted(0L);
            article.setDataChangeBy(222L);
            article.setDataChangeTime(LocalDateTime.now());
            article.setDataCreateTime(LocalDateTime.now());
            list.add(article);
        }
       /* for (int i = 0; i < list.size(); i++) {
            elasticsearchRestTemplate.save(list.get(i));
        }*/
 /* TermsQueryBuilder termsQueryBuilder = new TermsQueryBuilder("userName","9");
        CriteriaQuery criteriaQuery = new CriteriaQuery(Criteria.where("userName").contains("9"));
        StringQuery stringQuery = new StringQuery("9");
        SearchHits<Article> search = elasticsearchRestTemplate.search(stringQuery, Article.class);
*/
    }


}
