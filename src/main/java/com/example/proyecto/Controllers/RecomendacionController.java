package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.RecomendacionDTO;
import com.example.proyecto.Servicesinterfaces.IRecomendacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Recomendacion-general")
@CrossOrigin(origins = "*")
public class RecomendacionController {

    @Autowired
    private IRecomendacionService rS;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> crear(@RequestBody RecomendacionDTO recomendacion) {
        if (recomendacion.getFechaEnvio() == null) {
            return ResponseEntity.badRequest().body("La fecha de envío no puede ser nula");
        }
        if (recomendacion.getFechaEnvio().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de envío no puede ser futura");
        }
        if (recomendacion.getIdUsuario() == null) {
            return ResponseEntity.badRequest().body("El id del usuario no puede estar vacío");
        }

        try {
            rS.insertar(recomendacion);
            return new ResponseEntity<>("Recomendación creada exitosamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(rS.listar());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/Usuario/{idUsuario}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> listarPorUsuario(@PathVariable Long idUsuario) {
        try {
            return ResponseEntity.ok(rS.listarPorUsuario(idUsuario));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> modificar(@RequestBody RecomendacionDTO recomendacion) {
        if (recomendacion.getIdRecomendacion() == null) {
            return ResponseEntity.badRequest().body("El id de la recomendación no puede estar vacío");
        }
        if (recomendacion.getFechaEnvio() == null) {
            return ResponseEntity.badRequest().body("La fecha de envío no puede ser nula");
        }
        if (recomendacion.getFechaEnvio().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de envío no puede ser futura");
        }

        try {
            rS.update(recomendacion);
            return ResponseEntity.ok("Recomendación actualizada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            rS.eliminar(id);
            return ResponseEntity.ok("Recomendación eliminada correctamente con ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo eliminar: " + e.getMessage());
        }
    }
}
