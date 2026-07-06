package com.andreacienfuegos.reactive.controller;

import com.andreacienfuegos.reactive.dto.UsuarioRegistroDTO;
import com.andreacienfuegos.reactive.dto.UsuarioResponseDTO;
import com.andreacienfuegos.reactive.entity.Usuario;
import com.andreacienfuegos.reactive.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/perfil")
    public UsuarioResponseDTO obtenerPerfil(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return usuarioService.obtenerPerfil(userDetails.getUsername());
    }

    @PostMapping("/registro")
    public Usuario registrar(@Valid @RequestBody UsuarioRegistroDTO dto) {
        return usuarioService.registrarUsuario(dto);
    }

}