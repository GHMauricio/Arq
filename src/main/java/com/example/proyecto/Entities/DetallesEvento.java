package com.example.proyecto.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;

@Entity
@Table(name = "DetallesEvento")
@Data
public class DetallesEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleEvento;

    @ManyToOne
    @JoinColumn(name = "idEvento")
    private Eventos evento;

    private String actividad;
    private String responsable;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}
