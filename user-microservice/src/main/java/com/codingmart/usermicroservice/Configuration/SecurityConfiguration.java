package com.codingmart.usermicroservice.Configuration;
import com.codingmart.usermicroservice.Filter.JWTFilter;
import com.codingmart.usermicroservice.Service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.AuthenticationManagerBeanDefinitionParser;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private JWTFilter jwtFilter;
    protected void configure(AuthenticationManagerBuilder auth)throws Exception
    {
        auth.userDetailsService(myUserDetailService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean()throws Exception
    {
        return super.authenticationManagerBean();
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
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/authenticate")
                .permitAll()
                .antMatchers(WHITE_LIST_URLS).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }
}

