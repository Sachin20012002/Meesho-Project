package com.codingmart.usermicroservice.Event;

import com.codingmart.usermicroservice.Entity.User;
import com.codingmart.usermicroservice.Service.MailSenderService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private User user;
    private  String applicationUrl;
    @Autowired
    private MailSenderService emailService;

    public RegistrationCompleteEvent( User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
//    RegistrationCompleteEventListener.onApplicationEvent(RegistrationCompleteEvent);
//    emailService.sendsimpleEmail("pugaloviya.p@codingmart.com", "helo", "userservice");
}
