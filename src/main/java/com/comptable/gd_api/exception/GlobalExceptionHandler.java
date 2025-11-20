package com.comptable.gd_api.exception;


import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseError> handleUsernameNotFoundException(UsernameNotFoundException exception){
        ResponseError error = ResponseError.builder()
                .message(exception.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .errorDate(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseError> handleAuthenticationException(org.springframework.security.core.AuthenticationException exception){
        ResponseError error = ResponseError.builder()
                .message("Invalid credentials")
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .errorDate(LocalDateTime.now())
                .statusCode(401)
                .build();
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }


}
