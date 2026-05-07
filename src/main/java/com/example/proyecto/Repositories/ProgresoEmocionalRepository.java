package com.example.proyecto.Repositories;

import com.example.proyecto.Entities.ProgresoEmocional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgresoEmocionalRepository extends JpaRepository<ProgresoEmocional,Long> {

    List<ProgresoEmocional> findByUsuarioIdUsuario(Long idUsuario);
}
