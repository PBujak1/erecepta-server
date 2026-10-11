package com.example.erecepta.dto.Responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LekResponse {
    private Integer IDLeku;
    private String Nazwa;

    public LekResponse(String Nazwa) {
        this.Nazwa = Nazwa;
    }
}
