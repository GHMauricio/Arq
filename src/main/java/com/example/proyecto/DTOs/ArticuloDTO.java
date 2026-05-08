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

    
}
