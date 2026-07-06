package com.andreacienfuegos.reactive.dto;

import jakarta.validation.constraints.NotBlank;

public class RutinaRegistroDTO {

    @NotBlank
    private String nombre;

    private String descripcion;

    public RutinaRegistroDTO() {
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
}