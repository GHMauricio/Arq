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
}
