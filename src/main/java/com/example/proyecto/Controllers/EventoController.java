package com.example.proyecto.Controllers;
import com.example.proyecto.DTOs.EventosDTO;
import com.example.proyecto.Servicesinterfaces.IEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Eventos-general")
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    private IEventoService eS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<EventosDTO> listarTodo() {
        return eS.listarEventosDTO();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> crear(@RequestBody EventosDTO dto) {
        if (dto.getTituloEvento() == null || dto.getTituloEvento().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El título del evento no puede estar vacío.");
        }
        if (dto.getTipoEvento() == null || dto.getTipoEvento().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El tipo de evento no puede estar vacío.");
        }
        if (dto.getFechaInicio().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de inicio no puede ser en el futuro, ya que sería imposible realizarlo.");
        }
        try {
            return new ResponseEntity<>(eS.guardar(dto), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody EventosDTO dto) {
        if (dto.getTituloEvento() == null || dto.getTituloEvento().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El título del evento no puede estar vacío.");
        }
        if (dto.getTipoEvento() == null || dto.getTipoEvento().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El tipo de evento no puede estar vacío.");
        }
        if (dto.getFechaInicio().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de inicio no puede ser en el futuro, ya que sería imposible realizarlo.");
        }
        try {
            return ResponseEntity.ok(eS.actualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/Usuario/{idUsuario}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public List<EventosDTO> listar(@PathVariable Long idUsuario) {
        return eS.listarPorUsuario(idUsuario);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            eS.eliminar(id);
            return ResponseEntity.ok("El evento con ID " + id + " fue eliminado correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/por-anio")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<EventosDTO> listarPorAnio() {
        return eS.listarPorAnioDescendente();
    }
}
