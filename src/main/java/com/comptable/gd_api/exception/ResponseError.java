package com.comptable.gd_api.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError {

    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime errorDate;
    private int statusCode;




}
