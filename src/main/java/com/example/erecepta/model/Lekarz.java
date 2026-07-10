package com.example.erecepta.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lekarz")
public class Lekarz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlekarza")
    private Integer idLekarza;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "nrpzw")
    private Integer nrPzw;

    @Column(name = "adres")
    private String adres;

    @Column(name = "numerTelefonu")
    private Integer numerTelefonu;

    @Column(name = "email")
    private String email;

    @Column(name = "wiek")
    private Integer wiek;

    @Column(name = "plec")
    private String plec;

    @Lob
    @Column(name = "avatar")
    private byte[] avatar;

    @Column(name = "idspecjalizacji")
    private Integer idSpecjalizacji;

    @Column(name = "opis")
    private String opis;

    @Column(name = "specjalizacja")
    private String specjalizacja;

    public Lekarz() {
    }

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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Integer getNrPzw() {
        return nrPzw;
    }

    public void setNrPzw(Integer nrPzw) {
        this.nrPzw = nrPzw;
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

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
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

    public String getSpecjalizacja() {
        return specjalizacja;
    }

    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }
}