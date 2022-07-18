package com.dahuang;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.io.File;

@SpringBootTest
class Springboot09TestApplicationTests {


    @Autowired
    JavaMailSenderImpl mailSender;
    @Test
    void contextLoads1() {

        //一个简单的邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("大黄，你好呀！");
        mailMessage.setText("谢谢你呀！");

        mailMessage.setTo("201302307@qq.com");
        mailMessage.setFrom("915323422@qq.com");

        mailSender.send(mailMessage);
    }

    @Test
    void contextLoads2() throws MessagingException {

        //一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        //组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        helper.setSubject("大黄，你好呀！");
        helper.setText("<h1 style='color:red'>谢谢你<h1>",true);

        // 附件
        helper.addAttachment("1.jpg",new File("C:\\Users\\Administrator\\Desktop\\1.jpg"));

        helper.setTo("915323422@qq.com");
        helper.setFrom("915323422@qq.com");

        mailSender.send(mimeMessage);
    }

}
