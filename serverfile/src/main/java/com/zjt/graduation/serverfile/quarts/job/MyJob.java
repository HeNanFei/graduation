package com.zjt.graduation.serverfile.quarts.job;

import com.zjt.graduation.serverfile.clazz.TestClazz;
import lombok.SneakyThrows;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author hyh
 * @Date: 2020/10/25 18:45
 * @Version 1.0
 */
@Component
public class MyJob extends QuartzJobBean {


    @Autowired
    private ApplicationContext applicationContext;
    
    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobKey key = jobDetail.getKey();
        key.getName();

      /*  MongoTemplate bean = applicationContext.getBean(MongoTemplate.class);
        bean.dropCollection(TestClazz.class);*/
        TestClazz testClazz = new TestClazz();
        testClazz.setMobile("the job is running"+ LocalDateTime.now());
        testClazz.setName("the job is running"+ LocalDateTime.now());

       // bean.insert(testClazz,"theWorld");
          /*  Query query = Query.query(Criteria.where("name").is("name"));
            List<TestClazz> testClazzes = bean.find(query, TestClazz.class);
            System.out.println(testClazzes);
            TestClazz test = new TestClazz();
            test.
                    setName(String.valueOf(LocalDateTime.now()))
                    .setMobile("mobile");
            bean.insert(test);*/
            //bean.insert(new TestClazz().setMobile(String.valueOf(LocalDateTime.now())).setName(jobDetail.getJobDataMap().toString()),"testClazz");

    }
}
