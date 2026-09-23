package com.example.erecepta.services;

import com.example.erecepta.dto.WizytaResponse;
import com.example.erecepta.entity.Wizyta;
import com.example.erecepta.repository.WizytaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WizytaService {

    private final WizytaRepository wizytaRepository;

    public WizytaService(WizytaRepository wizytaRepository) {
        this.wizytaRepository = wizytaRepository;
    }

    public List<WizytaResponse> getWizytyPacjenta(Integer idPacjenta) {

        List<Wizyta> wizyty = wizytaRepository.findByIDPacjenta(idPacjenta);

        return wizyty.stream()
                .map(wizyta -> new WizytaResponse(wizyta.getDataWizyty()))
                .toList();
    }
}
