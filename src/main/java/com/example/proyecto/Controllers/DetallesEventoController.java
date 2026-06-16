package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.DetallesEventoDTO;
import com.example.proyecto.Servicesinterfaces.IDetallesEventosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DetallesEvento-general")
@CrossOrigin(origins = "*")
public class DetallesEventoController {

    @Autowired
    private IDetallesEventosService deS;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> registrar(@RequestBody DetallesEventoDTO dto) {
        if (dto.getActividad() == null || dto.getActividad().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La actividad no puede ser nula o vacía");
        }
        if (dto.getResponsable() == null || dto.getResponsable().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El responsable no puede ser nulo o vacío");
        }
        if (dto.getIdEvento() == null) {
            return ResponseEntity.badRequest().body("El id del evento no puede estar vacío");
        }

        try {
            deS.insertar(dto);
            return new ResponseEntity<>("Detalle de evento registrado exitosamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(deS.listar());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/Evento/{idEvento}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> listarPorEvento(@PathVariable Long idEvento) {
        try {
            return ResponseEntity.ok(deS.listarPorEvento(idEvento));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/actividad/{actividad}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> listarPorActividad(@PathVariable String actividad) {
        List<DetallesEventoDTO> lista = deS.listarPorActividad(actividad);
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay detalles con la actividad: " + actividad);
        }
        return ResponseEntity.ok(lista);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> modificar(@RequestBody DetallesEventoDTO dto) {
        if (dto.getIdDetalleEvento() == null) {
            return ResponseEntity.badRequest().body("El id del detalle no puede estar vacío");
        }
        if (dto.getActividad() == null || dto.getActividad().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La actividad no puede ser nula o vacía");
        }
        if (dto.getResponsable() == null || dto.getResponsable().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El responsable no puede ser nulo o vacío");
        }

        try {
            deS.update(dto);
            return ResponseEntity.ok("Detalle de evento actualizado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            deS.eliminar(id);
            return ResponseEntity.ok("Detalle de evento eliminado exitosamente con ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
