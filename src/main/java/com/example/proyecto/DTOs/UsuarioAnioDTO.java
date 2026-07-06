package com.example.proyecto.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAnioDTO {
    private Integer anio;
    private Long cantidad;
}
