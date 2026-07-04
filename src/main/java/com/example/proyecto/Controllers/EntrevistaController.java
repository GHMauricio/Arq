package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.EntrevistaDTO;
import com.example.proyecto.Servicesinterfaces.IEntrevistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Entrevista-general")
@CrossOrigin(origins = "*")
public class EntrevistaController {

    @Autowired
    private IEntrevistaService eS;

    @PostMapping("/registrar")
    //@PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> registrar(@RequestBody EntrevistaDTO dto) {
        if (dto.getFechaEntrevista() == null) {
            return ResponseEntity.badRequest().body("La fecha de la entrevista no puede ser nula");
        }
        if (dto.getTemaEntrevista() == null || dto.getTemaEntrevista().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El tema de la entrevista no puede ser nulo o vacío");
        }
        if (dto.getIdRecomendacion() == null) {
            return ResponseEntity.badRequest().body("El id de la recomendación no puede estar vacío");
        }

        try {
            eS.insertar(dto);
            return new ResponseEntity<>("Entrevista registrada exitosamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    //@PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(eS.listar());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<EntrevistaDTO> obtenerEntrevista(@PathVariable("id") Long id) {
        try {
            EntrevistaDTO dto = eS.listarId(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
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

    @GetMapping("/tema/{temaEntrevista}")
    //@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> listarPorTema(@PathVariable String temaEntrevista) {
        List<EntrevistaDTO> lista = eS.listarPorTema(temaEntrevista);
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay entrevistas con el tema: " + temaEntrevista);
        }
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> modificar(@RequestBody EntrevistaDTO dto) {
        if (dto.getIdEntrevista() == null) {
            return ResponseEntity.badRequest().body("El id de la entrevista no puede estar vacío");
        }
        if (dto.getFechaEntrevista() == null) {
            return ResponseEntity.badRequest().body("La fecha de la entrevista no puede ser nula");
        }
        if (dto.getTemaEntrevista() == null || dto.getTemaEntrevista().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El tema de la entrevista no puede ser nulo o vacío");
        }

        try {
            eS.update(dto);
            return ResponseEntity.ok("Entrevista actualizada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        // Solo el ADMINISTRADOR puede eliminar entrevistas registradas
        try {
            eS.eliminar(id);
            return ResponseEntity.ok("Entrevista eliminada exitosamente con ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
