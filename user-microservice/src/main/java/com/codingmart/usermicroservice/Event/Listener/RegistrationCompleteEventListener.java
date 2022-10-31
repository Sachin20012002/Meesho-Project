package com.codingmart.usermicroservice.Event.Listener;

import com.codingmart.usermicroservice.Entity.UserData;
import com.codingmart.usermicroservice.Event.RegistrationCompleteEvent;
import com.codingmart.usermicroservice.Service.MailSenderService;
import com.codingmart.usermicroservice.Service.UserService;
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
        UserData userData1 = event.getUserData();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, userData1);
        //send email to the user
        String url = event.getApplicationUrl() + "/user"
                + "/verifyregistration?token="
                + token;
      emailService.sendSimpleEmail("pugaloviya.p@codingmart.com",url, "userService");
    }

}
