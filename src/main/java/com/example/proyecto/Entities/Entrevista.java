package com.example.proyecto.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Entrevistas")
@Data
public class Entrevista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrevista;

    @ManyToOne
    @JoinColumn(name = "idRecomendacion")
    private Recomendacion recomendacion;

    private LocalDate fechaEntrevista;
    private String temaEntrevista;
    private String comentarioEntrevista;

    public Long getIdEntrevista() {
        return idEntrevista;
    }

    public void setIdEntrevista(Long idEntrevista) {
        this.idEntrevista = idEntrevista;
    }

    public Recomendacion getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(Recomendacion recomendacion) {
        this.recomendacion = recomendacion;
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
