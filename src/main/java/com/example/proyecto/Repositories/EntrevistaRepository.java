package com.example.proyecto.Repositories;

import com.example.proyecto.Entities.Entrevista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EntrevistaRepository extends JpaRepository<Entrevista,Long> {
    List<Entrevista> findByRecomendacionIdRecomendacion(Long idUsuario);
}
