package com.example.erecepta.dto.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceptaPozycjaRequest {
    private String nazwaLeku;
    private Integer liczbaOpakowan;
    private Integer odplatnosc;
    private Integer dawkowanie;

    public ReceptaPozycjaRequest() {
    }

    public ReceptaPozycjaRequest(String nazwaLeku, Integer liczbaOpakowan, Integer odplatnosc, Integer dawkowanie) {
        this.nazwaLeku = nazwaLeku;
        this.liczbaOpakowan = liczbaOpakowan;
        this.odplatnosc = odplatnosc;
        this.dawkowanie = dawkowanie;
    }
}
