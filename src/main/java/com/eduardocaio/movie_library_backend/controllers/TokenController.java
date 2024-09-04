package com.eduardocaio.movie_library_backend.controllers;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardocaio.movie_library_backend.dto.LoginRequest;
import com.eduardocaio.movie_library_backend.dto.LoginResponse;
import com.eduardocaio.movie_library_backend.dto.UserSignupDTO;
import com.eduardocaio.movie_library_backend.services.TokenService;
import com.eduardocaio.movie_library_backend.services.UserService;

@RestController
@CrossOrigin
@RequestMapping(value = "/auth")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;


    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest login){
        return ResponseEntity.ok(tokenService.login(login));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Void> signup(@RequestBody UserSignupDTO newUser){
        userService.signup(newUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/verify/{code}")
    public ResponseEntity<Void> verify(@PathVariable("code") UUID code){
        userService.verify(code);
        return ResponseEntity.ok().build();
    }
}
