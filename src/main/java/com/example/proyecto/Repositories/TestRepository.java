package com.example.proyecto.Repositories;

import com.example.proyecto.Entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository  extends JpaRepository<Test, Long> {
    List<Test> findByUsuarioIdUsuarioOrderByFechaTestDesc(Long idUsuario);
}
