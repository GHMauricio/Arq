package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.ProgresoEmocionalDTO;
import com.example.proyecto.Servicesinterfaces.IProgresoEmocionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Progreso-general")
@CrossOrigin(origins = "*")
public class ProgresoEmocionalController {

    @Autowired
    private IProgresoEmocionalService pS;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> registrar(@RequestBody ProgresoEmocionalDTO dto) {
        if (dto.getFechaProgreso() == null) {
            return ResponseEntity.badRequest().body("La fecha de progreso no puede ser nula");
        }
        if (dto.getEstadoEmocional() == null || dto.getEstadoEmocional().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El estado emocional no puede ser nulo o vacío");
        }
        if (dto.getIdUsuario() == null) {
            return ResponseEntity.badRequest().body("El id del usuario no puede estar vacío");
        }

        try {
            pS.guardarProgreso(dto);
            return new ResponseEntity<>("Progreso emocional registrado exitosamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<ProgresoEmocionalDTO> listar() {
        return pS.listarProgresosDTO();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<ProgresoEmocionalDTO> obtener(@PathVariable Long id) {
        ProgresoEmocionalDTO dto = pS.obtenerProgreso(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/Usuario/{idUsuario}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public List<ProgresoEmocionalDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return pS.listarProgresosPorUsuario(idUsuario);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        // Solo el ADMINISTRADOR puede eliminar registros de progreso emocional
        try {
            pS.eliminarProgreso(id);
            return ResponseEntity.ok("Progreso emocional eliminado correctamente con ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
