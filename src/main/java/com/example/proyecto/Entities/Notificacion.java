package com.example.proyecto.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "Notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotificacion;

    private String mensajeNotificacion;
    private LocalDate fechaEnvio;
    private boolean leido = false;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
}
