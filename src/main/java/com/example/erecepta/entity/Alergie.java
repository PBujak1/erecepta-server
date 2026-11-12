package com.example.erecepta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "alergie")
public class Alergie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDAlergii")
    private Integer IDAlergii;

    @Column(name = "IDPacjenta")
    private Integer IDPacjenta;

    @Column(name = "Nazwa")
    private String Nazwa;
}
