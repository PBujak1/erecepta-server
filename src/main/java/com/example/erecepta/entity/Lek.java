package com.example.erecepta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "lek")
public class Lek {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "IDLeku")
    private Integer IDLeku;

    @Column(name = "IDRodzaju")
    private Integer IDRodzaju;

    @Column(name = "Nazwa")
    private String Nazwa;

}
