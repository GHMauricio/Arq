package com.example.proyecto.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "ProgresoEmocional")
@Data
public class ProgresoEmocional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProgreso;

    private LocalDate fechaProgreso;
    private String estadoEmocional;
    private String comentariosProgreso;
    private Integer puntajeEmocional;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Long getIdProgreso() {
        return idProgreso;
    }

    public void setIdProgreso(Long idProgreso) {
        this.idProgreso = idProgreso;
    }

    public LocalDate getFechaProgreso() {
        return fechaProgreso;
    }

    public void setFechaProgreso(LocalDate fechaProgreso) {
        this.fechaProgreso = fechaProgreso;
    }

    public String getEstadoEmocional() {
        return estadoEmocional;
    }

    public void setEstadoEmocional(String estadoEmocional) {
        this.estadoEmocional = estadoEmocional;
    }

    public String getComentariosProgreso() {
        return comentariosProgreso;
    }

    public void setComentariosProgreso(String comentariosProgreso) {
        this.comentariosProgreso = comentariosProgreso;
    }

    public Integer getPuntajeEmocional() {
        return puntajeEmocional;
    }

    public void setPuntajeEmocional(Integer puntajeEmocional) {
        this.puntajeEmocional = puntajeEmocional;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
