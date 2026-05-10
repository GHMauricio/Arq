package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.ArticuloDTO;
import com.example.proyecto.Servicesinterfaces.IArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("Articulos-general")
@CrossOrigin(origins = "*")
public class ArticuloController {

    @Autowired
    private IArticuloService aS;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> registrar(@RequestBody ArticuloDTO dto) {
        if (dto.getTituloArticulo() == null || dto.getTituloArticulo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El título del artículo es obligatorio.");
        }
        if (dto.getContenidoArticulo() == null || dto.getContenidoArticulo().length() < 10) {
            return ResponseEntity.badRequest().body("El contenido del artículo debe tener al menos 10 caracteres.");
        }
        if (dto.getFechaPublicacion() != null && dto.getFechaPublicacion().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de publicación no puede ser una fecha futura.");
        }

        try {
            if (articulo.getTituloArticulo() == null || articulo.getTituloArticulo().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("El título del artículo es obligatorio.");
            }

            if (articulo.getContenidoArticulo() == null || articulo.getContenidoArticulo().length() < 10) {
                return ResponseEntity.badRequest().body("El contenido del artículo debe tener al menos 10 caracteres.");
            }

            if (articulo.getFechaPublicacion() != null && articulo.getFechaPublicacion().isAfter(LocalDate.now())) {
                return ResponseEntity.badRequest().body("La fecha de publicación no puede ser una fecha futura.");
            }

            aS.insertar(articulo);
            return new ResponseEntity<>(articulo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(aS.listar());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/Recomendacion/{idRecomendacion}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> listarPorRecomendacion(@PathVariable Long idRecomendacion) {
        try {
            return ResponseEntity.ok(aS.listarPorRecomendacion(idRecomendacion));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/fecha-descendente")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> listarPorFechaPublicacionDescendente() {
        List<ArticuloDTO> lista = aS.listarPorFechaPublicacionDescendente();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay artículos registrados");
        }
        return ResponseEntity.ok(lista);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> modificar(@RequestBody ArticuloDTO dto) {
        if (dto.getIdArticulo() == null) {
            return ResponseEntity.badRequest().body("El ID del artículo es obligatorio para modificar.");
        }
        if (dto.getTituloArticulo() == null || dto.getTituloArticulo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El título del artículo es obligatorio.");
        }
        if (dto.getContenidoArticulo() == null || dto.getContenidoArticulo().length() < 10) {
            return ResponseEntity.badRequest().body("El contenido del artículo debe tener al menos 10 caracteres.");
        }
        if (dto.getFechaPublicacion() != null && dto.getFechaPublicacion().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de publicación no puede ser una fecha futura.");
        }

        try {
            if (articulo.getIdArticulo() == null) {
                return ResponseEntity.badRequest().body("El ID del artículo es obligatorio para modificar.");
            }

            if (articulo.getAutorArticulo() == null || articulo.getAutorArticulo().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("El autor del artículo es obligatorio.");
            }

            if (articulo.getCategoriaArticulo() == null || articulo.getCategoriaArticulo().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("La categoría del artículo es obligatoria.");
            }

            aS.insertar(articulo);
            return new ResponseEntity<>(articulo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            aS.eliminar(id);
            return ResponseEntity.ok("El artículo con ID " + id + " fue eliminado correctamente del sistema.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
