package com.example.erecepta.controllers;

import com.example.erecepta.dto.PacjentResponse;
import com.example.erecepta.services.PacjentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacjent")
public class PacjentController {

    private final PacjentService pacjentService;

    public PacjentController(PacjentService pacjentService) {
        this.pacjentService = pacjentService;
    }

    @GetMapping("/{pesel}")
    public PacjentResponse getPacjent(@PathVariable String pesel) {

        return pacjentService.getPacjentByPesel(pesel);

    }

}