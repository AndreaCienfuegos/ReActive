package com.andreacienfuegos.reactive.repository;

import com.andreacienfuegos.reactive.entity.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {

}