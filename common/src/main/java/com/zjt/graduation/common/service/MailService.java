package com.zjt.graduation.common.service;

import java.util.List;
import java.util.Map;

/**
 * @Author hyh
 * @Date: 2020/10/4 21:03
 * @Version 1.0
 */
public interface MailService {

    Integer sendMail();


    Map sendBySingle();

    Integer sendMail(String message, List<String> emails);

}
