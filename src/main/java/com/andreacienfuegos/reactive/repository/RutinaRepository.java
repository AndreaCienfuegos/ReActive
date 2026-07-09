package com.andreacienfuegos.reactive.repository;

import com.andreacienfuegos.reactive.entity.Rutina;
import com.andreacienfuegos.reactive.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RutinaRepository extends JpaRepository<Rutina, Long> {

    List<Rutina> findByUsuario(Usuario usuario);

}