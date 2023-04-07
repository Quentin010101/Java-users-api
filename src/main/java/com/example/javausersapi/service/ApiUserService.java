package com.example.javausersapi.service;

import com.example.javausersapi.model.api.ApiUser;
import com.example.javausersapi.repository.ApiUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiUserService {

    @Autowired
    ApiUserRepository repository;

    public ApiUser getUserInformation(Long id){
        if(id == null){
            throw new IllegalArgumentException("Value must be a long");
        }
        if(repository.existsById(id)){
            return repository.getById(id);
        }
        return null;
    }

    public List<ApiUser> getRandomUsers(Long number){
        if(number == null){
            throw new IllegalArgumentException("Value must be a long");
        }
        return repository.findRandomUsers().stream().limit(number).collect(Collectors.toList());
    }


}
