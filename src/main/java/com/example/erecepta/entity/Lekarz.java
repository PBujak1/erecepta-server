package com.example.erecepta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}