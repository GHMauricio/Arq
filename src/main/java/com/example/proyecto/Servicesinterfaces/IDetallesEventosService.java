package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.DetallesEventoDTO;
import com.example.proyecto.Entities.DetallesEvento;

import java.util.List;

public interface IDetallesEventosService {
    public void insertar(DetallesEvento detalle);
    public List<DetallesEventoDTO> listarPorEvento(Long idEvento);
    public void eliminar(Long id);
    List<DetallesEventoDTO> listar();
}
