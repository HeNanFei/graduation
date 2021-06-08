package com.zjt.graduation.serverfile;

import cn.hutool.core.util.ZipUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjt.graduation.common.config.oss.AliOssConfig;
import com.zjt.graduation.common.entity.SellGood;
import com.zjt.graduation.common.entity.TGood;
import com.zjt.graduation.common.entity.TGoods;
import com.zjt.graduation.common.mapper.TGoodMapper;
import com.zjt.graduation.common.mapper.TGoodsMapper;
import com.zjt.graduation.common.utils.AliOssUtil;
import com.zjt.graduation.common.utils.ValidateUtils;
import com.zjt.graduation.common.validate.Last;
import com.zjt.graduation.serverfile.entity.Article;
import com.zjt.graduation.serverfile.utils.PayUtil;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.util.FileCopyUtils;

import javax.validation.ConstraintViolation;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/*
import com.zjt.graduation.serverfile.test.ThreadCallable;
*/

@SpringBootTest
class ServerfileApplicationTests {


/*

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    GridFsOperations operations;

    @Autowired
    private GridFSBucket gridFSBucket;
*/

    @Autowired
    private TGoodMapper tGoodMapper;

    @Autowired
    private TGoodsMapper tGoodsMapper;

    @Autowired
    private AliOssConfig aliOssConfig;

    @Autowired
    private AliOssUtil aliOssUtil;
    
    @Autowired
    private PayUtil payUtil;

    @Autowired
    private OSS ssClent;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    @Autowired
    private RestHighLevelClient restHighLevelClient;


/*
    @Test
    public void test() throws IOException {
        //初始化mogonTemplate
        MongoOperations mongoOps = new MongoTemplate(new SimpleMongoClientDbFactory(MongoClients.create(), "test"));

        mongoTemplate.insert(new TestClazz().setMobile("mobile").setName("name"));
        TestClazz test = new TestClazz();
        test.setMobile("0823094");
        test.setName("shenme");

        mongoTemplate.find(new Query(Criteria.where("name").is("name")), TestClazz.class);

        //文件保存
        //gridFsTemplate.store(new FileInputStream("C:\\Users\\winter\\Desktop\\使用说明.txt"),"融资尊林企业模拟");


        //精确查询
        GridFSFile gr = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is("5fcb45bd72bccb461515419c")));

        //获取文件下载流
        GridFSDownloadStream gridFsDownloadStream = gridFSBucket.openDownloadStream(gr.getObjectId());
        byte[] bytes = FileCopyUtils.copyToByteArray(gridFsDownloadStream);

        FileOutputStream fileOutputStream1 = new FileOutputStream(new File("C:\\Users\\winter\\Desktop\\"+gr.getFilename()+".txt"));

        fileOutputStream1.write(bytes);
        fileOutputStream1.close();


    }*/

    @Test
    public void ttt(){
        List<TGood> tGoods = tGoodMapper.selectList(new QueryWrapper<>());
        List<TGoods> tGoods1 = tGoodsMapper.selectList(new QueryWrapper<>());
        System.out.println(tGoods);
    }
    
    @Test
    public void tttt(){
        Map<String,List<String>> checkData = new HashMap<>();
        checkData.put("platform_goods_code",tGoodsMapper.getColumn(new QueryWrapper()));
        checkData.put("goods_code",tGoodsMapper.getGoodsCode(new QueryWrapper()));
        checkData.put("brand_code",tGoodsMapper.getBrandCode(new QueryWrapper()));
        checkData.put("category_code",tGoodsMapper.getCategoryCode(new QueryWrapper()));
        checkData.put("category_name",tGoodsMapper.getCategoryName(new QueryWrapper()));
        checkData.put("name",tGoodsMapper.getName(new QueryWrapper()));
        checkData.put("second_category_code",tGoodsMapper.getSecondCategoryCode(new QueryWrapper()));
        checkData.put("second_category_name",tGoodsMapper.getSecondCategoryName(new QueryWrapper()));
        checkData.put("three_category_code",tGoodsMapper.getThreeCategoryCode(new QueryWrapper()));
        checkData.put("three_category_name",tGoodsMapper.getThreeCategoryName(new QueryWrapper()));
        checkData.put("brand_name",tGoodsMapper.getBrandName(new QueryWrapper()));
        checkData.put("title",tGoodsMapper.getTitle(new QueryWrapper()));

        List<String> id1 = tGoodsMapper.getColumn(new QueryWrapper<TGoods>().gt("id", 1));
        //List<TGoods> id = tGoodsMapper.selectList(new QueryWrapper<TGoods>().select("distinct(id) id,distinct(goods_code) goods_code"));
    }

