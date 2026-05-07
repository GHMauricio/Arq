package com.example.proyecto.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TestDTO {
    private Long idTest;
    private Long idUsuario;
    private LocalDate fechaTest;
    private String estadoEmocional;
    private String notasTest;
    private Double puntajeTest;
}
