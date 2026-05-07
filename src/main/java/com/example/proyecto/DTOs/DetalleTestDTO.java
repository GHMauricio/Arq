package com.example.proyecto.DTOs;

import lombok.Data;

@Data
public class DetalleTestDTO {
    private Long idDetalleTest;
    private Long idTest;
    private String pregunta;
    private String respuesta;
    private String observacion;
}
