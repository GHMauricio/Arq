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

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<EventosDTO> listarTodo() {
        return eS.listarEventosDTO();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> crear(@RequestBody EventosDTO dto) {
        try {
            return new ResponseEntity<>(eS.guardar(dto), HttpStatus.CREATED);
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
        eS.eliminar(id);
        return ResponseEntity.ok("Evento eliminado correctamente");
    }
}
