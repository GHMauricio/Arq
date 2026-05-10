package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.EventosDTO;
import com.example.proyecto.Servicesinterfaces.IEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DetallesEventos-general")
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    private IEventoService eS;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> crear(@RequestBody EventosDTO dto) {
        if (dto.getTituloEvento() == null || dto.getTituloEvento().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El título del evento no puede ser nulo o vacío");
        }
        if (dto.getFechaInicio() == null || dto.getFechaFin() == null) {
            return ResponseEntity.badRequest().body("Las fechas de inicio y fin no pueden ser nulas");
        }
        if (dto.getFechaFin().isBefore(dto.getFechaInicio())) {
            return ResponseEntity.badRequest().body("La fecha de fin no puede ser anterior a la de inicio");
        }

        try {
            if (detalle.getActividad() == null || detalle.getActividad().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("La actividad del evento es obligatoria.");
            }

            if (detalle.getResponsable() == null || detalle.getResponsable().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("El responsable del evento es obligatorio.");
            }

            if (detalle.getHoraInicio() != null && detalle.getHoraFin() != null
                    && !detalle.getHoraInicio().isBefore(detalle.getHoraFin())) {
                return ResponseEntity.badRequest().body("La hora de inicio debe ser anterior a la hora de fin.");
            }

            deS.insertar(detalle);
            return new ResponseEntity<>(detalle, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> listarTodo() {
        try {
            return ResponseEntity.ok(eS.listarEventosDTO());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/Usuario/{idUsuario}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> listarPorUsuario(@PathVariable Long idUsuario) {
        try {
            return ResponseEntity.ok(eS.listarPorUsuario(idUsuario));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/tipo/{tipoEvento}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> listarPorTipoEvento(@PathVariable String tipoEvento) {
        List<EventosDTO> lista = eS.listarPorTipoEvento(tipoEvento);
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay eventos con el tipo: " + tipoEvento);
        }
        return ResponseEntity.ok(lista);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> modificar(@RequestBody EventosDTO dto) {
        if (dto.getIdEvento() == null) {
            return ResponseEntity.badRequest().body("El id del evento no puede estar vacío");
        }
        if (dto.getTituloEvento() == null || dto.getTituloEvento().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El título del evento no puede ser nulo o vacío");
        }
        if (dto.getFechaInicio() == null || dto.getFechaFin() == null) {
            return ResponseEntity.badRequest().body("Las fechas de inicio y fin no pueden ser nulas");
        }
        if (dto.getFechaFin().isBefore(dto.getFechaInicio())) {
            return ResponseEntity.badRequest().body("La fecha de fin no puede ser anterior a la de inicio");
        }

        try {
            if (detalle.getIdDetalleEvento() == null) {
                return ResponseEntity.badRequest().body("El ID del detalle es obligatorio para modificar.");
            }

            if (detalle.getActividad() == null || detalle.getActividad().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("La actividad del evento es obligatoria.");
            }

            if (detalle.getHoraInicio() != null && detalle.getHoraFin() != null
                    && !detalle.getHoraInicio().isBefore(detalle.getHoraFin())) {
                return ResponseEntity.badRequest().body("La hora de inicio debe ser anterior a la hora de fin.");
            }

            deS.insertar(detalle);
            return new ResponseEntity<>(detalle, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            deS.eliminar(id);
            return ResponseEntity.ok("El detalle de evento con ID " + id + " fue eliminado correctamente del sistema.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
