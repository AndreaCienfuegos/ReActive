package com.andreacienfuegos.reactive.service;

import com.andreacienfuegos.reactive.dto.UsuarioRegistroDTO;
import com.andreacienfuegos.reactive.entity.Rol;
import com.andreacienfuegos.reactive.entity.Usuario;
import com.andreacienfuegos.reactive.exception.EmailDuplicadoException;
import com.andreacienfuegos.reactive.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.andreacienfuegos.reactive.dto.UsuarioResponseDTO;
import java.util.stream.Collectors;

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

    public List<UsuarioResponseDTO> obtenerTodos() {

        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public UsuarioResponseDTO registrarUsuario(UsuarioRegistroDTO dto) {

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

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return convertirADTO(usuarioGuardado);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
    private UsuarioResponseDTO convertirADTO(Usuario usuario) {

        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(usuario.getNombre());
        dto.setApellidos(usuario.getApellidos());
        dto.setEmail(usuario.getEmail());
        dto.setSexo(usuario.getSexo());
        dto.setAlturaCm(usuario.getAlturaCm());
        dto.setPesoKg(usuario.getPesoKg());
        dto.setRol(usuario.getRol());

        return dto;
    }

    public UsuarioResponseDTO obtenerPerfil(String email) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return convertirADTO(usuario);
    }
    public Usuario buscarPorEmail(String email) {

        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}