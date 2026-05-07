package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.TestDTO;
import com.example.proyecto.Entities.Test;

import java.util.List;

public interface ITestService {
    public void insertar(Test test);
    public List<TestDTO> listar();
    public List<TestDTO> listarPorUsuario(Long idUsuario);
    public void eliminar(Long id);
}
