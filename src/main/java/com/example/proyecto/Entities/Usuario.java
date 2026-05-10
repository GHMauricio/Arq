package com.example.proyecto.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombreUsuario;
    private String correoUsuario;
    private String contrasenaUsuario;
    private LocalDate fechaRegistro;
    private Boolean enabled;

    @NotNull(message = "Ingrese su fecha de nacimiento")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate nacimientoUsuario;

    private LocalDate nacimientoAdolescente;
    private String generoAdolescente;
    private String interesesAdolescente;
    private Integer cantidadAdolescente;

    @OneToMany(mappedBy = "usuario")
    private List<ProgresoEmocional> progresos;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> rolUsuario;

    public List<Role> getRoles() { return rolUsuario; }

    public String getUsername() { return nombreUsuario; }

    public void setUsername(String username) { this.nombreUsuario = username; }

    public String getPassword() { return contrasenaUsuario; }

    public void setPassword(String password) { this.contrasenaUsuario = password; }
}
