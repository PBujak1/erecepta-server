package com.example.erecepta.dto.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LekarzDaneRequest {
    private String pesel;
    private String imie;
    private String nazwisko;
    private String adres;
    private String telefon;
    private String email;
    private String wiek;
    private String plec;

    public LekarzDaneRequest() {
    }
}
