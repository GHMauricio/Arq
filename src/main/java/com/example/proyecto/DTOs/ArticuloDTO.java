package com.example.proyecto.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ArticuloDTO {
    private Long idArticulo;
    private Long idRecomendacion;
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

    public Long getIdRecomendacion() {
        return idRecomendacion;
    }

    public void setIdRecomendacion(Long idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
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
