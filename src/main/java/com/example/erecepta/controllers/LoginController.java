package com.example.erecepta.controllers;

import com.example.erecepta.dto.LoginRequest;
import com.example.erecepta.dto.LoginResponse;
import com.example.erecepta.services.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/pacjent")
    public LoginResponse loginPacjent(@RequestBody LoginRequest request) {

        System.out.println("nazwisko: " + request.getNazwisko());
        System.out.println("PESEL: " + request.getPesel());
        LoginResponse response = loginService.loginPacjent(request);

        if (response == null) {
            throw new RuntimeException("Błędny login lub hasło");
        }

        return response;
    }
}