package com.andreacienfuegos.reactive.service;

import com.andreacienfuegos.reactive.dto.EjercicioResumenDTO;
import com.andreacienfuegos.reactive.dto.RutinaRegistroDTO;
import com.andreacienfuegos.reactive.dto.RutinaResponseDTO;
import com.andreacienfuegos.reactive.entity.Ejercicio;
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
    private final EjercicioService ejercicioService;

    public RutinaService(RutinaRepository rutinaRepository,
                         UsuarioService usuarioService,
                         EjercicioService ejercicioService) {

        this.rutinaRepository = rutinaRepository;
        this.usuarioService = usuarioService;
        this.ejercicioService = ejercicioService;
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

        return rutinaRepository.findByUsuario(usuario)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public Rutina buscarEntidadPorId(Long id) {

        return rutinaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rutina no encontrada"));
    }

    public RutinaResponseDTO agregarEjercicio(Long idRutina, Long idEjercicio) {

        Rutina rutina = buscarEntidadPorId(idRutina);

        Ejercicio ejercicio = ejercicioService.buscarEntidadPorId(idEjercicio);

        rutina.getEjercicios().add(ejercicio);

        Rutina guardada = rutinaRepository.save(rutina);

        return convertirADTO(guardada);
    }

    public RutinaResponseDTO eliminarEjercicio(Long idRutina, Long idEjercicio) {

        Rutina rutina = buscarEntidadPorId(idRutina);

        rutina.getEjercicios()
                .removeIf(e -> e.getIdEjercicio().equals(idEjercicio));

        Rutina guardada = rutinaRepository.save(rutina);

        return convertirADTO(guardada);
    }

    private RutinaResponseDTO convertirADTO(Rutina rutina) {

        RutinaResponseDTO dto = new RutinaResponseDTO();

        dto.setIdRutina(rutina.getIdRutina());
        dto.setNombre(rutina.getNombre());
        dto.setDescripcion(rutina.getDescripcion());

        List<EjercicioResumenDTO> ejercicios = rutina.getEjercicios()
                .stream()
                .map(e -> {
                    EjercicioResumenDTO ejercicioDTO = new EjercicioResumenDTO();
                    ejercicioDTO.setIdEjercicio(e.getIdEjercicio());
                    ejercicioDTO.setNombre(e.getNombre());
                    return ejercicioDTO;
                })
                .toList();

        dto.setEjercicios(ejercicios);

        return dto;
    }
    public RutinaResponseDTO obtenerPorId(Long id) {

        Rutina rutina = buscarEntidadPorId(id);

        return convertirADTO(rutina);
    }

    public void eliminarRutina(Long id) {

        Rutina rutina = buscarEntidadPorId(id);

        rutinaRepository.delete(rutina);
    }

    public RutinaResponseDTO actualizarRutina(
            Long id,
            RutinaRegistroDTO dto) {

        Rutina rutina = buscarEntidadPorId(id);

        rutina.setNombre(dto.getNombre());
        rutina.setDescripcion(dto.getDescripcion());

        Rutina guardada = rutinaRepository.save(rutina);

        return convertirADTO(guardada);
    }
}