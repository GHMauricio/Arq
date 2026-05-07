package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.EventosDTO;
import com.example.proyecto.Entities.Eventos;

import java.util.List;

public interface IEventoService {

    List<EventosDTO> listarEventosDTO();
    // Método para registrar o actualizar un evento
    Eventos guardar(Eventos evento);

    // Método para obtener todos los eventos vinculados a un usuario
    List<EventosDTO> listarPorUsuario(Long idUsuario);

    void eliminar(Long id);

    List<EventosDTO> listarTodo();
}
