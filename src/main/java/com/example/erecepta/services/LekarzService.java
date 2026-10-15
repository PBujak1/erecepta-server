package com.example.erecepta.services;

import com.example.erecepta.dto.Responses.LekarzResponse;
import com.example.erecepta.dto.Responses.SpecjalizacjaResponse;
import com.example.erecepta.dto.Responses.WizytaResponse;
import com.example.erecepta.entity.Lekarz;
import com.example.erecepta.entity.Wizyta;
import com.example.erecepta.repository.LekarzRepository;
import com.example.erecepta.repository.SpecjalizacjaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LekarzService {
    private static LekarzRepository lekarzRepository = null;
    private static SpecjalizacjaRepository specjalizacjaRepository = null;

    public LekarzService(LekarzRepository lekarzRepository, SpecjalizacjaRepository specjalizacjaRepository) {
        this.lekarzRepository = lekarzRepository;
        this.specjalizacjaRepository = specjalizacjaRepository;
    }

    public static List<LekarzResponse> getDaneLekarza(String imie, String nazwisko) {
        Optional<Lekarz> lekarz = lekarzRepository.findByImieAndNazwisko(imie, nazwisko);

        return lekarz.stream()
                .map(lekarze -> new LekarzResponse(
                        lekarze.getNrPzw(),
                        lekarze.getEmail(),
                        lekarze.getNumerTelefonu(),
                        lekarze.getOpis(),
                        lekarze.getPesel()
                ))
                .toList();
    }

    public static List<LekarzResponse> szukajLekarzy(String fraza, int limit) {
        String oczyszczona = fraza == null ? "" : fraza.trim();
        if (oczyszczona.length() < 2) {
            return List.of();
        }

        int bezpiecznyLimit = Math.min(Math.max(limit, 1), 20);

        return lekarzRepository.szukajLekarzy(oczyszczona, PageRequest.of(0, bezpiecznyLimit))
                .stream()
                .map(l -> new LekarzResponse(
                        l.getImie(),
                        l.getNazwisko()
                ))
                .toList();
    }

    public static List<SpecjalizacjaResponse> szukajSpecjalizacji(String fraza, int limit) {
        String oczyszczona = fraza == null ? "" : fraza.trim();
        if (oczyszczona.length() < 2) {
            return List.of();
        }

        int bezpiecznyLimit = Math.min(Math.max(limit, 1), 20);

        return specjalizacjaRepository.szukajSpecjalizacji(oczyszczona, PageRequest.of(0, bezpiecznyLimit))
                .stream()
                .map(l -> new SpecjalizacjaResponse(
                        l.getNazwaSpecjalizacji()
                ))
                .toList();
    }
}
