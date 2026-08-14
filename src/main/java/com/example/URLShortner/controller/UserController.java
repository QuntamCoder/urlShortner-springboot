package com.example.URLShortner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;


@RestController
public class UserController {
    @GetMapping("/api/user/profile")

 public String profile(Authentication authentication){
        return  "Hello"+authentication.getName();
    }
}
