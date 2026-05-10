package com.example.proyecto.Repositories;

import com.example.proyecto.Entities.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecomendacionRepository extends JpaRepository<Recomendacion, Long> {

    List<Recomendacion> findByUsuarioIdUsuario(Long idUsuario);

    @Query("SELECT r FROM Recomendacion r ORDER BY r.fechaEnvio DESC")
    List<Recomendacion> listarPorFechaDescendente();
}
