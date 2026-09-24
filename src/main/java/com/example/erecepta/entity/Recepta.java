package com.example.erecepta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "recepta")
public class Recepta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDRecepty")
    Integer IDRecepty;

    @Column(name = "IDLeku")
    Integer IDLeku;

    @Column(name = "IDPacjenta")
    Integer IDPacjenta;

    @Column(name = "LiczbaOpakowan")
    Integer LiczbaOpakowan;

    @Column(name = "CzyRefundowany")
    Integer CzyRefundowany;

    @Column(name = "Dawkowanie")
    Integer Dawkowanie;
}
