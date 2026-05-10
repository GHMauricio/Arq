package com.example.proyecto.DTOs;
import lombok.Data;
import java.time.LocalDate;

@Data
public class EventosDTO {
    private Long idEvento;
    private Long idUsuario;
    private String tituloEvento;
    private String descripcionEvento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String tipoEvento;

    public EventosDTO(Long idEvento, String tituloEvento, LocalDate fechaInicio, LocalDate fechaFin, String descripcionEvento, Long idUsuario) {
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
