package com.example.javausersapi.Dto;

public class ApiKeyResponse {
    private String apikey;

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public ApiKeyResponse(String apikey) {
        this.apikey = apikey;
    }
}
