package com.example.erecepta.dto.Responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReceptaResponse {

    private String nazwaLeku;
    private String liczbaOpakowan;
    private String dawkowanie;

    public ReceptaResponse() {
    }

    public ReceptaResponse(String nazwaLeku, String liczbaOpakowan, String dawkowanie) {
        this.nazwaLeku = nazwaLeku;
        this.liczbaOpakowan = liczbaOpakowan;
        this.dawkowanie = dawkowanie;
    }
}
