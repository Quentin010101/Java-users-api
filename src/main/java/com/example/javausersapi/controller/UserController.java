package com.example.javausersapi.controller;

import com.example.javausersapi.Dto.ApiKeyResponse;
import com.example.javausersapi.Dto.UserResponse;
import com.example.javausersapi.service.UserDetailsImpl;
import com.example.javausersapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public UserResponse getUser(Authentication authentication){

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        UserResponse user = userService.getUserByUsername(userPrincipal.getUsername());
        return user;
    }

    @GetMapping("/apikey")
    public ApiKeyResponse generateApikey(Authentication authentication){

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        ApiKeyResponse apikey = userService.generateApikey(userPrincipal.getUsername());
        return apikey;
    }
}
