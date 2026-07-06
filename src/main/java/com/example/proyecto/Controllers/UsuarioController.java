package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.UsuarioDTO;
import com.example.proyecto.DTOs.UsuarioRegistroDTO;
import com.example.proyecto.Servicesinterfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/usuarios-general")
public class UsuarioController {

    @Autowired
    private IUsuarioService uS;

    @GetMapping("/listar")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<UsuarioDTO> listar() {
        return uS.listar();
    }

    @GetMapping("/ordenar-por-anio")
    //@PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> listarPorNacimientoAdolescente() {
        List<UsuarioAnioDTO> lista = uS.contabilizarAdolescentesPorAnio();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay usuarios registrados");
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/contar-por-genero")
    //@PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> contabilizarUsuariosPorGenero() {
        List<UsuarioGeneroDTO> lista = uS.contabilizarUsuariosPorGenero();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay usuarios registrados");
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable("id") Long id) {
        try {
            UsuarioDTO dto = uS.listarId(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/Registrar")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> registroUsuario(@RequestBody UsuarioRegistroDTO dto) {
        if (dto.getCorreoUsuario() == null || !dto.getCorreoUsuario().contains("@")) {
            return ResponseEntity.badRequest().body("El correo debe contener un '@'");
        }
        if (dto.getFechaRegistro() != null && dto.getFechaRegistro().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de registro no puede ser futura");
        }
        if (dto.getContrasenaUsuario() == null ||
                !dto.getContrasenaUsuario().matches("^(?=.*[A-Za-z])(?=.*\\d).+$")) {
            return ResponseEntity.badRequest().body("La contraseña debe contener letras y números");
        }

        try {
            uS.insertar(dto);
            return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> modificarUsuario(@PathVariable Long id, @RequestBody UsuarioRegistroDTO dto) {
        if (dto.getCorreoUsuario() == null || !dto.getCorreoUsuario().contains("@")) {
            return ResponseEntity.badRequest().body("El correo debe contener un '@'");
        }
        if (dto.getFechaRegistro() != null && dto.getFechaRegistro().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de registro no puede ser futura");
        }
        if (dto.getContrasenaUsuario() == null ||
                !dto.getContrasenaUsuario().matches("^(?=.*[A-Za-z])(?=.*\\d).+$")) {
            return ResponseEntity.badRequest().body("La contraseña debe contener letras y números");
        }

        try {
            dto.setIdUsuario(id);
            uS.update(dto);
            return ResponseEntity.ok("Usuario actualizado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        // Solo el ADMINISTRADOR puede eliminar usuarios del sistema
        try {
            uS.eliminar(id);
            return ResponseEntity.ok("Usuario eliminado correctamente con ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo eliminar: " + e.getMessage());
        }
    }
}
