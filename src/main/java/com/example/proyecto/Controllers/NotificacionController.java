package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.NotificacionDTO;
import com.example.proyecto.Servicesinterfaces.INotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionController {

    @Autowired
    private INotificacionService nS;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> registrar(@RequestBody NotificacionDTO dto) {
        if (dto.getMensajeNotificacion() == null || dto.getMensajeNotificacion().isBlank()) {
            return ResponseEntity.badRequest().body("El mensaje de la notificación no puede ser nulo o vacío");
        }
        if (dto.getFechaEnvio() == null) {
            return ResponseEntity.badRequest().body("La fecha de envío no puede ser nula");
        }
        if (dto.getFechaEnvio().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de envío no puede ser futura");
        }

        try {
            nS.guardar(dto);
            return new ResponseEntity<>("Notificación creada exitosamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(nS.listar());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/leidas/{anio}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> listarLeidasPorAnio(@PathVariable int anio) {
        List<NotificacionDTO> leidas = nS.listarLeidasPorAnio(anio);
        if (leidas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay notificaciones leídas para el año: " + anio);
        }
        return ResponseEntity.ok(leidas);
    }

    @GetMapping("/Usuario/{idUsuario}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> listarPorUsuario(@PathVariable Long idUsuario) {
        try {
            return ResponseEntity.ok(nS.listarPorUsuario(idUsuario));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/Usuario/{idUsuario}/leer")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> marcarComoLeidas(@PathVariable Long idUsuario) {
        try {
            nS.marcarComoLeido(idUsuario);
            return ResponseEntity.ok("Notificaciones actualizadas correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody NotificacionDTO dto) {
        if (dto.getMensajeNotificacion() == null || dto.getMensajeNotificacion().isBlank()) {
            return ResponseEntity.badRequest().body("El mensaje de la notificación no puede ser nulo o vacío");
        }
        if (dto.getFechaEnvio() == null) {
            return ResponseEntity.badRequest().body("La fecha de envío no puede ser nula");
        }
        if (dto.getFechaEnvio().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de envío no puede ser futura");
        }

        try {
            nS.actualizar(id, dto);
            return ResponseEntity.ok("Notificación actualizada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            nS.eliminar(id);
            return ResponseEntity.ok("Notificación eliminada exitosamente con ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
