package com.example.erecepta.services;

import com.example.erecepta.dto.Requests.ReceptaPozycjaRequest;
import com.example.erecepta.dto.Requests.ReceptaRequest;
import com.example.erecepta.dto.Responses.ReceptaLekarzResponse;
import com.example.erecepta.dto.Responses.ReceptaResponse;
import com.example.erecepta.entity.Lek;
import com.example.erecepta.entity.Recepta;
import com.example.erecepta.entity.ReceptaPozycja;
import com.example.erecepta.entity.Wizyta;
import com.example.erecepta.entity.enums.StatusRecepty;
import com.example.erecepta.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReceptaService {
    private final LekRepository lekRepository;
    private final ReceptaRepository receptaRepository;
    private final LekarzRepository lekarzRepository;
    private final WizytaRepository wizytaRepository;
    private final ReceptaPozycjaRepository receptaPozycjaRepository;
    private final PacjentRepository pacjentRepository;

    public ReceptaService(LekRepository lekRepository,
                          ReceptaRepository receptaRepository,
                          LekarzRepository lekarzRepository,
                          WizytaRepository wizytaRepository,
                          ReceptaPozycjaRepository receptaPozycjaRepository,
                          PacjentRepository pacjentRepository
    ) {
        this.lekRepository = lekRepository;
        this.receptaRepository = receptaRepository;
        this.lekarzRepository = lekarzRepository;
        this.wizytaRepository = wizytaRepository;
        this.receptaPozycjaRepository = receptaPozycjaRepository;
        this.pacjentRepository = pacjentRepository;
    }

    public List<ReceptaResponse> getRecepta(Integer pacjentIdByPesel) {
        if (pacjentIdByPesel == null) {
            return List.of();
        }

        List<Wizyta> wizyty = wizytaRepository.findByIDPacjenta(pacjentIdByPesel);

        return wizyty.stream()
                .filter(w -> w.getIDWizyty() != null)
                .map(w -> receptaRepository.findByIDWizyty(w.getIDWizyty()))
                .flatMap(Optional::stream)
                .flatMap(recepta -> receptaPozycjaRepository.findByIdRecepty(recepta.getIDRecepty()).stream())
                .map(pozycja -> {
                    String nazwaLeku = lekRepository.findLekByIdLeku(pozycja.getIdLeku())
                            .map(Lek::getNazwa)
                            .orElse("Nieznany");
                    String liczbaOpakowan = pozycja.getLiczbaOpakowan() != null ? pozycja.getLiczbaOpakowan().toString() : null;
                    String dawkowanie = pozycja.getDawkowanie() != null ? pozycja.getDawkowanie().toString() : null;

                    return new ReceptaResponse(nazwaLeku, liczbaOpakowan, dawkowanie);
                })
                .toList();
    }


    /*
      Pobiera listę recept wystawionych przez lekarza (przez wizyty).
      Każda recepta może mieć wiele pozycji (leków) — każda pozycja
      zwracana jest jako osobny ReceptaLekarzResponse.
    */
    public Optional<List<ReceptaLekarzResponse>> getReceptyLekarza(Integer idLekarza) {
        if (idLekarza == null || lekarzRepository.findByIdLekarza(idLekarza).isEmpty()) {
            return Optional.empty();
        }

        List<Wizyta> wizyty = wizytaRepository.findByIDLekarza(idLekarza);

        List<ReceptaLekarzResponse> wynik = wizyty.stream()
                .filter(w -> w.getIDWizyty() != null)
                .flatMap(w -> receptaRepository.findByIDWizyty(w.getIDWizyty())
                        .stream()
                        .flatMap(recepta -> {
                            String dataWystawienia = recepta.getDataWystawienia() != null
                                    ? recepta.getDataWystawienia().toString()
                                    : (w.getDataWizyty() != null ? w.getDataWizyty().toString() : null);
                            String idPacjenta = w.getIDPacjenta() != null
                                    ? String.valueOf(w.getIDPacjenta()) : null;
                            String idRecepty = String.valueOf(recepta.getIDRecepty());

                            return receptaPozycjaRepository.findByIdRecepty(recepta.getIDRecepty())
                                    .stream()
                                    .map(p -> {
                                        String nazwaLeku = lekRepository.findLekByIdLeku(p.getIdLeku())
                                                .map(Lek::getNazwa)
                                                .orElse("Nieznany");

                                        String odplatnosc = p.getRefundacja() != null && p.getRefundacja() == 1
                                                ? "Refundowany" : "100%";

                                        return new ReceptaLekarzResponse(
                                                idRecepty,
                                                nazwaLeku,
                                                idPacjenta,
                                                p.getLiczbaOpakowan() != null ? String.valueOf(p.getLiczbaOpakowan()) : null,
                                                odplatnosc,
                                                p.getDawkowanie() != null ? String.valueOf(p.getDawkowanie()) : null,
                                                dataWystawienia
                                        );
                                    });
                        }))
                .toList();

        return Optional.of(wynik);
    }


    /*
        Dodanie recepty do bazy wraz z pozycjami leków
     */
    @Transactional
    public boolean dodajRecepte(String pesel, ReceptaRequest request) {
        var pacjent = pacjentRepository.findByPesel(pesel);
        if (pacjent.isEmpty()) {
            return false;
        }

        var lekarz = lekarzRepository.findByPesel(request.getPeselLekarza());
        if (lekarz.isEmpty()) {
            return false;
        }

        // Przygotowujemy listę pozycji (z pola `leki` lub ze starego pojedynczego leku)
        List<ReceptaPozycjaRequest> pozycjeDoDodania = new ArrayList<>();
        if (request.getLeki() != null && !request.getLeki().isEmpty()) {
            pozycjeDoDodania.addAll(request.getLeki());
        } else if (request.getNazwaLeku() != null) {
            pozycjeDoDodania.add(new ReceptaPozycjaRequest(
                    request.getNazwaLeku(),
                    request.getLiczbaOpakowan(),
                    request.getOdplatnosc(),
                    request.getDawkowanie()
            ));
        }

        if (pozycjeDoDodania.isEmpty()) {
            return false;
        }

        // Tworzymy i zapisujemy wizytę
        Wizyta wizyta = new Wizyta();
        wizyta.setIDLekarza(lekarz.get().getIdLekarza());
        wizyta.setIDPacjenta(pacjent.get().getIdPacjenta());
        wizyta.setDataWizyty(LocalDate.now());
        Wizyta zapisanaWizyta = wizytaRepository.save(wizyta);

        // Tworzymy i zapisujemy receptę powiązaną z wizytą
        Recepta recepta = new Recepta();
        recepta.setIDWizyty(zapisanaWizyta.getIDWizyty());
        recepta.setDataWystawienia(LocalDate.now());
        recepta.setStatus(StatusRecepty.valueOf("wystawiona"));
        Recepta zapisanaRecepta = receptaRepository.save(recepta);

        // Tworzymy pozycje dla każdego leku
        for (ReceptaPozycjaRequest pozycjaReq : pozycjeDoDodania) {
            Lek lek = lekRepository.findByNazwa(pozycjaReq.getNazwaLeku())
                    .orElseGet(() -> {
                        Lek nowyLek = new Lek();
                        nowyLek.setNazwa(pozycjaReq.getNazwaLeku());
                        nowyLek.setIdRodzaju(1);
                        return lekRepository.save(nowyLek);
                    });

            ReceptaPozycja pozycja = new ReceptaPozycja();
            pozycja.setIdRecepty(zapisanaRecepta.getIDRecepty());
            pozycja.setIdLeku(lek.getIdLeku());
            pozycja.setLiczbaOpakowan(pozycjaReq.getLiczbaOpakowan());
            pozycja.setDawkowanie(pozycjaReq.getDawkowanie());
            pozycja.setRefundacja(pozycjaReq.getOdplatnosc());
            receptaPozycjaRepository.save(pozycja);
        }

        return true;
    }
}
