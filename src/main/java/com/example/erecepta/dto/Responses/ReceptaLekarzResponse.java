package com.example.erecepta.dto.Responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceptaLekarzResponse {
    private String idRecepty;
    private String nazwaLeku;
    private String idPacjenta;
    private String liczbaOpakowan;
    private String odplatnosc;
    private String dawkowanie;
    private String dataWystawienia;

    public ReceptaLekarzResponse() {
    }

    public ReceptaLekarzResponse(
            String idRecepty,
            String nazwaLeku,
            String idPacjenta,
            String liczbaOpakowan,
            String odplatnosc,
            String dawkowanie,
            String dataWystawienia
    ) {
        this.idRecepty = idRecepty;
        this.nazwaLeku = nazwaLeku;
        this.idPacjenta = idPacjenta;
        this.liczbaOpakowan = liczbaOpakowan;
        this.odplatnosc = odplatnosc;
        this.dawkowanie = dawkowanie;
        this.dataWystawienia = dataWystawienia;
    }
}
