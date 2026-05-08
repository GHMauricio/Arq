package com.example.proyecto.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Tests")
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTest;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    private LocalDate fechaTest;
    private String estadoEmocional;
    private String notasTest;
    private Double puntajeTest;

    public Long getIdTest() {
        return idTest;
    }

    public void setIdTest(Long idTest) {
        this.idTest = idTest;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaTest() {
        return fechaTest;
    }

    public void setFechaTest(LocalDate fechaTest) {
        this.fechaTest = fechaTest;
    }

    public String getEstadoEmocional() {
        return estadoEmocional;
    }

    public void setEstadoEmocional(String estadoEmocional) {
        this.estadoEmocional = estadoEmocional;
    }

    public String getNotasTest() {
        return notasTest;
    }

    public void setNotasTest(String notasTest) {
        this.notasTest = notasTest;
    }

    public Double getPuntajeTest() {
        return puntajeTest;
    }

    public void setPuntajeTest(Double puntajeTest) {
        this.puntajeTest = puntajeTest;
    }
}
