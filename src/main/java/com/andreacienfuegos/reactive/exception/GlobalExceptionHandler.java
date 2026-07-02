package com.andreacienfuegos.reactive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailDuplicadoException.class)
    public ResponseEntity<Map<String, String>> manejarEmailDuplicado(EmailDuplicadoException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("mensaje", ex.getMessage()));
    }

}