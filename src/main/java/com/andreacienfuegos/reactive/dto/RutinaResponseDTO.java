package com.andreacienfuegos.reactive.dto;


import java.util.List;

public class RutinaResponseDTO {

    private Long idRutina;
    private String nombre;
    private String descripcion;

    private List<EjercicioResumenDTO> ejercicios;

    public RutinaResponseDTO() {
    }

    public Long getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(Long idRutina) {
        this.idRutina = idRutina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<EjercicioResumenDTO> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<EjercicioResumenDTO> ejercicios) {
        this.ejercicios = ejercicios;
    }
}