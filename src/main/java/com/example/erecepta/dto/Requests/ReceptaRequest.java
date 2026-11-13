package com.example.erecepta.dto.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceptaRequest {
    private String peselLekarza;
    private Integer liczbaOpakowan;
    private String nazwaLeku;
    private Integer odplatnosc;
    private Integer dawkowanie;

    public ReceptaRequest() {
    }

    public ReceptaRequest(String peselLekarza, Integer liczbaOpakowan, String nazwaLeku, Integer odplatnosc, Integer dawkowanie) {
        this.peselLekarza = peselLekarza;
        this.liczbaOpakowan = liczbaOpakowan;
        this.nazwaLeku = nazwaLeku;
        this.odplatnosc = odplatnosc;
        this.dawkowanie = dawkowanie;
    }
}
