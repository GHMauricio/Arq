package com.example.proyecto.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProgresoEmocionalDTO {

    private Long idProgreso;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha de progreso no puede ser futura")
    private LocalDate fechaProgreso;
    
    private String estadoEmocional;
    private String comentariosProgreso;
    private Integer puntajeEmocional;
    private Long idUsuario;
}
