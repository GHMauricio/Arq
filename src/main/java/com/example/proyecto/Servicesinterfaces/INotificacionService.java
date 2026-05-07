package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.NotificacionDTO;
import com.example.proyecto.Entities.Notificacion;

import java.util.List;

public interface INotificacionService {

    Notificacion guardar(Notificacion notificacion);

    List<NotificacionDTO> listarPorUsuario(Long idUsuario);

    void marcarComoLeido(Long idUsuario);

    void eliminar(Long id);
}
