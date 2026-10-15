package com.example.erecepta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "Specjalizacja")
public class Specjalizacja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDSpecjalizacji")
    Integer IDSpecjalizacji;

    @Column(name = "NazwaSpecjalizacji")
    String NazwaSpecjalizacji;
}
