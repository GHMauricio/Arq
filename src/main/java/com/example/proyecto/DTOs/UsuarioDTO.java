package com.example.proyecto.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDTO {
    private Long idUsuario;
    private String nombreUsuario;
    private String correoUsuario;
    private String rolUsuario;
    private LocalDate fechaRegistro;
    private LocalDate nacimientoUsuario;
    private LocalDate nacimientoAdolescente;
    private String generoAdolescente;
    private String interesesAdolescente;
    private Integer cantidadAdolescente;
}
