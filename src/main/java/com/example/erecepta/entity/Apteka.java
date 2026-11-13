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
    Integer idApteki;

    @Column (name = "Adres")
    String adres;

    @Column (name = "NrKontaktu")
    String numerTelefonu;
}

