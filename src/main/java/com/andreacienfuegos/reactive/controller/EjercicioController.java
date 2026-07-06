package com.andreacienfuegos.reactive.controller;

import com.andreacienfuegos.reactive.dto.EjercicioRegistroDTO;
import com.andreacienfuegos.reactive.dto.EjercicioResponseDTO;
import com.andreacienfuegos.reactive.service.EjercicioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ejercicios")
public class EjercicioController {

    private final EjercicioService ejercicioService;

    public EjercicioController(EjercicioService ejercicioService) {
        this.ejercicioService = ejercicioService;
    }

    @GetMapping
    public List<EjercicioResponseDTO> obtenerTodos() {
        return ejercicioService.obtenerTodos();
    }

    @PostMapping
    public EjercicioResponseDTO guardar(
            @Valid @RequestBody EjercicioRegistroDTO dto) {

        return ejercicioService.guardar(dto);
    }
}