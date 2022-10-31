package com.codingmart.usermicroservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender mailSender;


    public void sendSimpleEmail(String toEmail,
                                String body,
                                String subject)
    {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("oviya16082001@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

    }
}
