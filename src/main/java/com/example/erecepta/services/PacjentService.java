package com.example.erecepta.services;

import com.example.erecepta.dto.PacjentResponse;
import com.example.erecepta.model.Pacjent;
import com.example.erecepta.repository.PacjentRepository;
import org.springframework.stereotype.Service;

@Service
public class PacjentService {
    private final PacjentRepository pacjentRepository;

    public PacjentService(PacjentRepository pacjentRepository) {
        this.pacjentRepository = pacjentRepository;
    }

    public PacjentResponse getPacjentByPesel(String pesel) {
        Pacjent pacjent = pacjentRepository
                .findByPesel(pesel)
                .orElseThrow(() -> new RuntimeException("Pacjent nie istnieje"));

        return new PacjentResponse(
                pacjent.getImie(),
                pacjent.getNazwisko(),
                pacjent.getWiek(),
                pacjent.getPlec(),
                pacjent.getNumerTelefonu(),
                pacjent.getEmail(),
                pacjent.getAdres(),
                pacjent.getPesel()
        );
    }
}
