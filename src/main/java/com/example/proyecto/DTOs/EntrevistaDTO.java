package com.example.proyecto.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EntrevistaDTO {
    private Long idEntrevista;
    private Long idRecomendacion;
    private LocalDate fechaEntrevista;
    private String temaEntrevista;
    private String comentarioEntrevista;

    public Long getIdEntrevista() {
        return idEntrevista;
    }

    public void setIdEntrevista(Long idEntrevista) {
        this.idEntrevista = idEntrevista;
    }

    public Long getIdRecomendacion() {
        return idRecomendacion;
    }

    public void setIdRecomendacion(Long idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
    }

    public LocalDate getFechaEntrevista() {
        return fechaEntrevista;
    }

    public void setFechaEntrevista(LocalDate fechaEntrevista) {
        this.fechaEntrevista = fechaEntrevista;
    }

    public String getTemaEntrevista() {
        return temaEntrevista;
    }

    public void setTemaEntrevista(String temaEntrevista) {
        this.temaEntrevista = temaEntrevista;
    }

    public String getComentarioEntrevista() {
        return comentarioEntrevista;
    }

    public void setComentarioEntrevista(String comentarioEntrevista) {
        this.comentarioEntrevista = comentarioEntrevista;
    }
}
