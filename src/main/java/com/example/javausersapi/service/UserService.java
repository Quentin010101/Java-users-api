package com.example.javausersapi.service;

import com.example.javausersapi.Dto.ApiKeyResponse;
import com.example.javausersapi.Dto.UserResponse;
import com.example.javausersapi.model.User;
import com.example.javausersapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ApiKeyResponse generateApikey(String username){

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        UUID apiKey = UUID.randomUUID();
        user.setApikey(String.valueOf(apiKey));
        userRepository.save(user);
        ApiKeyResponse apikey = new ApiKeyResponse(user.getApikey());
        return apikey;
    }

    public UserResponse getUserByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getApikey(), user.getRequests());
    }
}
