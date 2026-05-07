package com.example.proyecto.Repositories;

import com.example.proyecto.Entities.Articulos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulos, Long> {
    List<Articulos> findByRecomendacionIdRecomendacion(Long idRecomendacion);
}
