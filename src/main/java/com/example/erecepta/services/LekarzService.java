package com.example.erecepta.services;

import com.example.erecepta.dto.Requests.LekarzDaneRequest;
import com.example.erecepta.dto.Requests.ReceptaPozycjaRequest;
import com.example.erecepta.dto.Requests.ReceptaRequest;
import com.example.erecepta.dto.Responses.*;
import com.example.erecepta.entity.Lek;
import com.example.erecepta.entity.Recepta;
import com.example.erecepta.entity.ReceptaPozycja;
import com.example.erecepta.entity.Wizyta;
import com.example.erecepta.repository.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LekarzService {
    private final LekarzRepository lekarzRepository;
    private final SpecjalizacjaRepository specjalizacjaRepository;
    private final WizytaRepository wizytaRepository;
    private final ReceptaRepository receptaRepository;
    private final ReceptaPozycjaRepository receptaPozycjaRepository;
    private final PacjentRepository pacjentRepository;
    private final LekRepository lekRepository;
    private final AlergieRepository alergieRepository;

    public LekarzService(
            LekarzRepository lekarzRepository,
            SpecjalizacjaRepository specjalizacjaRepository,
            WizytaRepository wizytaRepository,
            ReceptaRepository receptaRepository,
            ReceptaPozycjaRepository receptaPozycjaRepository,
            PacjentRepository pacjentRepository,
            LekRepository lekRepository,
            AlergieRepository alergieRepository
    ) {
        this.lekarzRepository = lekarzRepository;
        this.specjalizacjaRepository = specjalizacjaRepository;
        this.wizytaRepository = wizytaRepository;
        this.receptaRepository = receptaRepository;
        this.receptaPozycjaRepository = receptaPozycjaRepository;
        this.pacjentRepository = pacjentRepository;
        this.lekRepository = lekRepository;
        this.alergieRepository = alergieRepository;
    }

    public Optional<LekarzResponse> getDaneLekarza(String imie, String nazwisko) {
        return lekarzRepository.findByImieAndNazwisko(imie, nazwisko)
                .map(l -> new LekarzResponse(
                        l.getNrPzw(),
                        l.getEmail(),
                        l.getNumerTelefonu(),
                        l.getOpis(),
                        l.getPesel()
                ));
    }

    /*
        Metoda która dynamicznie wyszukuje lekarzy na podstawie frazy
     */
    public List<LekarzResponse> szukajLekarzy(String fraza, int limit) {
        String oczyszczona = fraza == null ? "" : fraza.trim();
        if (oczyszczona.length() < 2) {
            return List.of();
        }
        int bezpiecznyLimit = Math.min(Math.max(limit, 1), 20);
        return lekarzRepository.szukajLekarzy(oczyszczona, PageRequest.of(0, bezpiecznyLimit))
                .stream()
                .map(l -> new LekarzResponse(
                        l.getImie(),
                        l.getNazwisko()))
                .toList();
    }

    /*
        Metoda działająca jak szukanie lekarzy tylko do specjalizacji
     */
    public List<SpecjalizacjaResponse> szukajSpecjalizacji(String fraza, int limit) {
        String oczyszczona = fraza == null ? "" : fraza.trim();
        if (oczyszczona.length() < 2) {
            return List.of();
        }
        int bezpiecznyLimit = Math.min(Math.max(limit, 1), 20);
        return specjalizacjaRepository.szukajSpecjalizacji(oczyszczona, PageRequest.of(0, bezpiecznyLimit))
                .stream()
                .map(l -> new SpecjalizacjaResponse(
                        l.getNazwaSpecjalizacji()))
                .toList();
    }

    /*
      Pobiera pełny profil lekarza po peselu.
    */
    public Optional<LekarzDaneResponse> getDaneLekarzaByPesel(String pesel) {
        return lekarzRepository.findByPesel(pesel)
                .map(l -> new LekarzDaneResponse(
                        String.valueOf(l.getIdLekarza()),
                        l.getImie(),
                        l.getNazwisko(),
                        l.getPesel(),
                        l.getAdres(),
                        l.getNumerTelefonu() != null ? String.valueOf(l.getNumerTelefonu()) : null,
                        l.getEmail(),
                        l.getWiek() != null ? String.valueOf(l.getWiek()) : null,
                        l.getPlec(),
                        l.getNrPzw() != null ? String.valueOf(l.getNrPzw()) : null,
                        l.getSpecjalizacja(),
                        l.getOpis()
                ));
    }



    /*
      Pobiera historię wizyt lekarza.
    */
    public Optional<List<HistoriaLekarzaResponse>> getHistoriaLekarza(Integer idLekarza) {
        return lekarzRepository.findByIdLekarza(idLekarza)
                .map(lekarz -> {
                    String nazwaLekarza = lekarz.getImie() + " " + lekarz.getNazwisko();
                    return wizytaRepository.findByIDLekarza(idLekarza).stream()
                            .map(w -> {
                                String nazwaPacjenta = pacjentRepository.findByIdPacjenta(w.getIDPacjenta())
                                        .map(p -> p.getImie() + " " + p.getNazwisko())
                                        .orElse("Nieznany");

                                String dataWizyty = w.getDataWizyty() != null ? w.getDataWizyty().toString() : null;

                                String idRecepty = String.valueOf(lekarzRepository
                                        .findByIdLekarza(w.getIDLekarza())
                                        .map(l -> l.getIdLekarza())
                                        .orElse(null));

                                return new HistoriaLekarzaResponse(
                                        String.valueOf(w.getIDWizyty()),
                                        dataWizyty,
                                        nazwaLekarza,
                                        String.valueOf(idLekarza),
                                        nazwaPacjenta,
                                        w.getIDPacjenta() != null ? String.valueOf(w.getIDPacjenta()) : null,
                                        idRecepty
                                );
                            })
                            .toList();
                });
    }

    /*
      Aktualizuje dane lekarza. Zwraca false jeśli lekarz nie istnieje.
    */
    public boolean updateDaneLekarza(LekarzDaneRequest request) {
        return lekarzRepository.findByPesel(request.getPesel())
                .map(lekarz -> {
                    if (request.getImie() != null)
                        lekarz.setImie(request.getImie());
                    if (request.getNazwisko() != null)
                        lekarz.setNazwisko(request.getNazwisko());
                    if (request.getAdres() != null)
                        lekarz.setAdres(request.getAdres());
                    if (request.getEmail() != null)
                        lekarz.setEmail(request.getEmail());
                    if (request.getPlec() != null)
                        lekarz.setPlec(request.getPlec());
                    if (request.getTelefon() != null) {
                        try {
                            lekarz.setNumerTelefonu(Integer.parseInt(request.getTelefon()));
                        } catch (NumberFormatException ignored) {
                        }
                    }
                    if (request.getWiek() != null) {
                        try {
                            lekarz.setWiek(Integer.parseInt(request.getWiek()));
                        } catch (NumberFormatException ignored) {}
                    }
                    lekarzRepository.save(lekarz);
                    return true;
                })
                .orElse(false);
    }

    /*
      Aktualizuje dane lekarza
    */
    public boolean aktualizujDaneLekarza(String pesel, LekarzDaneRequest request) {
        return lekarzRepository.findByPesel(pesel)
                .map(lekarz -> {
                    if (request.getImie() != null)
                        lekarz.setImie(request.getImie());
                    if (request.getNazwisko() != null)
                        lekarz.setNazwisko(request.getNazwisko());
                    if (request.getNowyPesel() != null)
                        lekarz.setPesel(request.getNowyPesel());
                    if (request.getAdres() != null)
                        lekarz.setAdres(request.getAdres());
                    if (request.getEmail() != null)
                        lekarz.setEmail(request.getEmail());
                    if (request.getPlec() != null)
                        lekarz.setPlec(request.getPlec());
                    if (request.getTelefon() != null) {
                        try {
                            lekarz.setNumerTelefonu(Integer.parseInt(request.getTelefon()));
                        } catch (NumberFormatException ignored) {}
                    }
                    if (request.getWiek() != null) {
                        try {
                            lekarz.setWiek(Integer.parseInt(request.getWiek()));
                        } catch (NumberFormatException ignored) {}
                    }
                    lekarzRepository.save(lekarz);
                    return true;
                })
                .orElse(false);
    }

    /*
      Zwraca samo idLekarza po peselu.
     */
    public Optional<String> getIdLekarzaByPesel(String pesel) {
        return lekarzRepository.findByPesel(pesel)
                .map(l -> String.valueOf(l.getIdLekarza()));
    }
}
