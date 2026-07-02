package com.example.erecepta.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/test")
    public String test() {
        return "Spring Boot działa!";
    }
}