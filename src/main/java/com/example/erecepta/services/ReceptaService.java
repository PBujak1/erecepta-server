package com.example.erecepta.services;

import com.example.erecepta.dto.Responses.ReceptaResponse;
import com.example.erecepta.entity.Recepta;
import com.example.erecepta.repository.LekRepository;
import com.example.erecepta.repository.ReceptaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceptaService {
    private final LekRepository lekRepository;
    private final ReceptaRepository receptaRepository;

    public ReceptaService(LekRepository lekRepository, ReceptaRepository receptaRepository) {
        this.lekRepository = lekRepository;
        this.receptaRepository = receptaRepository;
    }

    public List<ReceptaResponse> getRecepta(Integer pacjentIdByPesel ) {
        List<Recepta> recepty = receptaRepository.findByIDPacjenta(pacjentIdByPesel);

        return recepty.stream()
                .map(recepta -> lekRepository.findLekByIDLeku(recepta.getIDLeku())
                        .map(lek -> new ReceptaResponse(
                            lek.getNazwa(),
                            recepta.getLiczbaOpakowan().toString(),
                            recepta.getDawkowanie().toString()
                        ))
                )
                .flatMap(Optional::stream)
                .toList();
    }
}
