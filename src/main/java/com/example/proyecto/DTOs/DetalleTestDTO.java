package com.example.proyecto.DTOs;

import lombok.Data;

@Data
public class DetalleTestDTO {
    private Long idDetalleTest;
    private Long idTest;
    private String pregunta;
    private String respuesta;
    private String observacion;

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

    public Long getIdDetalleEvento() {
        return idDetalleEvento;
    }

    public void setIdDetalleEvento(Long idDetalleEvento) {
        this.idDetalleEvento = idDetalleEvento;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
}

}
