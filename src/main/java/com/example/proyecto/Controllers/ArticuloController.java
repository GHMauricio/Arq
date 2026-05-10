package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.ArticuloDTO;
import com.example.proyecto.Entities.Articulos;
import com.example.proyecto.Servicesinterfaces.IArticuloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Articulos-general")
@CrossOrigin(origins = "*")
public class ArticuloController {

    @Autowired
    private IArticuloService aS;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> registrar(@RequestBody Articulos articulo) {
        if (articulo.getTituloArticulo() == null || articulo.getTituloArticulo().isBlank()) {
            return ResponseEntity.badRequest().body("El título del artículo es obligatorio");
        }
        if (articulo.getContenidoArticulo() == null || articulo.getContenidoArticulo().isBlank()) {
            return ResponseEntity.badRequest().body("El contenido del artículo es obligatorio");
        }
        if (articulo.getAutorArticulo() == null || articulo.getAutorArticulo().isBlank()) {
            return ResponseEntity.badRequest().body("El autor del artículo es obligatorio");
        }
        try {
            aS.insertar(articulo);
            return new ResponseEntity<>(articulo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public List<ArticuloDTO> listar() {
        return aS.listar();
    }

    @GetMapping("/Recomendacion/{idRecomendacion}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public List<ArticuloDTO> listarPorRecomendacion(@PathVariable Long idRecomendacion) {
        return aS.listarPorRecomendacion(idRecomendacion);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> modificar(@RequestBody Articulos articulo) {
        if (articulo.getTituloArticulo() == null || articulo.getTituloArticulo().isBlank()) {
            return ResponseEntity.badRequest().body("El título del artículo es obligatorio");
        }
        if (articulo.getContenidoArticulo() == null || articulo.getContenidoArticulo().isBlank()) {
            return ResponseEntity.badRequest().body("El contenido del artículo es obligatorio");
        }
        if (articulo.getAutorArticulo() == null || articulo.getAutorArticulo().isBlank()) {
            return ResponseEntity.badRequest().body("El autor del artículo es obligatorio");
        }
        try {
            aS.insertar(articulo);
            return new ResponseEntity<>(articulo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        aS.eliminar(id);
        return ResponseEntity.ok("Artículo con ID " + id + " eliminado correctamente");
    }
}
