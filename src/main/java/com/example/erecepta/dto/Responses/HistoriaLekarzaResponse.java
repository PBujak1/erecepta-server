package com.example.erecepta.dto.Responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoriaLekarzaResponse {
    private String idWizyty;
    private String dataWizyty;
    private String nazwaLekarza;
    private String idLekarza;
    private String nazwaPacjenta;
    private String idPacjenta;
    private String nrRecepty;

    public HistoriaLekarzaResponse() {
    }

    public HistoriaLekarzaResponse(
            String idWizyty,
            String dataWizyty,
            String nazwaLekarza,
            String idLekarza,
            String nazwaPacjenta,
            String idPacjenta,
            String nrRecepty
    ) {
        this.idWizyty = idWizyty;
        this.dataWizyty = dataWizyty;
        this.nazwaLekarza = nazwaLekarza;
        this.idLekarza = idLekarza;
        this.nazwaPacjenta = nazwaPacjenta;
        this.idPacjenta = idPacjenta;
        this.nrRecepty = nrRecepty;
    }
}
