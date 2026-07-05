package com.example.proyecto.DTOs;

import lombok.Data;

@Data
public class QuantityTestByUsuarioDTO {
    private Long idUsuario;
    private String nombreUsuario;
    private Long cantidadTest;
}