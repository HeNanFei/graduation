package com.zjt.graduation.common.config.mail;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author hyh
 * @Date: 2020/10/2 21:47
 * @Version 1.0
 */
@Component
@ConfigurationProperties("mail")
@Data
public class PropertiesSet {

    // 邮件服务器的主机名:如 "smtp.qq.com"，不同的邮箱服务器主机名是不一样的，例如网易163邮箱是"smtp.163.com"
    private String mailServer; //= "smtp.qq.com";
    // 登录邮箱的账号:如我的 "872060821@qq.com"，这里改成你自己的
    private String loginAccount; //= "872060821@qq.com";
    // 登录邮箱时候需要的授权码:可以进入邮箱,账号设置那里"生成授权码"，这里改成你自己的
    private String loginAuthCode; //= "cigyxkjkaeytbedf";
    // 发件人，发件人的邮箱，跟上面那个一样就可以了
    private String sender; //= "872060821@qq.com";




}
