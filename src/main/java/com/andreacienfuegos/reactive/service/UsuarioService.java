package com.andreacienfuegos.reactive.service;

import com.andreacienfuegos.reactive.dto.UsuarioRegistroDTO;
import com.andreacienfuegos.reactive.entity.Sexo;
import com.andreacienfuegos.reactive.entity.Usuario;
import com.andreacienfuegos.reactive.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario registrar(UsuarioRegistroDTO dto) {

        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombre());
        usuario.setApellidos(dto.getApellidos());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());

        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        usuario.setAlturaCm(dto.getAlturaCm());
        usuario.setPesoKg(dto.getPesoKg());

        if (dto.getSexo() != null) {
            usuario.setSexo(Sexo.valueOf(dto.getSexo().toUpperCase()));
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}