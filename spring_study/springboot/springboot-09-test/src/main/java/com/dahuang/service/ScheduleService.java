package com.dahuang.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScheduleService {


    //在一个特定的时间内执行这个方法  ~  Timer

    //cron 表达式~
    // 秒 分 时 日 月 周几~
    @Scheduled(cron = "0/10 * * * * ?")
    public void hello(){
        Date date = new Date();
        System.out.println(date+"hello,你被执行了！");
    }
}
