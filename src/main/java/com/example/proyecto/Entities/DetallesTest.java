package com.example.proyecto.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DetallesTests")
@Data
public class DetallesTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleTest;

    @ManyToOne
    @JoinColumn(name = "idTest")
    private Test test;

    private String pregunta;
    private String respuesta;
    private String observacion;
}
