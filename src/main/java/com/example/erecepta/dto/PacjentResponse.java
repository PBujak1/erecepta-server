package com.example.erecepta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacjentResponse {

    private String imie;
    private String nazwisko;
    private Integer wiek;
    private String plec;
    private Integer numerTelefonu;
    private String email;
    private String adres;
    private String pesel;

    public PacjentResponse() {
    }

    public PacjentResponse(String imie,
                           String nazwisko,
                           Integer wiek,
                           String plec,
                           Integer numerTelefonu,
                           String email,
                           String adres,
                           String pesel) {

        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
        this.plec = plec;
        this.numerTelefonu = numerTelefonu;
        this.email = email;
        this.adres = adres;
        this.pesel = pesel;
    }

    public PacjentResponse(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }
}