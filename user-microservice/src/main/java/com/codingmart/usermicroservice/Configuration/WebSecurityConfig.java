package com.codingmart.usermicroservice.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String[] WHITE_LIST_URLS = {
            "/supplier/all",
            "/user",
            "/supplier",
            "/user/all",
            " /user/verifyregistration",
            "/Hello",
            "/changePassword",
            "/resetPassword",
            "/user/SavePassword"


    };
//    //    public void configure(AuthenticationManagerBuilder auth)
////      throws Exception {
////
////        auth.inMemoryAuthentication()
////            .withUser("myusername")
////            .password("mypassword");
//////            .roles("USER");
////    }
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors()
//                .and()
//                .csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .antMatchers(WHITE_LIST_URLS).permitAll();
//                .antMatchers("/api/**").authenticated()
//                .and()
//                .oauth2Login(oauth2Login ->oauth2Login.loginPage("/oauth2/authorization/api-client-oidc") )
//                .oauth2Client(Customizer.withDefaults());


//        return http.build();
    }
//}

