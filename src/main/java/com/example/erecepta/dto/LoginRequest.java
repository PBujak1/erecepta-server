package com.example.erecepta.dto;

public class LoginRequest {
    private String pesel;
    private String haslo;

    public LoginRequest() {

    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
}
