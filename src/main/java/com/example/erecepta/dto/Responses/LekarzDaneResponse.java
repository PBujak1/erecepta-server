package com.example.erecepta.dto.Responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LekarzDaneResponse {
    private String idLekarza;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String adres;
    private String telefon;
    private String email;
    private String wiek;
    private String plec;
    private String nrPzw;
    private String specjalizacja;
    private String opis;

    public LekarzDaneResponse() {
    }

    public LekarzDaneResponse(
            String idLekarza,
            String imie,
            String nazwisko,
            String pesel,
            String adres,
            String telefon,
            String email,
            String wiek,
            String plec,
            String nrPzw,
            String specjalizacja,
            String opis
    ) {
        this.idLekarza = idLekarza;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.adres = adres;
        this.telefon = telefon;
        this.email = email;
        this.wiek = wiek;
        this.plec = plec;
        this.nrPzw = nrPzw;
        this.specjalizacja = specjalizacja;
        this.opis = opis;
    }
}
