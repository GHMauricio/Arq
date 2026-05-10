package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.DetallesEventoDTO;
import com.example.proyecto.Entities.DetallesEvento;
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
    public ResponseEntity<?> registrar(@RequestBody DetallesEvento detalle) {
        try {
            deS.insertar(detalle);
            return new ResponseEntity<>(detalle, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<DetallesEventoDTO> listar() {
        return deS.listar();
    }

    @GetMapping("/Evento/{idEvento}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public List<DetallesEventoDTO> listarPorEvento(@PathVariable Long idEvento) {
        return deS.listarPorEvento(idEvento);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> modificar(@RequestBody DetallesEvento detalle) {
        try {
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
            return ResponseEntity.ok("Detalle de evento con ID " + id + " eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
