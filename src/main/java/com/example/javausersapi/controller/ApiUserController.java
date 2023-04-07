package com.example.javausersapi.controller;

import com.example.javausersapi.model.api.ApiUser;
import com.example.javausersapi.service.ApiUserService;
import com.example.javausersapi.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class ApiUserController {

    @Autowired
    private ApiUserService apiUserService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // api/user/{id}/information
    @GetMapping("/{id}/information")
    public ResponseEntity<ApiUser> getUserInformation(@PathVariable Long id, @RequestParam(required = false) String apikey){
        try {
            this.checkApiKey(apikey);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(apiUserService.getUserInformation(id), HttpStatus.OK);
    }

    // api/user/random?number=
    @GetMapping("/random")
    public ResponseEntity<List<ApiUser>> getRandomUserInformation(@RequestParam(required = false) Long number, @RequestParam(required = false) String apikey){
        try {
            this.checkApiKey(apikey);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        if(number == null){
            number = (long) 1;
        }
        return new ResponseEntity<>(apiUserService.getRandomUsers(number), HttpStatus.OK);

    }

    private void checkApiKey(String apiKey) throws Exception {
        System.out.println(apiKey);
        if(!userDetailsService.apiKeyExists(apiKey) || apiKey == null){
            throw new IOException("Wrong api key");
        }
    }
}
