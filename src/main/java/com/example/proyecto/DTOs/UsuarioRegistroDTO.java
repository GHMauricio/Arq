package com.example.proyecto.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioRegistroDTO {
    private Long idUsuario;
    private String nombreUsuario;
    private String correoUsuario;
    private String contrasenaUsuario;
    private String rolUsuario;
    private LocalDate fechaRegistro;
     private Boolean enabled;
    private LocalDate nacimientoUsuario;
    private LocalDate nacimientoAdolescente;
    private String generoAdolescente;
    private String interesesAdolescente;
    private Integer cantidadAdolescente;
}
