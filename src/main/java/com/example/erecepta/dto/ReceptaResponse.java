package com.example.erecepta.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReceptaResponse {

    private String nazwaLeku;
    private Integer liczbaOpakowan;
    private Integer dawkowanie;

    public ReceptaResponse() {
    }

    public ReceptaResponse(String nazwaLeku, Integer liczbaOpakowan, Integer dawkowanie) {
        this.nazwaLeku = nazwaLeku;
        this.liczbaOpakowan = liczbaOpakowan;
        this.dawkowanie = dawkowanie;
    }
}
