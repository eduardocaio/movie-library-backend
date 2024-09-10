package com.eduardocaio.movie_library_backend.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.eduardocaio.movie_library_backend.exceptions.LoginException;
import com.eduardocaio.movie_library_backend.exceptions.SignupException;
import com.eduardocaio.movie_library_backend.exceptions.VerificationException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(SignupException.class)
    private ResponseEntity<StandardError> signupExceptionHandler(SignupException exception, HttpServletRequest request){
        StandardError error = new StandardError();
        error.setError("Username or email already registered");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setTimestamp(Instant.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(LoginException.class)
    private ResponseEntity<StandardError> loginExceptionHandler(LoginException exception, HttpServletRequest request){
        StandardError error = new StandardError();
        error.setError("Username or password invalid");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setTimestamp(Instant.now());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(VerificationException.class)
    private ResponseEntity<StandardError> verificationExceptionHandler(VerificationException exception, HttpServletRequest request){
        StandardError error = new StandardError();
        error.setError("Expired or invalid code");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setTimestamp(Instant.now());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }


}
