package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.EventosDTO;
import java.util.List;

public interface IEventoService {
    void guardar(EventosDTO dto);
    List<EventosDTO> listarEventosDTO();
    List<EventosDTO> listarPorUsuario(Long idUsuario);
    List<EventosDTO> listarTodo();
    void eliminar(Long id);
}
