package com.andreacienfuegos.reactive.service;

import com.andreacienfuegos.reactive.dto.RutinaRegistroDTO;
import com.andreacienfuegos.reactive.dto.RutinaResponseDTO;
import com.andreacienfuegos.reactive.entity.Rutina;
import com.andreacienfuegos.reactive.entity.Usuario;
import com.andreacienfuegos.reactive.repository.RutinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RutinaService {

    private final RutinaRepository rutinaRepository;
    private final UsuarioService usuarioService;

    public RutinaService(RutinaRepository rutinaRepository,
                         UsuarioService usuarioService) {

        this.rutinaRepository = rutinaRepository;
        this.usuarioService = usuarioService;
    }

    public RutinaResponseDTO crearRutina(RutinaRegistroDTO dto, String email) {

        Usuario usuario = usuarioService.buscarPorEmail(email);

        Rutina rutina = new Rutina();

        rutina.setNombre(dto.getNombre());
        rutina.setDescripcion(dto.getDescripcion());
        rutina.setUsuario(usuario);

        Rutina guardada = rutinaRepository.save(rutina);

        return convertirADTO(guardada);
    }

    public List<RutinaResponseDTO> obtenerMisRutinas(String email) {

        Usuario usuario = usuarioService.buscarPorEmail(email);

        return rutinaRepository.findAll()
                .stream()
                .filter(r -> r.getUsuario().getIdUsuario().equals(usuario.getIdUsuario()))
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private RutinaResponseDTO convertirADTO(Rutina rutina) {

        RutinaResponseDTO dto = new RutinaResponseDTO();

        dto.setIdRutina(rutina.getIdRutina());
        dto.setNombre(rutina.getNombre());
        dto.setDescripcion(rutina.getDescripcion());

        return dto;
    }
}