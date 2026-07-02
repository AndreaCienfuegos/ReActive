package com.andreacienfuegos.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String inicio() {
        return "¡Backend de ReActive funcionando correctamente!";
    }

}