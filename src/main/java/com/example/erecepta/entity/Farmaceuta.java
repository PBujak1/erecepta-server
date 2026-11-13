package com.example.erecepta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "farmaceuta")
public class Farmaceuta {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "IDFarmaceuty")
    private Integer idFarmaceuty;

    @Column (name = "IDApteki")
    private Integer idApteki;

    @Column (name = "Imie")
    private String imie;

    @Column (name = "Nazwisko")
    private String nazwisko;

    @Column (name = "NrPZW")
    private Integer nrPZW;
}
