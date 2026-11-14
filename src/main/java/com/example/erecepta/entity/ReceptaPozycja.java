package com.example.erecepta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "recepta_pozycja")
public class ReceptaPozycja {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "IDPozycji")
    private Integer idPozycji;

    @Column (name = "IDRecepty")
    private Integer idRecepty;

    @Column (name = "IDLeku")
    private Integer idLeku;

    @Column (name = "LiczbaOpakowan")
    private Integer liczbaOpakowan;

    @Column (name = "Dawkowanie")
    private Integer dawkowanie;

    @Column (name = "czyRefundowany")
    private Integer refundacja;
}
