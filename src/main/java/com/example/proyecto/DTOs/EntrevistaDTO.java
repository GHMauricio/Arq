package com.example.proyecto.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EntrevistaDTO {
    private Long idEntrevista;
    private Long idRecomendacion;
    private LocalDate fechaEntrevista;
    private String temaEntrevista;
    private String comentarioEntrevista;


}
