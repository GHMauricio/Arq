package com.example.proyecto.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventosDTO {
    private Long idEvento;
    private Long idUsuario; // Para la relación ManyToOne
    private String tituloEvento;
    private String descripcionEvento;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String tipoEvento;

    public EventosDTO(Long idEvento, String tituloEvento, LocalDateTime fechaInicio, LocalDateTime fechaFin, String descripcionEvento, Long idUsuario) {
        this.idEvento = idEvento;
        this.tituloEvento = tituloEvento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcionEvento = descripcionEvento;
        this.idUsuario = idUsuario;
    }

    public EventosDTO() {

    }
}
