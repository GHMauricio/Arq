package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.RecomendacionDTO;
import com.example.proyecto.Entities.Recomendacion;
import com.example.proyecto.Servicesinterfaces.IRecomendacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Recomendacion-general")
@CrossOrigin(origins = "*")
public class RecomendacionController {

    @Autowired
    private IRecomendacionService rS;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> crear(@RequestBody Recomendacion recomendacion) {
        try {
            rS.insertar(recomendacion);
            return new ResponseEntity<>(recomendacion, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<RecomendacionDTO> listar() {
        return rS.listar();
    }

    @GetMapping("/Usuario/{idUsuario}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public List<RecomendacionDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return rS.listarPorUsuario(idUsuario);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> modificar(@RequestBody Recomendacion recomendacion) {
        try {
            rS.insertar(recomendacion); // Usualmente el mismo método 'save' de JPA maneja el update si el ID existe
            return new ResponseEntity<>(recomendacion, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        rS.eliminar(id);

        return ResponseEntity.ok("Eliminado correctamente");
    }

}
