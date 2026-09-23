package com.example.erecepta.services;

import com.example.erecepta.dto.LekarzResponse;
import com.example.erecepta.dto.WizytaResponse;
import com.example.erecepta.entity.Lekarz;
import com.example.erecepta.entity.Wizyta;
import com.example.erecepta.repository.LekarzRepository;
import com.example.erecepta.repository.WizytaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WizytaService {

    private final WizytaRepository wizytaRepository;
    private final LekarzRepository lekarzRepository;

    public WizytaService(WizytaRepository wizytaRepository, LekarzRepository lekarzRepository) {
        this.wizytaRepository = wizytaRepository;
        this.lekarzRepository = lekarzRepository;
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

        //ROZWIĄZANIE CHATUGPT
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
}