    @Test
    public void ttts(){
        TGood tGood = new TGood();
        tGood.setBatch_no("jskdjf");
        TGoods tGoods = new TGoods();
        BeanUtils.copyProperties(tGood,tGoods);
        System.out.println(tGoods);
    }

    @Test
    public void ali() throws FileNotFoundException {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = "oss-cn-beijing.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = aliOssConfig.getBucket();
        String accessKeySecret = aliOssConfig.getSecret();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = new FileInputStream("C:\\Users\\winter\\Desktop\\商品模板 (2).xls");
        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(aliOssConfig.getBucketname(), "商品模板 (2).xls", inputStream);

        // 关闭OSSClient。
        //ossClient.shutdown();
    }

    @Test
    public void liast(){
        aliOssUtil.singleUpload("2020","graduation1.sql","C:\\Users\\winter\\Desktop\\graduation1.sql");
    }
    @Test
    public void lastEver() throws FileNotFoundException {
        aliOssUtil.singleUpload("2022/"+ LocalDateTime.now().getMonth().getValue()+"/"+LocalDateTime.now().getDayOfMonth(),"8.冒泡排序(Av415255592,P8).mp4","C:\\Users\\winter\\Desktop\\8.冒泡排序(Av415255592,P8).mp4");
        //aliUploadUtil.createDirectory("2022/"+ LocalDateTime.now().getMonth().getValue()+"/"+LocalDateTime.now().getDayOfMonth());
        //ssClent.putObject("winterfell","2021/",new File(""));
    }
    @Test
    public void listObject(){
        aliOssUtil.listAllFile(aliOssConfig.getBucketname());
    }

    @Test
    public void ll(){
        aliOssUtil.listDirectoryFiles(aliOssConfig.getBucketname(),"2022/"+ LocalDateTime.now().getMonth().getValue()+"/"+LocalDateTime.now().getDayOfMonth()+"/");
    }

    @Test
    public void doSomething(){
        ListObjectsV2Result listObjectsV2Result = aliOssUtil.listDirectoryFiles(aliOssConfig.getBucketname(), "2020/05/26/");
        System.out.println(listObjectsV2Result);
    }

