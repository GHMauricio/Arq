package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.TestDTO;
import java.util.List;

public interface ITestService {
    void insertar(TestDTO dto);
    void update(TestDTO dto);
    List<TestDTO> listar();
    List<TestDTO> listarPorUsuario(Long idUsuario);
    List<TestDTO> listarPorPuntajeAscendente();
    void eliminar(Long id);
}
