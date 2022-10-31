package com.codingmart.usermicroservice.Controller;

import com.codingmart.usermicroservice.Common.APIResponse;
import com.codingmart.usermicroservice.Entity.Password;
import com.codingmart.usermicroservice.Entity.UserData;
import com.codingmart.usermicroservice.Event.RegistrationCompleteEvent;
import com.codingmart.usermicroservice.Service.MailSenderService;
import com.codingmart.usermicroservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private UserService userService;
    @Autowired
    private MailSenderService emailService;

    @GetMapping("/all")
    public APIResponse getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/userById/{id}")
    public APIResponse findUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/verifyregistration")
    public String verifyRegistration(@RequestParam("token") String token) {
//        System.out.println(token + " oviya");
        String result = userService.validateVerificationToken(token);
        System.out.println(result);
        if (result.equals("valid")) {
            return "user verified successfully";
        } else {
            return "badUser";
        }
    }

    @PostMapping("")
    public String adduser(@RequestBody UserData userData, HttpServletRequest request) {
        UserData userData1 = userService.addUser(userData);

        publisher.publishEvent(new RegistrationCompleteEvent(userData1, applicationUrl(request)));
        return "success";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody Password password, HttpServletRequest request) {
        UserData userData = userService.findUserByEmailId(password.getEmailId());
        String url = "";
        if (userData != null) {
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(userData, token);
            url = passwordResetTokenMail(userData, applicationUrl(request), token);
        }
        return url;

    }

    @PostMapping("/SavePassword")
    public String SavePassword(@RequestParam("token") String token, @RequestBody Password password) {
        String result = userService.ValidatePasswordResetToken(token);
        if (!result.equalsIgnoreCase("valid")) {
            return "Invalid token";
        }
        Optional<UserData> user = userService.getUserByPasswordResetToken(token);
        if(user.isPresent()) {
            userService.changePassword(user.get(), password.getNewPassword());
            return "Password Reset Successfully";
        } else {
            return "Invalid Token";
        }
    }
    @PostMapping("/changePassword")
    public String changePassword(@RequestBody Password password){
        UserData userData = userService.findUserByEmailId(password.getEmailId());
        if(!userService.checkIfValidOldPassword(userData,password.getOldPassword()))
        {
            return "Invalid Old Password";
        }
        //Save New Password
        userService.changePassword(userData,password.getNewPassword());
        return "Password Changed Successfully";
    }






    private String passwordResetTokenMail(UserData userData, String applicationUrl, String token) {
        String url = applicationUrl + "/user"
                + "/SavePassword?token="
                + token;
         emailService.sendSimpleEmail("pugaloviya.p@codingmart.com", url,  "userservice");
//        log.info("click the link to reset your password", url);
        return url;


    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }


    @PostMapping("/addUser")
    public void addUsers(@RequestBody List<UserData> userData) {
        userService.saveUser(userData);
    }

    @PutMapping("/update")
    public UserData updateUser(@RequestBody UserData userData) {
        return userService.updateUser(userData);

    }

    @DeleteMapping("/delete")
    public void deleteUserById(@PathVariable long id) {
        userService.deleteUser(id);

    }
}




