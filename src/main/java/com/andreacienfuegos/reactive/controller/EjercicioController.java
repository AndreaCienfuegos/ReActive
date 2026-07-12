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

    @GetMapping("/{id}")
    public EjercicioResponseDTO obtenerPorId(
            @PathVariable Long id) {

        return ejercicioService.obtenerPorId(id);
    }

    @PostMapping
    public EjercicioResponseDTO guardar(
            @Valid @RequestBody EjercicioRegistroDTO dto) {

        return ejercicioService.guardar(dto);
    }

    @PutMapping("/{id}")
    public EjercicioResponseDTO actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EjercicioRegistroDTO dto) {

        return ejercicioService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(
            @PathVariable Long id) {

        ejercicioService.eliminar(id);
    }
}