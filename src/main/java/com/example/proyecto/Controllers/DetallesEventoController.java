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
@RequestMapping("/Eventos-general")
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
            eS.guardar(dto);
            return new ResponseEntity<>("Evento creado exitosamente", HttpStatus.CREATED);
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
            eS.update(dto);
            return ResponseEntity.ok("Evento actualizado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            eS.eliminar(id);
            return ResponseEntity.ok("Evento eliminado exitosamente con ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
