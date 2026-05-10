package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.TestDTO;
import com.example.proyecto.Entities.Test;

import java.util.List;

public interface ITestService {

    void insertar(Test test);
    List<TestDTO> listar();
    List<TestDTO> listarPorUsuario(Long idUsuario);
    List<TestDTO> listarPorPuntajeAscendente();
    void eliminar(Long id);
}
