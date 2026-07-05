package com.example.erecepta.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lekarz")
public class Lekarz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDlekarza")
    private Integer idLekarza;

    @Column(name = "Imie")
    private String imie;

    @Column(name = "Nazwisko")
    private String nazwisko;

    @Column(name = "NrPZW")
    private String nrPZW;

    @Column(name = "Pesel")
    private Integer pesel;

    @Column(name = "adres")
    private String adres;

    @Column(name = "NumerTelefonu")
    private String numerTelefonu;

    @Column(name = "Email")
    private String email;

    @Column(name = "wiek")
    private Integer wiek;

    @Column(name = "Plec")
    private String plec;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "IDSpecjalizacji")
    private Integer idSpecjalizacji;

    @Column(name = "opis")
    private String opis;

    public Integer getIdLekarza() {
        return idLekarza;
    }

    public void setIdLekarza(Integer idLekarza) {
        this.idLekarza = idLekarza;
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

    public String getNrPZW() {
        return nrPZW;
    }

    public void setNrPZW(String nrPZW) {
        this.nrPZW = nrPZW;
    }

    public Integer getPesel() {
        return pesel;
    }

    public void setPesel(Integer pesel) {
        this.pesel = pesel;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getWiek() {
        return wiek;
    }

    public void setWiek(Integer wiek) {
        this.wiek = wiek;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getIdSpecjalizacji() {
        return idSpecjalizacji;
    }

    public void setIdSpecjalizacji(Integer idSpecjalizacji) {
        this.idSpecjalizacji = idSpecjalizacji;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
