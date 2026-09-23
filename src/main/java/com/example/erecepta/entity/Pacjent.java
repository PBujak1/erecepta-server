package com.example.erecepta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    @Column(name = "NumerTelefonu")
    private Integer numerTelefonu;

    @Column(name = "Email")
    private String email;

    @Column(name = "Adres")
    private String adres;

    @Column(name = "Pesel")
    private String pesel;

    public Pacjent() {
    }
}