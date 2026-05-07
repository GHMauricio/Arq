package com.example.proyecto.Repositories;

import com.example.proyecto.DTOs.EventosDTO;
import com.example.proyecto.Entities.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Eventos, Long> {
    List<Eventos> findByUsuarioIdUsuario(Long idUsuario);
}
