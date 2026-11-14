package com.example.erecepta.services;

import com.example.erecepta.dto.Responses.PacjentDaneResponse;
import com.example.erecepta.dto.Responses.PacjentResponse;
import com.example.erecepta.dto.Responses.WizytyResponse;
import com.example.erecepta.entity.Pacjent;
import com.example.erecepta.entity.Recepta;
import com.example.erecepta.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacjentService {
    private final PacjentRepository pacjentRepository;
    private final WizytaRepository wizytaRepository;
    private final LekarzRepository lekarzRepository;
    private final AlergieRepository alergieRepository;
    private final ReceptaRepository receptaRepository;

    public PacjentService(
            PacjentRepository pacjentRepository,
            WizytaRepository wizytaRepository,
            LekarzRepository lekarzRepository,
            AlergieRepository alergieRepository,
            ReceptaRepository receptaRepository) {
        this.pacjentRepository = pacjentRepository;
        this.wizytaRepository = wizytaRepository;
        this.lekarzRepository = lekarzRepository;
        this.alergieRepository = alergieRepository;
        this.receptaRepository = receptaRepository;
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


    public Integer getPacjentIdByPesel(String pesel) {

        Pacjent pacjent = pacjentRepository.findByPesel(pesel)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono pacjenta"));

        return pacjent.getIdPacjenta();
    }

    /*
     Pobiera dane pacjenta (karta pacjenta) po peselu — dla widoku lekarza
   */
    public Optional<PacjentDaneResponse> getDanePacjentaByPesel(String pesel) {
        return pacjentRepository.findByPesel(pesel)
                .map(pacjent -> {
                    List<WizytyResponse> wizyty = wizytaRepository
                            .findByIDPacjenta(pacjent.getIdPacjenta())
                            .stream()
                            .map(w -> {
                                String nazwaLekarza = lekarzRepository
                                        .findByIdLekarza(w.getIDLekarza())
                                        .map(l -> l.getImie() + " " + l.getNazwisko())
                                        .orElse("Nieznany");
                                String idRecepty = String.valueOf(receptaRepository
                                        .findByIDWizyty(w.getIDWizyty())
                                        .map(Recepta::getIDRecepty)
                                        .orElse(null));
                                String dataWizyty = w.getDataWizyty() != null ? w.getDataWizyty().toString() : null;

                                return new WizytyResponse(
                                        dataWizyty,
                                        nazwaLekarza,
                                        idRecepty
                                );
                            })
                            .toList();

                    List<String> alergie = alergieRepository
                            .findByIDPacjenta(pacjent.getIdPacjenta())
                            .stream()
                            .map(a -> a.getNazwa())
                            .toList();

                    return new PacjentDaneResponse(
                            pacjent.getImie(),
                            pacjent.getNazwisko(),
                            pacjent.getPesel() != null ? Integer.parseInt(pacjent.getPesel()) : null,
                            pacjent.getWiek(),
                            pacjent.getPlec(),
                            pacjent.getAdres(),
                            wizyty,
                            alergie
                    );
                });
    }
}
