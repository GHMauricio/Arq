package com.example.proyecto.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Table(name = "DetallesEvento")
@Data
public class DetallesEvento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleEvento;

    @ManyToOne
    @JoinColumn(name = "idEvento")
    private Eventos evento;

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

    public Eventos getEvento() {
        return evento;
    }

    public void setEvento(Eventos evento) {
        this.evento = evento;
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
