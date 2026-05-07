package com.example.proyecto.Controllers;


import com.example.proyecto.DTOs.NotificacionDTO;
import com.example.proyecto.Entities.Notificacion;
import com.example.proyecto.Servicesinterfaces.INotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionController {

    @Autowired
    private INotificacionService nS;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> registrar(@RequestBody Notificacion notificacion) {
        try {
            return new ResponseEntity<>(nS.guardar(notificacion), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/Usuario/{idUsuario}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public List<NotificacionDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return nS.listarPorUsuario(idUsuario);
    }


    @PutMapping("/Usuario/{idUsuario}/leer")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<String> marcarComoLeidas(@PathVariable Long idUsuario) {
        nS.marcarComoLeido(idUsuario);
        return ResponseEntity.ok("Notificaciones actualizadas correctamente.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        nS.eliminar(id);
        return ResponseEntity.ok("Progreso eliminado correctamente");
    }
}
