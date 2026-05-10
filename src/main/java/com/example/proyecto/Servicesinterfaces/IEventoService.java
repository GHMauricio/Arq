package com.example.proyecto.Servicesinterfaces;
import com.example.proyecto.DTOs.EventosDTO;
import java.util.List;

public interface IEventoService {
    EventosDTO guardar(EventosDTO dto);
    EventosDTO actualizar(Long id, EventosDTO dto); // NUEVO
    List<EventosDTO> listarEventosDTO();
    List<EventosDTO> listarPorUsuario(Long idUsuario);
    List<EventosDTO> listarTodo();
    List<EventosDTO> listarPorAnioDescendente(); // NUEVO
    void eliminar(Long id);
}
