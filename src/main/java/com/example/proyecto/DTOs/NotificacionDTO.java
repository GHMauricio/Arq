package com.example.proyecto.DTOs;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class NotificacionDTO {
    private Long idNotificacion;
    private String mensajeNotificacion;
    private LocalDate fechaEnvio;
    private boolean leido;
    private Long idUsuario;
}
