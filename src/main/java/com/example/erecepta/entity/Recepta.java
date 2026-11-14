package com.example.erecepta.entity;

import com.example.erecepta.entity.enums.StatusRecepty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table (name = "recepta")
public class Recepta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDRecepty")
    private Integer IDRecepty;

    @Column(name = "IDWizyty")
    private Integer IDWizyty;

    @Column (name = "DataWystawienia")
    private LocalDate DataWystawienia;

    @Column(name = "DataWaznosci")
    private LocalDate DataWaznosci;

    @Column(name = "KodDostepu")
    private String KodDostepu;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private StatusRecepty Status;
}
