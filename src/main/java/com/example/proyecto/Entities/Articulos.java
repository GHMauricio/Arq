package com.example.proyecto.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "Articulos")
@Data
public class Articulos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticulo;

    @ManyToOne
    @JoinColumn(name = "idRecomendacion")
    private Recomendacion recomendacion;

    private String tituloArticulo;
    private String contenidoArticulo;
    private String categoriaArticulo;
    private LocalDate fechaPublicacion;
    private String autorArticulo;
}
