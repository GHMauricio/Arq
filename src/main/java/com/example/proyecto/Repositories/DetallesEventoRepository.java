package com.example.proyecto.Repositories;

import com.example.proyecto.Entities.DetallesEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallesEventoRepository extends JpaRepository<DetallesEvento, Long> {
    List<DetallesEvento> findByEventoIdEvento(Long idEvento);
}
