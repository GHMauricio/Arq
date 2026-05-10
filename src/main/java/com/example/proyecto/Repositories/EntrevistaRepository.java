package com.example.proyecto.Repositories;

import com.example.proyecto.Entities.Entrevista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrevistaRepository extends JpaRepository<Entrevista, Long> {

    List<Entrevista> findByRecomendacionIdRecomendacion(Long idRecomendacion);

    @Query("SELECT e FROM Entrevista e WHERE e.temaEntrevista = :temaEntrevista")
    List<Entrevista> listarPorTema(@Param("temaEntrevista") String temaEntrevista);
}
