package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.DetalleTestDTO;
import com.example.proyecto.Entities.DetallesTest;
import com.example.proyecto.Servicesinterfaces.IDetalleTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DetalleTest-general")
@CrossOrigin(origins = "*")
public class DetalleTestController {

    @Autowired
    private IDetalleTestService dtS;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> registrar(@RequestBody DetallesTest detalle) {
        try {
            if (detalle.getPregunta() == null || detalle.getPregunta().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("La pregunta del detalle es obligatoria.");
            }

            if (detalle.getRespuesta() == null || detalle.getRespuesta().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("La respuesta del detalle es obligatoria.");
            }

            if (detalle.getPregunta().length() > 255) {
                return ResponseEntity.badRequest().body("La pregunta no puede exceder los 255 caracteres.");
            }

            dtS.insertar(detalle);
            return new ResponseEntity<>(detalle, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<DetalleTestDTO> listarTodo() {
        return dtS.listar();
    }

    @GetMapping("/Test/{idTest}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public List<DetalleTestDTO> listar(@PathVariable Long idTest) {
        return dtS.listarPorTest(idTest);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> modificar(@RequestBody DetallesTest detalle) {
        try {
            if (detalle.getIdDetalleTest() == null) {
                return ResponseEntity.badRequest().body("El ID del detalle es obligatorio para modificar.");
            }

            if (detalle.getPregunta() == null || detalle.getPregunta().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("La pregunta del detalle es obligatoria.");
            }

            if (detalle.getRespuesta() == null || detalle.getRespuesta().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("La respuesta del detalle es obligatoria.");
            }

            dtS.insertar(detalle);
            return new ResponseEntity<>(detalle, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            dtS.eliminar(id);
            return ResponseEntity.ok("El detalle de test con ID " + id + " fue eliminado correctamente del sistema.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.DetalleTestDTO;
import com.example.proyecto.Entities.DetallesTest;
import com.example.proyecto.Servicesinterfaces.IDetalleTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DetalleTest-general")
@CrossOrigin(origins = "*")
public class DetalleTestController {

    @Autowired
    private IDetalleTestService dtS;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<?> registrar(@RequestBody DetallesTest detalle) {
        try {
            if (detalle.getPregunta() == null || detalle.getPregunta().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("La pregunta del detalle es obligatoria.");
            }

            if (detalle.getRespuesta() == null || detalle.getRespuesta().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("La respuesta del detalle es obligatoria.");
            }

            if (detalle.getPregunta().length() > 255) {
                return ResponseEntity.badRequest().body("La pregunta no puede exceder los 255 caracteres.");
            }

            dtS.insertar(detalle);
            return new ResponseEntity<>(detalle, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<DetalleTestDTO> listarTodo() {
        return dtS.listar();
    }

    @GetMapping("/Test/{idTest}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public List<DetalleTestDTO> listar(@PathVariable Long idTest) {
        return dtS.listarPorTest(idTest);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> modificar(@RequestBody DetallesTest detalle) {
        try {
            if (detalle.getIdDetalleTest() == null) {
                return ResponseEntity.badRequest().body("El ID del detalle es obligatorio para modificar.");
            }

            if (detalle.getPregunta() == null || detalle.getPregunta().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("La pregunta del detalle es obligatoria.");
            }

            if (detalle.getRespuesta() == null || detalle.getRespuesta().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("La respuesta del detalle es obligatoria.");
            }

            dtS.insertar(detalle);
            return new ResponseEntity<>(detalle, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            dtS.eliminar(id);
            return ResponseEntity.ok("El detalle de test con ID " + id + " fue eliminado correctamente del sistema.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
