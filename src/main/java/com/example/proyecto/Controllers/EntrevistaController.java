package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.EntrevistaDTO;
import com.example.proyecto.Entities.Entrevista;
import com.example.proyecto.Servicesinterfaces.IEntrevistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Entrevista-general")
@CrossOrigin(origins = "*")
public class EntrevistaController {
    @Autowired
    private IEntrevistaService eS;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> registrar(@RequestBody Entrevista entrevista){
        eS.insertar(entrevista);
        return new ResponseEntity<>(entrevista, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<EntrevistaDTO> listar(){
        return eS.listar();
    }

    @GetMapping("/Recomendacion/{idRecomendacion}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public List<EntrevistaDTO> listarPorRecomendacion(@PathVariable Long idRecomendacion) {
        return eS.listarPorRecomendacion(idRecomendacion);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> modificar(@RequestBody Entrevista entrevista) {
        eS.insertar(entrevista); // JPA detecta el ID y actualiza
        return new ResponseEntity<>(entrevista, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        eS.eliminar(id);
        return ResponseEntity.ok("Progreso eliminado correctamente");
    }
}

