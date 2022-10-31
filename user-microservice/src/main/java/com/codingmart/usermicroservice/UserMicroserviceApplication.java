package com.codingmart.usermicroservice;


import com.codingmart.usermicroservice.Service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class
UserMicroserviceApplication {
	public static void main(String[] args) {

		SpringApplication.run(UserMicroserviceApplication.class, args);
	}
}
//		@EventListener(ApplicationReadyEvent.class)
//	  public void triggerEmail() {
//	 mailService.sendSimpleEmail("pugaloviya.p@codingmart.com", "hello","userverified");
//	}
