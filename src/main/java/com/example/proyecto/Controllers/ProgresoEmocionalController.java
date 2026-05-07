package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.ProgresoEmocionalDTO;
import com.example.proyecto.Entities.ProgresoEmocional;
import com.example.proyecto.Servicesinterfaces.IProgresoEmocionalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Progreso-general")
@CrossOrigin(origins = "*")
public class ProgresoEmocionalController {

    @Autowired
    private IProgresoEmocionalService pS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<ProgresoEmocionalDTO> listar(){
        return pS.listarProgresosDTO();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PADRE')")
    public ResponseEntity<?> registrar(@Valid @RequestBody ProgresoEmocional progreso){
        try{
            ProgresoEmocional nuevo = pS.guardarProgreso(progreso);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<ProgresoEmocionalDTO> obtener(@PathVariable Long id){
        ProgresoEmocionalDTO dto = pS.obtenerProgreso(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/Usuario/{idUsuario}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public List<ProgresoEmocionalDTO> listarPorUsuario(@PathVariable Long idUsuario){
        return pS.listarProgresosPorUsuario(idUsuario);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        pS.eliminarProgreso(id);
        return ResponseEntity.ok("Progreso eliminado correctamente");
    }



}
