package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.RecomendacionDTO;
import com.example.proyecto.Entities.Recomendacion;
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
    public ResponseEntity<?> crear(@RequestBody Recomendacion recomendacion) {
        if (recomendacion.getFechaEnvio() == null) {
            return ResponseEntity.badRequest().body("La fecha de envío no puede ser nula");
        }
        if (recomendacion.getFechaEnvio().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de envío no puede ser futura");
        }
        if (recomendacion.getMensajeNotificacion() == null || recomendacion.getMensajeNotificacion().isBlank()) {
            return ResponseEntity.badRequest().body("El mensaje de notificación no puede estar vacío");
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
    public List<RecomendacionDTO> listar() {
        return rS.listar();
    }

    @GetMapping("/Usuario/{idUsuario}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public List<RecomendacionDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return rS.listarPorUsuario(idUsuario);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> modificar(@RequestBody Recomendacion recomendacion) {
        if (recomendacion.getFechaEnvio() == null) {
            return ResponseEntity.badRequest().body("La fecha de envío no puede ser nula");
        }
        if (recomendacion.getFechaEnvio().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de envío no puede ser futura");
        }
        if (recomendacion.getMensajeNotificacion() == null || recomendacion.getMensajeNotificacion().isBlank()) {
            return ResponseEntity.badRequest().body("El mensaje de notificación no puede estar vacío");
        }

        try {
            rS.insertar(recomendacion);
            return new ResponseEntity<>("Recomendación actualizada correctamente", HttpStatus.OK);
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
