package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.DetallesEventoDTO;

import java.util.List;

public interface IDetallesEventosService {

    void insertar(DetallesEventoDTO dto);
    void update(DetallesEventoDTO dto);
    List<DetallesEventoDTO> listar();
    List<DetallesEventoDTO> listarPorEvento(Long idEvento);
    List<DetallesEventoDTO> listarPorActividad(String actividad);
    void eliminar(Long id);
}
