package com.zjt.graduation.serverfile.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
/*import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


/**
 * @Author hyh
 * @Date: 2020/10/25 15:21
 * @Version 1.0
 */
@Configuration
public class Config {

    @Autowired
    private PayConfig payConfig;
/*

    @Bean
    public GridFSBucket getGridFSBucket(com.mongodb.MongoClient mongoClient){
        MongoDatabase waht = mongoClient.getDatabase("test");
        return   GridFSBuckets.create(waht);
    }
*/

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE )
    public AlipayClient alipayClient(){
        AlipayClient alipayClient = new DefaultAlipayClient(payConfig.getServerUrl(),payConfig.getAppId(),payConfig.getPrivateKey(),"json","GBK",payConfig.getPublicKey(),payConfig.getSignType());
        return alipayClient;
    }

  /*  @Bean
    public RestHighLevelClient restHighLevelClient(RestClientBuilder restClientBuilder){
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        return restHighLevelClient;
    }*/


}

