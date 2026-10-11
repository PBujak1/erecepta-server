package com.example.erecepta.services;

import com.example.erecepta.dto.Responses.LekarzResponse;
import com.example.erecepta.repository.LekarzRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class LekarzService {
    private static LekarzRepository lekarzRepository = null;

    public LekarzService(LekarzRepository lekarzRepository) {
        this.lekarzRepository = lekarzRepository;
    }

    public static List<LekarzResponse> szukajLekarzy(String fraza, int limit) {
        String oczyszczona = fraza == null ? "" : fraza.trim();
        if (oczyszczona.length() < 2) {
            return List.of();
        }

        int bezpiecznyLimit = Math.min(Math.max(limit, 1), 20);

        return lekarzRepository.szukaj(oczyszczona, PageRequest.of(0, bezpiecznyLimit))
                .stream()
                .map(l -> new LekarzResponse(
                        l.getImie(),
                        l.getNazwisko()
                ))
                .toList();
    }
}
