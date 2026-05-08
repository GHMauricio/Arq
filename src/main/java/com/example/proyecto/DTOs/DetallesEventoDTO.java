package com.example.proyecto.DTOs;

import lombok.Data;

import java.time.LocalTime;

@Data
public class DetallesEventoDTO {
    private Long idDetalleEvento;
    private Long idEvento;
    private String actividad;
    private String responsable;
    private LocalTime horaInicio;
    private LocalTime horaFin;


}
