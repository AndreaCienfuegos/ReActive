package com.andreacienfuegos.reactive.service;

import com.andreacienfuegos.reactive.dto.EjercicioRegistroDTO;
import com.andreacienfuegos.reactive.dto.EjercicioResponseDTO;
import com.andreacienfuegos.reactive.entity.Ejercicio;
import com.andreacienfuegos.reactive.repository.EjercicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EjercicioService {

    private final EjercicioRepository ejercicioRepository;

    public EjercicioService(EjercicioRepository ejercicioRepository) {
        this.ejercicioRepository = ejercicioRepository;
    }

    public List<EjercicioResponseDTO> obtenerTodos() {

        return ejercicioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public EjercicioResponseDTO guardar(EjercicioRegistroDTO dto) {

        Ejercicio ejercicio = new Ejercicio();

        ejercicio.setNombre(dto.getNombre());
        ejercicio.setDescripcion(dto.getDescripcion());
        ejercicio.setGrupoMuscular(dto.getGrupoMuscular());
        ejercicio.setMaterial(dto.getMaterial());
        ejercicio.setNivel(dto.getNivel());
        ejercicio.setVideoUrl(dto.getVideoUrl());
        ejercicio.setImagenUrl(dto.getImagenUrl());

        Ejercicio guardado = ejercicioRepository.save(ejercicio);

        return convertirADTO(guardado);
    }

    private EjercicioResponseDTO convertirADTO(Ejercicio ejercicio) {

        EjercicioResponseDTO dto = new EjercicioResponseDTO();

        dto.setIdEjercicio(ejercicio.getIdEjercicio());
        dto.setNombre(ejercicio.getNombre());
        dto.setDescripcion(ejercicio.getDescripcion());
        dto.setGrupoMuscular(ejercicio.getGrupoMuscular());
        dto.setMaterial(ejercicio.getMaterial());
        dto.setNivel(ejercicio.getNivel());
        dto.setVideoUrl(ejercicio.getVideoUrl());
        dto.setImagenUrl(ejercicio.getImagenUrl());

        return dto;
    }
}