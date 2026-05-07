package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.DetalleTestDTO;
import com.example.proyecto.Entities.DetallesTest;

import java.util.List;

public interface IDetalleTestService {
    public void insertar(DetallesTest detalle);
    public List<DetalleTestDTO> listarPorTest(Long idTest);
    public void eliminar(Long id);
    List<DetalleTestDTO> listar();
}
