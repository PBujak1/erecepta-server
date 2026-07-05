package com.example.erecepta.dto;

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

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Integer getWiek() {
        return wiek;
    }

    public String getPlec() {
        return plec;
    }

    public Integer getNumerTelefonu() {
        return numerTelefonu;
    }

    public String getEmail() {
        return email;
    }

    public String getAdres() {
        return adres;
    }

    public String getPesel() {
        return pesel;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setWiek(Integer wiek) {
        this.wiek = wiek;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public void setNumerTelefonu(Integer numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}