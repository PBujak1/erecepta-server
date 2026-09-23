package com.example.erecepta.dto;

import java.time.LocalDate;

public class WizytaResponse {

    private LocalDate dataWizyty;

    public WizytaResponse() {
    }

    public WizytaResponse(LocalDate dataWizyty) {
        this.dataWizyty = dataWizyty;
    }

    public LocalDate getDataWizyty() {
        return dataWizyty;
    }

    public void setDataWizyty(LocalDate dataWizyty) {
        this.dataWizyty = dataWizyty;
    }
}
