package com.example.erecepta.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pacjent")
public class Pacjent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPacjenta")
    private Integer idPacjenta;

    @Column(name = "Imie")
    private String imie;

    @Column(name = "Nazwisko")
    private String nazwisko;

    @Column(name = "Wiek")
    private Integer wiek;

    @Column(name = "Plec")
    private String plec;

    @Column(name = "numerTelefonu")
    private Integer numerTelefonu;

    @Column(name = "Email")
    private String email;

    @Column(name = "Adres")
    private String adres;

    @Column(name = "Pesel")
    private String pesel;

    public Pacjent() {
    }

    public Integer getIdPacjenta() {
        return idPacjenta;
    }

    public void setIdPacjenta(Integer idPacjenta) {
        this.idPacjenta = idPacjenta;
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

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}