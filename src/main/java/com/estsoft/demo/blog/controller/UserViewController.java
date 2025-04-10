package com.estsoft.demo.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {
    // "/login"
    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    // "/signup"
    @GetMapping("/signup")
    public String signUpView() {
        return "signup";
    }
}
