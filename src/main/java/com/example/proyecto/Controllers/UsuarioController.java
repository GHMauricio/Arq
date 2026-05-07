package com.example.proyecto.Controllers;

import com.example.proyecto.DTOs.UsuarioDTO;
import com.example.proyecto.DTOs.UsuarioRegistroDTO;
import com.example.proyecto.Entities.Usuario;
import com.example.proyecto.Servicesinterfaces.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Usuarios-general")

public class UsuarioController {

    @Autowired
    private IUsuarioService uS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<UsuarioDTO> listar(){
        return uS.listar();
    }

    @GetMapping("/usuarios-general")
    public ResponseEntity<String> getUsuariosGeneral() {
        return ResponseEntity.ok("Acceso concedido: lista de usuarios");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PADRE')")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable("id") Long id) {
        try {
            UsuarioDTO dto = uS.listarId(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/Registrar")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> registroUsuario(@Valid @RequestBody UsuarioRegistroDTO dto) {
        try {
            uS.insertar(dto);
            return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        try {
            uS.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
