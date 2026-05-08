package com.example.proyecto.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Articulos")
@Data
public class Articulos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticulo;

    // Nueva relación según el diagrama
    @ManyToOne
    @JoinColumn(name = "idRecomendacion")
    private Recomendacion recomendacion;

    private String tituloArticulo;
    private String contenidoArticulo;
    private String categoriaArticulo;
    private LocalDate fechaPublicacion;
    private String autorArticulo;

    public Long getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Long idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Recomendacion getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(Recomendacion recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getTituloArticulo() {
        return tituloArticulo;
    }

    public void setTituloArticulo(String tituloArticulo) {
        this.tituloArticulo = tituloArticulo;
    }

    public String getContenidoArticulo() {
        return contenidoArticulo;
    }

    public void setContenidoArticulo(String contenidoArticulo) {
        this.contenidoArticulo = contenidoArticulo;
    }

    public String getCategoriaArticulo() {
        return categoriaArticulo;
    }

    public void setCategoriaArticulo(String categoriaArticulo) {
        this.categoriaArticulo = categoriaArticulo;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getAutorArticulo() {
        return autorArticulo;
    }

    public void setAutorArticulo(String autorArticulo) {
        this.autorArticulo = autorArticulo;
    }
}
