package com.example.dxw.movie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MailImpl {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;
    public Object se(Map map) {
        Object zh = map.get("zh");
        Object mail = map.get("mail");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo("zhusinianhua@qq.com ");
        simpleMailMessage.setSubject("测试这是标题");
        simpleMailMessage.setText("测试这是内容");
        javaMailSender.send(simpleMailMessage);
        return null;
    }
}
