package com.example.proyecto.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecomendacionDTO {
        private Long idRecomendacion;
        private Long idUsuario;
        private LocalDate fechaEnvio;
        private String estadoRecomendacion;


}
