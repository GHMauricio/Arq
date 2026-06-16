package com.example.proyecto.Repositories;

import com.example.proyecto.Entities.DetallesTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleTestRepository extends JpaRepository<DetallesTest, Long> {

    List<DetallesTest> findByTestIdTest(Long idTest);

    @Query("SELECT d FROM DetallesTest d WHERE d.respuesta = :respuesta")
    List<DetallesTest> listarPorRespuesta(@Param("respuesta") String respuesta);

    void deleteByTestIdTest(Long idTest);
}
