package com.eduardocaio.movie_library_backend.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.eduardocaio.movie_library_backend.dto.LoginRequest;
import com.eduardocaio.movie_library_backend.dto.LoginResponse;
import com.eduardocaio.movie_library_backend.entities.UserEntity;
import com.eduardocaio.movie_library_backend.enums.StatusUser;
import com.eduardocaio.movie_library_backend.exceptions.LoginException;

@Service
public class TokenService {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginResponse login(LoginRequest login){
        UserEntity user = userService.findByUsername(login.username());

        if(user.getStatus() == StatusUser.INATIVO || user.getStatus() == StatusUser.PENDENTE){
            throw new LoginException("Confirmação de cadastro pendente ou inativo. Favor verificar seu e-mail ou solicitar um novo código");
        }

        if(user == null || !user.isLoginCorrect(login, bCryptPasswordEncoder )){
            throw new LoginException("Usuário ou senha inválidos!");
        }
        

        Instant now = Instant.now();
        Long expiresIn = 3600L;

        JwtClaimsSet claims = JwtClaimsSet.builder().issuer("movie-library-eduardocaio").subject(user.getUsername().toString()).expiresAt(now.plusSeconds(expiresIn)).issuedAt(now).build();

        String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginResponse(jwtValue, expiresIn);
    }

}
