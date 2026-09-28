package com.example.erecepta.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HistoriaPacjentaResponse {
    private LocalDate dataWizyty;
    private String nazwaLekarza;
    private String nazwaPacjenta;
    private Integer nrRecepty;

    public HistoriaPacjentaResponse() {
    }

    public HistoriaPacjentaResponse(LocalDate dataWizyty) {
        this.dataWizyty = dataWizyty;
    }

    public HistoriaPacjentaResponse(
            LocalDate dataWizyty,
            String nazwaLekarza,
            String nazwaPacjenta,
            Integer idRecepty)
    {
        this.dataWizyty = dataWizyty;
        this.nazwaLekarza = nazwaLekarza;
        this.nazwaPacjenta = nazwaPacjenta;
        this.nrRecepty = idRecepty;
    }
}