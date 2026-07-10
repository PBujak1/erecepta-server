package com.example.erecepta.dto;

public class LekarzResponse {
    private String imie;
    private String nazwisko;
    private Integer nrPZW;
    private String specjalizacja;
    private String email;
    private String adres;
    private Integer numerTelefonu;

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

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Integer getNrPZW() {
        return nrPZW;
    }

    public void setNrPZW(Integer nrPZW) {
        this.nrPZW = nrPZW;
    }

    public String getSpecjalizacja() {
        return specjalizacja;
    }

    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Integer getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(Integer numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }
}
