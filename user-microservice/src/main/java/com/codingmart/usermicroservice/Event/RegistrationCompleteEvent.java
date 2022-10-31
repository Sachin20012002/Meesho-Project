package com.codingmart.usermicroservice.Event;

import com.codingmart.usermicroservice.Entity.UserData;
import com.codingmart.usermicroservice.Service.MailSenderService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private UserData userData;
    private  String applicationUrl;
    @Autowired
    private MailSenderService emailService;

    public RegistrationCompleteEvent(UserData userData, String applicationUrl) {
        super(userData);
        this.userData = userData;
        this.applicationUrl = applicationUrl;
    }
//    RegistrationCompleteEventListener.onApplicationEvent(RegistrationCompleteEvent);
//    emailService.sendsimpleEmail("pugaloviya.p@codingmart.com", "helo", "userservice");
}
