package com.example.proyecto.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificacionDTO {
    private Long idNotificacion;
    private String mensajeNotificacion;
    private LocalDateTime fechaEnvio;
    private boolean leido;
    private Long idUsuario;
}
