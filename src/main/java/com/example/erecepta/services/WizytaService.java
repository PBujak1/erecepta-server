package com.example.erecepta.services;

import com.example.erecepta.dto.Responses.HistoriaPacjentaResponse;
import com.example.erecepta.dto.Responses.LekarzResponse;
import com.example.erecepta.dto.Responses.WizytaResponse;
import com.example.erecepta.entity.Lekarz;
import com.example.erecepta.entity.Pacjent;
import com.example.erecepta.entity.Wizyta;
import com.example.erecepta.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WizytaService {

    private final WizytaRepository wizytaRepository;
    private final LekarzRepository lekarzRepository;
    private final PacjentRepository pacjentRepository;
    private final ReceptaRepository receptaRepository;
    private final ReceptaPozycjaRepository receptaPozycjaRepository;

    public WizytaService(WizytaRepository wizytaRepository, LekarzRepository lekarzRepository, PacjentRepository pacjentRepository, ReceptaRepository receptaRepository, ReceptaPozycjaRepository receptaPozycjaRepository) {
        this.wizytaRepository = wizytaRepository;
        this.lekarzRepository = lekarzRepository;
        this.pacjentRepository = pacjentRepository;
        this.receptaRepository = receptaRepository;
        this.receptaPozycjaRepository = receptaPozycjaRepository;
    }

    public List<WizytaResponse> getWizytyPacjenta(Integer idPacjenta) {

        List<Wizyta> wizyty = wizytaRepository.findByIDPacjenta(idPacjenta);

        return wizyty.stream()
                .filter(wizyta -> !wizyta.getDataWizyty().isAfter(LocalDate.now()))
                .map(wizyta -> new WizytaResponse(wizyta.getDataWizyty()))
                .toList();
    }


    public List<LekarzResponse> getLekarzePacjenta(Integer idPacjenta) {
        List<Wizyta> wizyty = wizytaRepository.findByIDPacjenta(idPacjenta);

        return wizyty.stream()
                .map(Wizyta::getIDLekarza)
                .distinct()
                .map(lekarzRepository::findByIdLekarza)
                .flatMap(Optional::stream)
                .map(lekarz -> new LekarzResponse(
                        lekarz.getImie(),
                        lekarz.getNazwisko()
                ))
                .toList();
    }


    public List<HistoriaPacjentaResponse> getHistoriaPacjenta(Integer idPacjenta) {

        List<Wizyta> wizyty = wizytaRepository.findByIDPacjenta(idPacjenta);

        return wizyty.stream()
                .filter(wizyta -> !wizyta.getDataWizyty().isAfter(LocalDate.now()))
                .map(wizyta -> {
                    Optional<Lekarz> lekarz = lekarzRepository.findByIdLekarza(wizyta.getIDLekarza());
                    Optional<Pacjent> pacjent = pacjentRepository.findByIdPacjenta(wizyta.getIDPacjenta());

                    String idPozycji = receptaRepository
                            .findByIDWizyty(wizyta.getIDWizyty())
                            .flatMap(r -> receptaPozycjaRepository
                                    .findByIdRecepty(r.getIDRecepty())
                                    .stream()
                                    .findFirst())
                            .map(p -> String.valueOf(p.getIdPozycji()))
                            .orElse(null);

                    return new HistoriaPacjentaResponse(
                            wizyta.getDataWizyty(),
                            lekarz.map(l ->
                                    l.getImie() + " " + l.getNazwisko()
                            ).orElse(null),

                            pacjent.map(p ->
                                    p.getImie() + " " + p.getNazwisko()
                            ).orElse(null),
                            idPozycji
                    );
                })
                .toList();
    }

    public List<HistoriaPacjentaResponse> getNadchodzaceWizyty(Integer idPacjenta) {

        List<Wizyta> wizyty = wizytaRepository.findByIDPacjenta(idPacjenta);

        return wizyty.stream()
                .filter(wizyta -> wizyta.getDataWizyty().isAfter(LocalDate.now()))
                .map(wizyta -> {
                    Optional<Lekarz> lekarz = lekarzRepository.findByIdLekarza(wizyta.getIDLekarza());
                    Optional<Pacjent> pacjent = pacjentRepository.findByIdPacjenta(wizyta.getIDPacjenta());

                    return new HistoriaPacjentaResponse(
                            wizyta.getDataWizyty(),
                            lekarz.map(l ->
                                    l.getImie() + " " + l.getNazwisko()
                            ).orElse(null),

                            pacjent.map(p ->
                                    p.getImie() + " " + p.getNazwisko()
                            ).orElse(null)
                    );
                })
                .toList();
    }

}
