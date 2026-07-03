package com.example.erecepta.services;

import com.example.erecepta.dto.LoginRequest;
import com.example.erecepta.dto.LoginResponse;
import com.example.erecepta.model.Pacjent;
import com.example.erecepta.repository.PacjentRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final PacjentRepository pacjentRepository;

    public LoginService(PacjentRepository pacjentRepository) {
        this.pacjentRepository = pacjentRepository;
    }

    public LoginResponse loginPacjent(LoginRequest request) {

        Pacjent pacjent = pacjentRepository.findByPeselAndNazwisko(request.getPesel(), request.getHaslo()).orElse(null);

        if (pacjent == null) {
            return null;
        }

        return new LoginResponse(
                pacjent.getImie(),
                pacjent.getNazwisko()
        );
    }
}