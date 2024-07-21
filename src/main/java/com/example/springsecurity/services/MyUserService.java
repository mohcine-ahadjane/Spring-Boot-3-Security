package com.example.springsecurity.services;

import com.example.springsecurity.entities.MyUser;
import com.example.springsecurity.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserService {
    @Autowired
    private MyUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public MyUser register(MyUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
