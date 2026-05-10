package com.example.proyecto.Repositories;

import com.example.proyecto.Entities.DetallesEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallesEventoRepository extends JpaRepository<DetallesEvento, Long> {

    List<DetallesEvento> findByEventoIdEvento(Long idEvento);

    @Query("SELECT d FROM DetallesEvento d WHERE d.actividad = :actividad")
    List<DetallesEvento> listarPorActividad(@Param("actividad") String actividad);
}
