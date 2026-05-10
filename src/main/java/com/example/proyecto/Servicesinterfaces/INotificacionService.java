package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.NotificacionDTO;

import java.util.List;

public interface INotificacionService {

    void guardar(NotificacionDTO dto);
    List<NotificacionDTO> listar();
    List<NotificacionDTO> listarPorUsuario(Long idUsuario);
    List<NotificacionDTO> listarLeidasPorAnio(int anio);
    void marcarComoLeido(Long idUsuario);
    void actualizar(Long id, NotificacionDTO dto);
    void eliminar(Long id);
}
