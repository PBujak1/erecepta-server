package com.example.erecepta.controllers;

import com.example.erecepta.dto.Responses.LekarzResponse;
import com.example.erecepta.services.LekarzService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lekarz")
public class LekarzController {
    private final LekarzService lekarzService;

    public LekarzController(LekarzService lekarzService) {
        this.lekarzService = lekarzService;
    }

    @GetMapping("/{dane}/daneLekarza")
    public List<LekarzResponse> getDaneLekarza(@PathVariable String dane) {
        String[] czesci = dane.split("_", 2);
        return lekarzService.getDaneLekarza(czesci[0], czesci[1]);
    }
}
