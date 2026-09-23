package com.example.erecepta.services;

import com.example.erecepta.dto.LoginRequest;
import com.example.erecepta.dto.LoginResponse;
import com.example.erecepta.entity.Lekarz;
import com.example.erecepta.entity.Pacjent;
import com.example.erecepta.repository.LekarzRepository;
import com.example.erecepta.repository.PacjentRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final PacjentRepository pacjentRepository;
    private final LekarzRepository lekarzRepository;

    public LoginService(PacjentRepository pacjentRepository,
                        LekarzRepository lekarzRepository) {
        this.pacjentRepository = pacjentRepository;
        this.lekarzRepository = lekarzRepository;
    }

    public LoginResponse getPacjentByNazwiskoAndPesel(LoginRequest request) {

        Pacjent pacjent = pacjentRepository.findByNazwiskoAndPesel(request.getNazwisko(), request.getPesel()).orElse(null);

        if (pacjent == null) {
            return null;
        }

        return new LoginResponse(
                pacjent.getImie(),
                pacjent.getNazwisko()
        );
    }

    public Integer getPacjentIdByPesel(String pesel) {

        Pacjent pacjent = pacjentRepository.findByPesel(pesel)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono pacjenta"));

        return pacjent.getIdPacjenta();
    }

    public LoginResponse getLekarzByNazwiskoAndPesel(LoginRequest request) {
        Lekarz lekarz = lekarzRepository.findByNazwiskoAndPesel(request.getNazwisko(), request.getPesel()).orElse(null);

        if (lekarz == null) {
            return null;
        }

        return new LoginResponse(
                lekarz.getImie(),
                lekarz.getNazwisko()
        );
    }
}