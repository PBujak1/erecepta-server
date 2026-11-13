package com.example.erecepta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "apteka")
public class Apteka {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    @Column (name = "IDApteki")
    private Integer idApteki;

    @Column (name = "Adres")
    private String adres;

    @Column (name = "NrKontaktu")
    private String numerTelefonu;
}

