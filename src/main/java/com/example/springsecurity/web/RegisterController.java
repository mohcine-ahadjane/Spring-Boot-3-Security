package com.example.springsecurity.web;

import com.example.springsecurity.entities.MyUser;
import com.example.springsecurity.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private MyUserService userService;
    @PostMapping
    public MyUser register(@RequestBody MyUser user){
        return userService.register(user);
    }
}
