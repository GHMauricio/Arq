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
        try {
            if (entrevista.getTemaEntrevista() == null || entrevista.getTemaEntrevista().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("El tema de la entrevista es obligatorio.");
            }

            if (entrevista.getFechaEntrevista() == null) {
                return ResponseEntity.badRequest().body("La fecha de la entrevista es obligatoria.");
            }

            if (entrevista.getFechaEntrevista().isBefore(LocalDate.now())) {
                return ResponseEntity.badRequest().body("La fecha de la entrevista no puede ser una fecha pasada.");
            }

            eS.insertar(entrevista);
            return new ResponseEntity<>(entrevista, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<EntrevistaDTO> listar() {
        return eS.listar();
    }

    @GetMapping("/Recomendacion/{idRecomendacion}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public List<EntrevistaDTO> listarPorRecomendacion(@PathVariable Long idRecomendacion) {
        return eS.listarPorRecomendacion(idRecomendacion);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> modificar(@RequestBody Entrevista entrevista) {
        try {
            if (entrevista.getIdEntrevista() == null) {
                return ResponseEntity.badRequest().body("El ID de la entrevista es obligatorio para modificar.");
            }

            if (entrevista.getTemaEntrevista() == null || entrevista.getTemaEntrevista().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("El tema de la entrevista es obligatorio.");
            }

            if (entrevista.getComentarioEntrevista() == null || entrevista.getComentarioEntrevista().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("El comentario de la entrevista es obligatorio.");
            }

            eS.insertar(entrevista);
            return new ResponseEntity<>(entrevista, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            eS.eliminar(id);
            return ResponseEntity.ok("La entrevista con ID " + id + " fue eliminada correctamente del sistema.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
