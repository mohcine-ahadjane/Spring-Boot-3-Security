package com.example.springsecurity.services;

import com.example.springsecurity.entities.MyUser;
import com.example.springsecurity.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private MyUserRepository myUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = myUserRepository.findByUsername(username);
        var userObj =user.get();
        if (user.isPresent()) {
            return  User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword()) // 1234
                    .roles(userObj.getRoles().split(","))
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
