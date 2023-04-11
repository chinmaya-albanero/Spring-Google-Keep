package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/home")
    public String home(){
return "this is homePage";
    }
    @GetMapping("/login")
    public String login(){
        return "This is login page";
    }
    @GetMapping("/register")
    public String register(){
        return "This is registation page";
    }
}
