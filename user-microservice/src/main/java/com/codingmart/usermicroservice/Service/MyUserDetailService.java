package com.codingmart.usermicroservice.Service;

import com.codingmart.usermicroservice.Entity.UserData;
import com.codingmart.usermicroservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public  UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        UserData user = userRepository.findByEmailId(emailId);
        return new User(user.getEmailId(), user.getSupplier().getPassword(),new ArrayList<>());
    }
}
