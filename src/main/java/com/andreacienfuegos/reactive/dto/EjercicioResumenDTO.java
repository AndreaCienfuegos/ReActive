package com.andreacienfuegos.reactive.dto;

public class EjercicioResumenDTO {

    private Long idEjercicio;
    private String nombre;

    public EjercicioResumenDTO() {
    }

    public Long getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(Long idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}