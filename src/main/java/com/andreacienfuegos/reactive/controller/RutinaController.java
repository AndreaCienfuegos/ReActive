package com.andreacienfuegos.reactive.controller;

import com.andreacienfuegos.reactive.dto.RutinaRegistroDTO;
import com.andreacienfuegos.reactive.dto.RutinaResponseDTO;
import com.andreacienfuegos.reactive.service.RutinaService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutinas")
public class RutinaController {

    private final RutinaService rutinaService;

    public RutinaController(RutinaService rutinaService) {
        this.rutinaService = rutinaService;
    }

    @PostMapping
    public RutinaResponseDTO crearRutina(
            @Valid @RequestBody RutinaRegistroDTO dto,
            Authentication authentication) {

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        return rutinaService.crearRutina(
                dto,
                userDetails.getUsername()
        );
    }

    @GetMapping
    public List<RutinaResponseDTO> obtenerMisRutinas(
            Authentication authentication) {

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        return rutinaService.obtenerMisRutinas(
                userDetails.getUsername()
        );
    }
}