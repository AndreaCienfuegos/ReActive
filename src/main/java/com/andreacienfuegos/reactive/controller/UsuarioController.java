package com.andreacienfuegos.reactive.controller;

import com.andreacienfuegos.reactive.dto.UsuarioRegistroDTO;
import com.andreacienfuegos.reactive.dto.UsuarioResponseDTO;
import com.andreacienfuegos.reactive.entity.Usuario;
import com.andreacienfuegos.reactive.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioResponseDTO> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    @PostMapping("/registro")
    public Usuario registrar(@Valid @RequestBody UsuarioRegistroDTO dto) {
        return usuarioService.registrarUsuario(dto);
    }

}