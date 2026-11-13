package com.example.erecepta.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@Table (name = "licencja")
public class Licencja {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "NrPZW")
    private Integer nrPZW;

    @Column (name = "dataZdobycia")
    private LocalDate dataZdobycia;
}
