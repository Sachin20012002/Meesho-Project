package com.codingmart.usermicroservice.Event.Listener;

import com.codingmart.usermicroservice.Entity.User;
import com.codingmart.usermicroservice.Event.RegistrationCompleteEvent;
import com.codingmart.usermicroservice.Service.MailSenderService;
import com.codingmart.usermicroservice.Service.UserService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private UserService userService;
    @Autowired
    private MailSenderService emailService;

    @Override
    //create verification token for user with link
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User user1 = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token,user1);
        //send email to the user
        String url = event.getApplicationUrl() + "/user"
                + "/verifyregistration?token="
                + token;
      emailService.sendSimpleEmail("pugaloviya.p@codingmart.com",url, "userService");
    }

}
