package com.example.proyecto.Repositories;

import com.example.proyecto.Entities.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecomendacionRepository extends JpaRepository<Recomendacion,Long> {
    List<Recomendacion> findByUsuarioIdUsuario(Long idUsuario);
}
