package com.example.javausersapi.Dto;

import com.example.javausersapi.model.Request;

import java.util.List;

public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String apikey;
    private List<Request> requests;
    public UserResponse(Long id, String username, String email, String apikey, List<Request> requests) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.apikey = apikey;
        this.requests = requests;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }
}
