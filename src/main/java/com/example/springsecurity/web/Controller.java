package com.example.springsecurity.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller   // use Controller not RestController to return a html view
@RequestMapping()
public class Controller {
    @GetMapping("/home")
    String home(){
        return "view";
    }
    @GetMapping("/admin/home")
    String adminHome(){
        return "admin-view";
    }
    @GetMapping("/user/home")
    String userHome(){
        return "user-view";
    }
    @GetMapping("/login")
    String handleLogin(){
        return "custom-login";
    }
}
