package com.example.citasmedicasbd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CitaNotFoundException extends RuntimeException {
    
    public CitaNotFoundException(String message) {
        super(message);
    }


}