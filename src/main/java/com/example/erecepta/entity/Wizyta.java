package com.example.erecepta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "e_wizyta")
public class Wizyta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDWizyty")
    private Integer IDWizyty;

    @Column(name = "DataWizyty")
    private LocalDate dataWizyty;

    @Column(name = "IDLekarza")
    private Integer IDLekarza;

    @Column(name = "IDPacjenta")
    private Integer IDPacjenta;

    @Column(name = "IDRecepty")
    private Integer IDRecepty;

    public Wizyta() {
    }
}
