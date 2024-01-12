package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class SendMail {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 邮件的发送方
     */
    @Value("${spring.mail.username}")
    private String sendFrom;
    public void sendTxtMail1(String accept, String topic, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendFrom);
        message.setTo(accept);
        message.setSubject(topic);
        message.setText(content);
        javaMailSender.send(message);
    }


}