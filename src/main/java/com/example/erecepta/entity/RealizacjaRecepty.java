package com.example.erecepta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table (name = "realizacja_recepty")
public class RealizacjaRecepty {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "IDRealizacji")
    private Integer idRealizacji;

    @Column (name = "IDRecepty")
    private Integer idRecepty;

    @Column (name = "IDFarmaceuty")
    private Integer idFarmaceuty;

    @Column (name = "IDApteki")
    private Integer idApteki;

    @Column (name = "dataRealizacji")
    private LocalDate dataRealizacji;
}
