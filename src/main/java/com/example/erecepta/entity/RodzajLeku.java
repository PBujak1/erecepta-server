package com.example.erecepta.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "rodzajleku")
public class RodzajLeku {
    @Id
    @Column (name = "IDRodzaju")
    private Integer idRodzaju;

    @Column (name = "Dawkowanie")
    private Integer dawkowanie;

    @Column (name = "Nazwa")
    private String nazwa;
}
