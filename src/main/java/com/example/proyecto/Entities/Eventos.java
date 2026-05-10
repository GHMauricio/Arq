package com.example.proyecto.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "Eventos")
@Data
public class Eventos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    private String tituloEvento;
    private String descripcionEvento;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String tipoEvento;
}
