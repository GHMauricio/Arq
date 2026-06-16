package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.DetalleTestDTO;

import java.util.List;

public interface IDetalleTestService {

    void insertar(DetalleTestDTO dto);
    void update(DetalleTestDTO dto);
    List<DetalleTestDTO> listar();
    List<DetalleTestDTO> listarPorTest(Long idTest);
    List<DetalleTestDTO> listarPorRespuesta(String respuesta);
    void eliminar(Long id);
}
