package com.example.erecepta.dto.Responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LekarzResponse {
    private String imie;
    private String nazwisko;
    private Integer nrPZW;
    private String specjalizacja;
    private String email;
    private String adres;
    private Integer numerTelefonu;
    private String Pesel;

    public LekarzResponse() {
    }

    public LekarzResponse(
            String imie,
            String nazwisko,
            Integer nrPZW,
            String specjalizacja,
            String email,
            String adres,
            Integer numerTelefonu
    ) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrPZW = nrPZW;
        this.specjalizacja = specjalizacja;
        this.email = email;
        this.adres = adres;
        this.numerTelefonu = numerTelefonu;
    }

    public LekarzResponse (
            Integer nrPzw,
            String email,
            Integer telefon,
            String opis,
            String PESEL
    ) {
        this.nrPZW = nrPzw;
        this.email = email;
        this.adres = opis;
        this.numerTelefonu = telefon;
        this.Pesel = PESEL;
    }

    public LekarzResponse(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }
}
