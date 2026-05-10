package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.EntrevistaDTO;
import com.example.proyecto.Entities.Entrevista;
import com.example.proyecto.Servicesinterfaces.IEntrevistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Entrevista-general")
@CrossOrigin(origins = "*")
public class EntrevistaController {

    @Autowired
    private IEntrevistaService eS;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> registrar(@RequestBody Entrevista entrevista) {
        if (entrevista.getTemaEntrevista() == null || entrevista.getTemaEntrevista().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El tema de la entrevista es obligatorio.");
        }
        if (entrevista.getComentarioEntrevista() == null || entrevista.getComentarioEntrevista().length() < 10) {
            return ResponseEntity.badRequest().body("El comentario de la entrevista debe tener al menos 10 caracteres.");
        }
        if (entrevista.getFechaEntrevista() != null && entrevista.getFechaEntrevista().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de la entrevista no puede ser una fecha futura.");
        }

        try {
            eS.insertar(entrevista);
            return new ResponseEntity<>("Entrevista creada exitosamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(eS.listar());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/Recomendacion/{idRecomendacion}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> listarPorRecomendacion(@PathVariable Long idRecomendacion) {
        try {
            return ResponseEntity.ok(eS.listarPorRecomendacion(idRecomendacion));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> modificar(@RequestBody Entrevista entrevista) {
        if (entrevista.getIdEntrevista() == null) {
            return ResponseEntity.badRequest().body("El ID de la entrevista es obligatorio para modificar.");
        }
        if (entrevista.getTemaEntrevista() == null || entrevista.getTemaEntrevista().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El tema de la entrevista es obligatorio.");
        }
        if (entrevista.getComentarioEntrevista() == null || entrevista.getComentarioEntrevista().length() < 10) {
            return ResponseEntity.badRequest().body("El comentario de la entrevista debe tener al menos 10 caracteres.");
        }
        if (entrevista.getFechaEntrevista() != null && entrevista.getFechaEntrevista().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de la entrevista no puede ser una fecha futura.");
        }

        try {
            eS.insertar(entrevista);
            return ResponseEntity.ok("Entrevista actualizada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            eS.eliminar(id);
            return ResponseEntity.ok("Entrevista eliminada exitosamente con ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
