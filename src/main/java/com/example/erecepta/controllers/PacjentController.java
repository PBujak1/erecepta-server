package com.example.erecepta.controllers;

import com.example.erecepta.dto.*;
import com.example.erecepta.services.PacjentService;
import com.example.erecepta.services.ReceptaService;
import com.example.erecepta.services.WizytaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacjent")
public class PacjentController {

    private final PacjentService pacjentService;
    private final WizytaService wizytaService;
    private final ReceptaService receptaService;

    public PacjentController(
            PacjentService pacjentService,
            WizytaService wizytaService,
            ReceptaService receptaService
    ) {
        this.wizytaService = wizytaService;
        this.pacjentService = pacjentService;
        this.receptaService = receptaService;
    }

    @GetMapping("/{pesel}")
    public PacjentResponse getPacjent(@PathVariable String pesel) {

        return pacjentService.getPacjentByPesel(pesel);

    }

    @GetMapping("/{pesel}/wizyty")
    public List<WizytaResponse> getWizytyPacjenta(@PathVariable String pesel) {
        return wizytaService.getWizytyPacjenta(pacjentService.getPacjentIdByPesel(pesel));
    }

    @GetMapping("/{pesel}/lekarzePacjenta")
    public List<LekarzResponse> getLekarzePacjenta(@PathVariable String pesel) {
        return wizytaService.getLekarzePacjenta(pacjentService.getPacjentIdByPesel(pesel));
    }

    @GetMapping("/{pesel}/dawkowanie")
    public List<ReceptaResponse> getDawkowanie(@PathVariable String pesel) {
        return receptaService.getRecepta(pacjentService.getPacjentIdByPesel(pesel));
    }

    @GetMapping("/{pesel}/historia")
    public List<HistoriaPacjentaResponse> getHistoriaPacjenta(@PathVariable String pesel) {
        return wizytaService.getHistoriaPacjenta(pacjentService.getPacjentIdByPesel(pesel));
    }
}