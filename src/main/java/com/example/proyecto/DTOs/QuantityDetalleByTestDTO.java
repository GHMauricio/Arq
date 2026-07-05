package com.example.proyecto.DTOs;

import lombok.Data;

@Data
public class QuantityDetalleByTestDTO {
    private Long idTest;
    private String estadoEmocional;
    private Long cantidadDetalle;
}