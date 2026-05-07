package com.example.proyecto.Repositories;

import com.example.proyecto.Entities.DetallesTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleTestRepository extends JpaRepository<DetallesTest, Long> {
    List<DetallesTest> findByTestIdTest(Long idTest);
}