    @Test
    public void batchDownLoad() throws IOException {
        //C:\Users\winter\Desktop
        ObjectListing allFiles = aliOssUtil.listAllFile("winterfell");
        List<OSSObjectSummary> objectSummaries = allFiles.getObjectSummaries();
        for (int i = 0; i < objectSummaries.size(); i++) {
            OSSObjectSummary ossObjectSummary = objectSummaries.get(i);
            try{
                String endpoint = aliOssConfig.getEndpoint();
                // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
                String accessKeyId = aliOssConfig.getBucket();
                String accessKeySecret = aliOssConfig.getSecret();

                // 创建OSSClient实例。
                OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
                OSSObject ossObject = ossClient.getObject(aliOssConfig.getBucketname(), ossObjectSummary.getKey());
                byte[] bytes = FileCopyUtils.copyToByteArray(ossObject.getObjectContent());
                String replace = ossObjectSummary.getKey().replace("/", "-");
                File file = new File("D:\\excel\\"+replace);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(bytes);
                fileOutputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        File file = new File("D:\\excel");
        File zip = ZipUtil.zip(file);
        byte[] bytes1 = FileCopyUtils.copyToByteArray(zip);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\home\\test.zip"));
        fileOutputStream.write(bytes1);
        fileOutputStream.close();
    }

    @Test
    public void doIt() throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = aliOssConfig.getEndpoint();
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = aliOssConfig.getBucket();
        String accessKeySecret = aliOssConfig.getSecret();
        String bucketName = aliOssConfig.getBucketname();
        //<yourObjectName>表示从OSS下载文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = "<yourObjectName>";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, objectName);
        // 对于大小为1000 Bytes的文件，正常的字节范围为0~999。
        // 获取0~999字节范围内的数据，包括0和999，共1000个字节的数据。如果指定的范围无效（比如开始或结束位置的指定值为负数，或指定值大于文件大小），则下载整个文件。
        getObjectRequest.setRange(0, 999);

        // 范围下载。
        OSSObject ossObject = ossClient.getObject(getObjectRequest);

        // 读取数据。
        byte[] buf = new byte[1024];
        InputStream in = ossObject.getObjectContent();
        for (int n = 0; n != -1; ) {
            n = in.read(buf, 0, buf.length);
        }

        // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        in.close();

        // 关闭OSSClient。
        //ossClient.shutdown();
    }
    
    @Test
    public void any(){
        SellGood sellGood = new SellGood();
        sellGood.setGoodName("jj");
        Set<ConstraintViolation<SellGood>> validate = ValidateUtils.getValidator().validate(sellGood);
        System.out.println(validate);
        ValidateUtils.getValidator().validateProperty(sellGood,"goodName", Last.class);
        //ValidateUtils.validateBean(sellGood, Last.class,null);
        //validator.validateProperty(sellGood,"goodName", Last.class);
    }

    @Test
    public void testDeleteVideo(){
        String s = payUtil.payForm();
        System.out.println(s);
    }

    @Test
    public void lastForever(){
        OSS ossClient = new OSSClientBuilder().build(aliOssConfig.getEndpoint(), "LTAI4GJsSpSzw8di3fH6zziK", "W3IoXiwM19KJNtVFcmdenQUjyNeX0q");

        final int maxKeys = 2;
        String nextMarker = null;
        ObjectListing objectListing;

        do {
            objectListing = ossClient.listObjects(new ListObjectsRequest("winterfell").withMarker(nextMarker).withMaxKeys(maxKeys));

            List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
            for (OSSObjectSummary s : sums) {
                System.out.println("\t" + s.getKey());
            }

            nextMarker = objectListing.getNextMarker();

        } while (objectListing.isTruncated());
    }

    public static void main(String[] args) throws IOException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do","2021000117610457",
                "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCrJdhIh+U9mfAjaxwn3O9UabYZXtFPe5fxXo2A3fPGUusAHDWyJbPip6e7ihifmPY4cAAYp65MajHA5Rf4/Wc56NCdTBWvwdZrWitC8Nfe0vpS0pFVNbgKJRrtjHr8S42d9rwRR/0koLjWuhfukPXO1VGVOo4kaDpnzhU5pD5uWAgDjJUFv5OYJIPpUIKGAtLZpvED0AUYPkRTbI/hI47zmtWFmSFZZ3OIVYnxyZw0hLR+ASYy7uF0AmpAMDs6iHbKej8qrx17PE4sPkUo0YRpTwEjYAC6oa+BJrhLoggG7wG/9ZlBDPx3lxhfT2rkqZUWRJ52ZTgWazQCn7qvB+R/AgMBAAECggEBAJT4oxM3HqYJGUprEB3upG+weC4LJ1vs86vIJplWGLu3LxBwfZisUrDmSAzovlglF6S2cI8FXaTsBpSllMFJKRHyAav5LyNGMPJOkTt6UhyFPhjbal9mG+TQuWiuD9sNlJ47xyWvqLu6Hknhv7ULfrpjWdjwGAu+oZ/7dOQwf5shGxeHCEB6dTBiM72XdAVhz49qm+6/S45x2POUAogDdPFMxTrMJGJjdldxUsW798W1M77XzI/YDCEpq4m0dqH/7cGYkJQLaBpauNT66Ywl8k0JW0QJ4d0W5epYt0EYwMil3FM2BmtsEW+W8WF1ZuqnFLa66a0rYoepfS7XU9sq6bkCgYEA8MZAamrgGGnHghyqeFfqTQSdtMOzeOHHWFMgj7VsjjZA4r2Ockb7tjDXVuqe7W2YV9UNXTuaAtgNeFsOKl7dRCv1Tje+bjh60ZOA39M5rRM+/Vim2EpiBBh1QmvSLKtGDY4w9hK71ZVU0kif/z5m4+kcJanCkK5fqbvEJEIMcmsCgYEAtfhzjkrIVc7xw6Z/dyIAkn/4o2ln+tcALQszvoZZI390OdLK5SVsIsAbXbbv4yUyOotczvoMXwKJh21yTdyReI60CMDgKQkBK60Q0c0qUAztpGwc/KlGWONn96ixtGPB4hKj1GHEIIRwCOtvFDF1pMTP5q4RyKjzTGGTPQ5EIz0CgYApR5Qbqp8pEFmoMQn4//mnorb3GuZWPz9SIU5f1Rb7esm9FgqqBQl6vvzsZeVBh5yq0lgsN9+7xRbqH5JaKVF+MpsoAd6gPwavj750oV4uEjKmdOCGDJqpYRgZfyrVhbfI8yvpxGR3xdZ5BwKHXEd/o6OzwVseOwcjrpF+NeS0aQKBgQCbzQ2jVwQP9A+rpHKeXc5/oHIR972/VzYfWugCyKSpcAbjO0fcyh274x+LLYZWxRIsbgYYcJ8G+fUmrt6fK6qhvg0HMZy7haoq6xLrju4be/Y8QRTQf15r3Mi3kwgTmxX1ql/7B3oVHU+PU4TXlUFUwe3/ZCp107H8Q879B6nvQQKBgQDFXUmb1/DcD4xjOl49DbyaCjac3I04JP4ZgwMBpyE6z7AIJN6UHvc1qvG0EOKJYGPRXLT8KfuMCh/mTB6yN7kVySqS98FGdRr0ylBHRnqE3+yyk/lWRN2HbELjChTnGuypM0xOVX2gN+Tdg+GPHEZoHNi9u0ojO59oBX5bcasLrQ==");
      /*  AlipayClient alipayClient = new
                DefaultAlipayClient ("https://openapi.alipaydev.com/gateway.do","2021000117610457","MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC4Z7ICiSE65kQ6fnU563njQViGbhpkyeeiql32T6/65y3kuv/R36RwtvODCoK6ScRm9Zuz+vIsmFOoutONdv5NM29xLge/rKTad7GkWubjMjROp4WqIUt7w9Invq4Kxiv47rYXvPtIEOuZ4e9RcKNYoBtZxdhZRWnO7Rp/jN2TGgNi8sH4ib2hBuJrWH33mrFGket/G0s8ledyzLx8i9QFmnl6OAmYrr6WnWlEWF4pGxKs+DlMViB+D62ZuB3kGZ5eXrkBZwyvcO3IWnbpvCvqiaAQwHjv5a7rA+ToKpBMs0IgNDu+7CtdJwJGrMq5KXxdCQeyn9G5CY1MRO/7ZJdNAgMBAAECggEAcx0QS3LtEoaSCu3MIfb0JMAzEeADi+1aMvolI9CE65yK0qFFdu8kCNS8WI4kI0RPXXJu20CJEvuYJdlSnJmuj+TO6Zkxbx7H3jUf6JhyT1uUuZDr6NNV+pG4S+VFYiZpZsElXNY1Cry9Rld6/JzS600vJbBSqQ+iyHDMvNjjrKJcldsiK/L7ePVOJg1U/zEtngBAeA49Avuj4UmjCAqMpCj2DWMeQJ0PiGmcS0c4tL6/+7F6BQb/hOpPTQjDpyCP8x4EqbaIr/+bbB7CfA7QNvLz85rMd+PMgCLSYVK/03/98BGcz3qNh9ucjalQgL79QhbxiazA0uTv8fz2P5dpQQKBgQDymtNgLGaB403D2t1Olc7iokv7y8ZwBlNNb9VAbu+qn8hjBHedbla1IVu5QJDztIugIZFuxRqGVtOZDww+33Xrcvxh8PDLJtI8kopNsQci8ea47kGO+04EDQvEkxkJwImz8mUGWBrmj9+I/kNnCsGPc1n1nrBsjcm52p2af5F0PQKBgQDCljomwFKRkBnnO8Or5xeMivGWt5ag2NTyP7R1an4T5H/rWzGdzsaxgyqElqhfX560c60wjBHa5MzO6zLtrhpz9wRaofwbfB2XZlhVKA3bvMN/rR6RIeODEeK1v2UUsn8vs/qeFrtcekXTUfLr8AmjOv4MdgaR3k1xeJGZpjcQUQKBgQCWaGS6EGzxBDJ3RZ7FFN2x5nsDPb/mPhWxQa9NhjmyZ+N9eAyMnxbEr0zFmoEDhqC2A3m92hdqyDPhhdHKvRHHin9sXUt8N7JJ+XQjXKiXArBPYO9mLOmeW1Q7aBMy15rH5HS7rS3oWbR1o2W4YZPFUven7t1kP7ecMSxa19Ie5QKBgBh/h2S3k+2loc099f2fg5R7Lb1W8czgX7b8FEEwHh1tCDnealm+3b8dj23P9fTD+hwn8CcLi3fulGZ8sBXCdKwi7lZmzHG9Z5BXH0nhWBVRJ5/ccNu+04D98HgHV7eKXX7o/uWGYSJcdowLZjUULHJzi+aPuXYF8eQWVOZuScJxAoGBAKcQ12qb3HxiWK0OWnJQ/+rS/CXj1HFwZY7BuHfj0nBPeR7wI2oHOw/GeglFi2kHzvNHnGWDRcVs5Za7kGrQoyoQU2cnjdtMoiX4Zwii5yXoQEezXy9Mdk1S6ubPN5RBPm2bx6oiQZBES7Ivap5erPRWwBL9yWPX8Qplgq/OYgKC","json","utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnPrj8wEVtO7vRY4OOwKQN4L/Ks9Cjz4NCaxaWgTM3zTpI/v1x0WrKsQbRJOzWNha8R8R10J4OvzbCHztjfyrGvu8U3cozzNyJC1WLn0RMoVuMy0pV+vaWa04YmutUHjm+kIbJvvPjVuweNL1JVbrn2OAZQOjQJ6xOZOonOY6zWyekd+G4wYJs8uJxjWKKrdYwA9aiwaAo9EgT0VfOoaqpkNtKEy6HgNZ2LqXaIKRg4e6/YPzJjoriQbZLPw25donCE7hFnSuq4JJDQ0zDdu9epmDU+ZTxdkKc2vactNy8Fh7/9lonjjIiDMhSq+ltIubmTP8tHhwEbs87L1ReVkOrwIDAQAB","RSA2" );
*/
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        /* //在公共参数中设置回跳和通知地址
        request.setReturnUrl(RETURN_URL);
        request.setNotifyUrl(NOTIFY_URL);*/

        //商户订单号，商户网站订单系统中唯一订单号，必填
        //生成随机Id
        String out_trade_no = UUID.randomUUID().toString();
        //付款金额，必填
        String total_amount =Integer.toString(360);
        //订单名称，必填
        String subject ="bread";
        //商品描述，可空
        String body = "bread";
        request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.println(form);
    }

    @Test
    public void esSearch(){
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("content","苏牙");
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(matchQueryBuilder);
        //List<Article> articles = elasticsearchRestTemplate.queryForList(nativeSearchQuery, Article.class);
        List<Article> articles = elasticsearchRestTemplate.queryForList(nativeSearchQuery, Article.class);
        System.out.println(articles);
    }

}
