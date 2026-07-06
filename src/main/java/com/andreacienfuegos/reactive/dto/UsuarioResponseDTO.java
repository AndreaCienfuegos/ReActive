package com.andreacienfuegos.reactive.dto;

import com.andreacienfuegos.reactive.entity.Rol;
import com.andreacienfuegos.reactive.entity.Sexo;

public class UsuarioResponseDTO {

    private Long idUsuario;
    private String nombre;
    private String apellidos;
    private String email;
    private Sexo sexo;
    private Integer alturaCm;
    private Double pesoKg;
    private Rol rol;

    public UsuarioResponseDTO() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Integer getAlturaCm() {
        return alturaCm;
    }

    public void setAlturaCm(Integer alturaCm) {
        this.alturaCm = alturaCm;
    }

    public Double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}