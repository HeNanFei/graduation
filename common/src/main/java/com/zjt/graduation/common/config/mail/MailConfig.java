package com.zjt.graduation.common.config.mail;

import cn.hutool.core.util.NumberUtil;
import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @Author hyh
 * @Date: 2020/10/2 21:45
 * @Version 1.0
 */
@Component
public class MailConfig {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static Logger logger = LoggerFactory.getLogger(MailConfig.class);

    @Autowired
    private PropertiesSet propertiesSet;
    /**
     * 发送邮件工具类:通过qq邮件发送,因为具有ssl加密,采用的是smtp协议
     *
     * @param recipients
     *            收件人:支持群发
     * @param emailSubject
     *            邮件的主题
     * @param emailContent
     *            邮件的内容
     * @param emailContentType
     *            邮件内容的类型,支持纯文本:"text/plain;charset=utf-8";,带有Html格式的内容:
     *            "text/html;charset=utf-8"
     * @return res
     *                发送条数
     */
    public  int sendEmail(String[] receiverAddresses, String emailSubject, String emailContent, String emailContentType) {
        int res = 0;
        try {
            // 跟smtp服务器建立一个连接
            Properties p = new Properties();
            // 设置邮件服务器主机名
            p.setProperty("mail.host", propertiesSet.getMailServer());
            // 发送服务器需要身份验证,要采用指定用户名密码的方式去认证
            p.setProperty("mail.smtp.auth", "true");
            // 发送邮件协议名称
            p.setProperty("mail.transport.protocol", "smtp");


            // 开启SSL加密，否则会失败
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            p.put("mail.smtp.ssl.enable", "true");
            p.put("mail.smtp.ssl.socketFactory", sf);


            // 创建session
            Session session = Session.getDefaultInstance(p,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            // 用户名可以用QQ账号也可以用邮箱的别名:第一个参数为邮箱账号,第二个为授权码
                            PasswordAuthentication pa = new PasswordAuthentication(
                                    propertiesSet.getLoginAccount(), propertiesSet.getLoginAuthCode());
                            return pa;
                        }
                    });


            // 设置打开调试状态
            session.setDebug(true);


            // 可以发送几封邮件:可以在这里 for循环多次
            // 声明一个Message对象(代表一封邮件),从session中创建
            MimeMessage msg = new MimeMessage(session);
            // 邮件信息封装


            // 1发件人
            InternetAddress sendInternetAddress = new InternetAddress(propertiesSet.getSender());
            msg.setFrom(sendInternetAddress);

            // 2一个的收件人
            /*InternetAddress receiveInternetAddress = new InternetAddress(receiverAddress);
            msg.setRecipient(RecipientType.TO, receiveInternetAddress);*/


            // 2多个收件人,把String数组转成InternetAddress数组
            InternetAddress[] receiveInternetAddresses = new
                    InternetAddress[receiverAddresses.length];
            for (int i = 0; i < receiverAddresses.length; i++) {
                receiveInternetAddresses[i]  = new
                        InternetAddress(receiverAddresses[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, receiveInternetAddresses);



            // 3邮件内容:主题、内容
            msg.setSubject(emailSubject);
            // msg.setContent("Hello, 我是debug!!!", );//纯文本
            msg.setContent(emailContent, emailContentType);// 发html格式的文本


            //验证码
            //int[] ints = NumberUtil.generateRandomNumber(1, 999999, receiveInternetAddresses.length);
            //String contentSend = String.format(msg,String.valueOf(ints[]));
            // 发送动作
                Transport.send(msg);

            logger.info("邮件发送成功");
            res = 1;
        } catch (Exception e) {
            logger.info("邮件发送失败");
            res = 0;
        }
        return res;
    }


    public  Map sendEmaiBySingle(String[] receiverAddresses, String emailSubject, String emailContent, String emailContentType){
        int res = 0;
        //结果集
        Map<String,String> map = new HashMap<>();
        try {
            // 跟smtp服务器建立一个连接
            Properties p = new Properties();
            // 设置邮件服务器主机名
            p.setProperty("mail.host", propertiesSet.getMailServer());
            // 发送服务器需要身份验证,要采用指定用户名密码的方式去认证
            p.setProperty("mail.smtp.auth", "true");
            // 发送邮件协议名称
            p.setProperty("mail.transport.protocol", "smtp");


            // 开启SSL加密，否则会失败
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            p.put("mail.smtp.ssl.enable", "true");
            p.put("mail.smtp.ssl.socketFactory", sf);


            // 创建session
            Session session = Session.getDefaultInstance(p,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            // 用户名可以用QQ账号也可以用邮箱的别名:第一个参数为邮箱账号,第二个为授权码
                            PasswordAuthentication pa = new PasswordAuthentication(
                                    propertiesSet.getLoginAccount(), propertiesSet.getLoginAuthCode());
                            return pa;
                        }
                    });


            // 设置打开调试状态
            session.setDebug(true);


            // 可以发送几封邮件:可以在这里 for循环多次
            // 声明一个Message对象(代表一封邮件),从session中创建
            MimeMessage msg = new MimeMessage(session);
            // 邮件信息封装


            // 1发件人
            InternetAddress sendInternetAddress = new InternetAddress(propertiesSet.getSender());
            msg.setFrom(sendInternetAddress);



            //验证码
            int[] ints = NumberUtil.generateRandomNumber(1, 999999, receiverAddresses.length);

            for (int i=0;i<receiverAddresses.length;i++) {

                // 2一个的收件人
                InternetAddress receiveInternetAddress = new InternetAddress(receiverAddresses[i]);

                msg.setRecipient(MimeMessage.RecipientType.TO, receiveInternetAddress);
                msg.setRecipients(Message.RecipientType.TO,receiverAddresses[i]);
                msg.setSubject(emailSubject);
                msg.setContent(emailContent, emailContentType);// 发html格式的文本

                String contentSend = String.format(emailContent,String.valueOf(ints[i]));
                stringRedisTemplate.opsForValue().set(String.valueOf(ints[i]),String.valueOf(ints[i]));
                stringRedisTemplate.expire(String.valueOf(ints[i]),1, TimeUnit.MINUTES);
                msg.setContent(contentSend,emailContentType);
                // 发送动作
                Transport.send(msg);

                logger.info("邮件发送成功");

                res ++;
                map.put("total",String.valueOf(res));
            }
        } catch (Exception e) {
            logger.info("邮件发送失败");
            res = 0;
        }
        return map;
    }


}
