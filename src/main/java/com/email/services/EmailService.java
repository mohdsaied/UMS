package com.email.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendEmail(String to,String object,String message){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(object);
        simpleMailMessage.setText(message);
        javaMailSender.send(simpleMailMessage);
    }
}
