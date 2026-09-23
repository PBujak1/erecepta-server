package com.example.erecepta.controllers;

import com.example.erecepta.dto.LekarzResponse;
import com.example.erecepta.dto.LoginRequest;
import com.example.erecepta.dto.LoginResponse;
import com.example.erecepta.dto.WizytaResponse;
import com.example.erecepta.entity.Wizyta;
import com.example.erecepta.services.LoginService;
import com.example.erecepta.services.WizytaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;
    private final WizytaService wizytaService;

    public LoginController(LoginService loginService, WizytaService wizytaService) {
        this.loginService = loginService;
        this.wizytaService = wizytaService;
    }

    @PostMapping("/pacjent")
    public LoginResponse loginPacjent(@RequestBody LoginRequest request) {

        System.out.println("nazwisko: " + request.getNazwisko());
        System.out.println("PESEL: " + request.getPesel());
        LoginResponse response = loginService.getPacjentByNazwiskoAndPesel(request);

        if (response == null) {
            throw new RuntimeException("Błędny login lub hasło");
        }

        return response;
    }

    @PostMapping("/lekarz")
    public LoginResponse loginLekarz(@RequestBody LoginRequest request) {

        System.out.println("nazwisko: " + request.getNazwisko());
        System.out.println("PESEL: " + request.getPesel());
        LoginResponse response = loginService.getLekarzByNazwiskoAndPesel(request);

        if (response == null) {
            throw new RuntimeException("Błędny login lub hasło");
        }

        return response;
    }


    @GetMapping("/pacjent/{pesel}/wizyty")
    public List<WizytaResponse> getWizytyPacjenta(@PathVariable String pesel) {

        return wizytaService.getWizytyPacjenta(
                loginService.getPacjentIdByPesel(pesel)
        );
    }

    @GetMapping("/pacjent/{pesel}/lekarzePacjenta")
    public List<LekarzResponse> getLekarzePacjenta(@PathVariable String pesel) {

        return wizytaService.getLekarzePacjenta(loginService.getPacjentIdByPesel(pesel));
    }
}