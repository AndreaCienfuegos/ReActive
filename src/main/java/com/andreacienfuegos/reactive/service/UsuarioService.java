package com.andreacienfuegos.reactive.service;

import com.andreacienfuegos.reactive.dto.UsuarioRegistroDTO;
import com.andreacienfuegos.reactive.entity.Rol;
import com.andreacienfuegos.reactive.entity.Usuario;
import com.andreacienfuegos.reactive.exception.EmailDuplicadoException;
import com.andreacienfuegos.reactive.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario registrarUsuario(UsuarioRegistroDTO dto) {

        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new EmailDuplicadoException("Ya existe un usuario con ese correo electrónico.");
        }

        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombre());
        usuario.setApellidos(dto.getApellidos());
        usuario.setEmail(dto.getEmail());

        // 🔒 Ciframos la contraseña
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        usuario.setSexo(dto.getSexo());
        usuario.setAlturaCm(dto.getAlturaCm());
        usuario.setPesoKg(dto.getPesoKg());

        usuario.setFechaRegistro(LocalDate.now());
        usuario.setRol(Rol.USUARIO);

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

}