package com.eduardocaio.movie_library_backend.controllers;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardocaio.movie_library_backend.dto.LoginRequest;
import com.eduardocaio.movie_library_backend.dto.LoginResponse;
import com.eduardocaio.movie_library_backend.entities.UserEntity;
import com.eduardocaio.movie_library_backend.services.UserService;

@RestController
@CrossOrigin
@RequestMapping(value = "/auth")
public class TokenController {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest login){
        UserEntity user = userService.findByUsername(login.username());
        if(user == null || !user.isLoginCorrect(login, bCryptPasswordEncoder)){
            throw new BadCredentialsException("user or password is invalid");
        }

        Instant now = Instant.now();
        Long expiresIn = 300L;

        JwtClaimsSet claims = JwtClaimsSet.builder().issuer("movie-library-eduardocaio").subject(user.getId().toString()).expiresAt(now.plusSeconds(expiresIn)).issuedAt(now).build();

        String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }
}
