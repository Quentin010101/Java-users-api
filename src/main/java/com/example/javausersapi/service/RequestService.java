package com.example.javausersapi.service;

import com.example.javausersapi.model.Request;
import com.example.javausersapi.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public void addRequest(Request request){
        this.requestRepository.save(request);
    }
}
