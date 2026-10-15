package com.example.erecepta.dto.Responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecjalizacjaResponse {
    private String specjalizacja;

    public SpecjalizacjaResponse(String Specjalizacja) {
        this.specjalizacja = Specjalizacja;
    }

    public SpecjalizacjaResponse() {
    }
}
