package com.example.erecepta.services;

import com.example.erecepta.dto.*;
import com.example.erecepta.entity.Lekarz;
import com.example.erecepta.entity.Pacjent;
import com.example.erecepta.entity.Recepta;
import com.example.erecepta.entity.Wizyta;
import com.example.erecepta.repository.LekarzRepository;
import com.example.erecepta.repository.PacjentRepository;
import com.example.erecepta.repository.ReceptaRepository;
import com.example.erecepta.repository.WizytaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WizytaService {

    private final WizytaRepository wizytaRepository;
    private final LekarzRepository lekarzRepository;
    private final PacjentRepository pacjentRepository;
    private final ReceptaRepository receptaRepository;

    public WizytaService(WizytaRepository wizytaRepository, LekarzRepository lekarzRepository, PacjentRepository pacjentRepository, ReceptaRepository receptaRepository) {
        this.wizytaRepository = wizytaRepository;
        this.lekarzRepository = lekarzRepository;
        this.pacjentRepository = pacjentRepository;
        this.receptaRepository = receptaRepository;
    }

    public List<WizytaResponse> getWizytyPacjenta(Integer idPacjenta) {

        List<Wizyta> wizyty = wizytaRepository.findByIDPacjenta(idPacjenta);

        return wizyty.stream()
                .map(wizyta -> new WizytaResponse(wizyta.getDataWizyty()))
                .toList();
    }


    public List<LekarzResponse> getLekarzePacjenta(Integer pacjentIdByPesel) {
        /*
        MOJE ROZWIĄZANIE
        1.SZUKAMY IDLEKARZA Z WIZYT DO KTÓRYCH CHODZIŁ KONKRETNY PACJENT
        2.NA PODSTAWIE ID SZUKAMY IMIENIA I NAZWISKA LEKARZA I DODAJEMY DO LISTY

        List<Wizyta> wizyty = wizytaRepository.findByIDPacjenta(pacjentIdByPesel);
        Optional<Lekarz> lekarze = lekarzRepository.findByIdLekarza(wizyty.getFirst().getIDLekarza());

        return lekarze.stream()
                .map(lekarz -> new LekarzResponse(lekarz.getImie() + " " + lekarz.getNazwisko() ))
                .toList();

         */


        //ROZWIĄZANIE CHATAGPT
        List<Wizyta> wizyty = wizytaRepository.findByIDPacjenta(pacjentIdByPesel);

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
                            ).orElse(null),

                            wizyta.getIDRecepty()
                    );
                })
                .toList();
    }

}
