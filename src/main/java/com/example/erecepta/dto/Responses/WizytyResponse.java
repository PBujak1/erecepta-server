package com.example.erecepta.dto.Responses;

public class WizytyResponse {
    private String dataWizyty;
    private String nazwaLekarza;
    private String nrRecepty;

    public WizytyResponse() {
    }

    public WizytyResponse(
            String dataWizyty,
            String nazwaLekarza,
            String nrRecepty
    ) {
        this.dataWizyty = dataWizyty;
        this.nazwaLekarza = nazwaLekarza;
        this.nrRecepty = nrRecepty;
    }

    public String getDataWizyty() {
        return dataWizyty;
    }

    public void setDataWizyty(String dataWizyty) {
        this.dataWizyty = dataWizyty;
    }

    public String getNazwaLekarza() {
        return nazwaLekarza;
    }

    public void setNazwaLekarza(String nazwaLekarza) {
        this.nazwaLekarza = nazwaLekarza;
    }

    public String getNrRecepty() {
        return nrRecepty;
    }

    public void setNrRecepty(String nrRecepty) {
        this.nrRecepty = nrRecepty;
    }
}
