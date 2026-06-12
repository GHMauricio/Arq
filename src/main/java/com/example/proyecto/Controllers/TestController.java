package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.TestDTO;
import com.example.proyecto.Servicesinterfaces.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Tests-general")
@CrossOrigin(origins = "*")
public class TestController {

    @Autowired
    private ITestService tS;

    @PostMapping
    //@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<String> registrar(@RequestBody TestDTO dto) {
        if (dto.getFechaTest() == null) {
            return ResponseEntity.badRequest().body("La fecha del test no puede ser nula");
        }
        if (dto.getFechaTest().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha del test no puede ser futura");
        }
        if (dto.getPuntajeTest() == null || dto.getPuntajeTest() < 0.0 || dto.getPuntajeTest() > 20.0) {
            return ResponseEntity.badRequest().body("El puntaje debe estar entre 0 y 20");
        }

        try {
            tS.insertar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Test registrado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    //@PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<TestDTO> listar() {
        return tS.listar();
    }

    @GetMapping("/puntaje-ascendente")
    //@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> listarPorPuntajeAscendente() {
        List<TestDTO> lista = tS.listarPorPuntajeAscendente();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay tests registrados");
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/Usuario/{idUsuario}")
    //@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public List<TestDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return tS.listarPorUsuario(idUsuario);
    }

    @PutMapping
    //@PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> modificar(@RequestBody TestDTO dto) {
        if (dto.getIdTest() == null) {
            return ResponseEntity.badRequest().body("El id del test no puede estar vacío");
        }
        if (dto.getFechaTest() == null) {
            return ResponseEntity.badRequest().body("La fecha del test no puede ser nula");
        }
        if (dto.getFechaTest().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha del test no puede ser futura");
        }
        if (dto.getPuntajeTest() == null || dto.getPuntajeTest() < 0.0 || dto.getPuntajeTest() > 20.0) {
            return ResponseEntity.badRequest().body("El puntaje debe estar entre 0 y 20");
        }

        try {
            tS.update(dto);
            return ResponseEntity.ok("Test actualizado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        // Solo el ADMINISTRADOR puede eliminar tests del sistema
        try {
            tS.eliminar(id);
            return ResponseEntity.ok("Test eliminado correctamente con ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar: " + e.getMessage());
        }
    }
}
