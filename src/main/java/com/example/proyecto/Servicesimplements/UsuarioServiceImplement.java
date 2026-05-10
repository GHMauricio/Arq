package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.UsuarioDTO;
import com.example.proyecto.DTOs.UsuarioRegistroDTO;
import com.example.proyecto.Entities.Role;
import com.example.proyecto.Entities.Usuario;
import com.example.proyecto.Repositories.UsuarioRepository;
import com.example.proyecto.Servicesinterfaces.IUsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImplement implements IUsuarioService {

    @Autowired
    private UsuarioRepository uR;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UsuarioDTO entityToDto(Usuario usuario){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setCorreoUsuario(usuario.getCorreoUsuario());
        dto.setNombreUsuario(usuario.getNombreUsuario());

        if(usuario.getRolUsuario() == null|| usuario.getRolUsuario().isEmpty()){
            dto.setRolUsuario("SIN_ROL_ASIGNADO");
        }else {
            // ✅ Convertir lista de roles a String separado por comas
            if (usuario.getRolUsuario() != null) {
                String roles = usuario.getRolUsuario()
                        .stream()
                        .map(role -> role.getRol()) // suponiendo que Role tiene getRol()
                        .collect(Collectors.joining(","));
                dto.setRolUsuario(roles);
            }
        }

        dto.setFechaRegistro(usuario.getFechaRegistro());
        dto.setNacimientoUsuario(usuario.getNacimientoUsuario());
        dto.setNacimientoAdolescente(usuario.getNacimientoAdolescente());
        dto.setGeneroAdolescente(usuario.getGeneroAdolescente());
        dto.setInteresesAdolescente(usuario.getInteresesAdolescente());
        dto.setCantidadAdolescente(usuario.getCantidadAdolescente());
        return dto;
    }

    @Override
    @Transactional
    public void insertar(UsuarioRegistroDTO dto) {
        if (uR.findByCorreoUsuario(dto.getCorreoUsuario()) != null) {
            throw new RuntimeException("El correo ya está registrado.");
        }

        if (dto.getCantidadAdolescente() != null && dto.getCantidadAdolescente() < 0) {
            throw new RuntimeException("La cantidad de adolescentes no puede ser negativa.");
        }

        // 2. MAPEO DE DTO A ENTIDAD
        Usuario u = new Usuario();
        u.setNombreUsuario(dto.getNombreUsuario());
        u.setCorreoUsuario(dto.getCorreoUsuario());
        u.setNacimientoUsuario(dto.getNacimientoUsuario());
        u.setCantidadAdolescente(dto.getCantidadAdolescente());
        u.setNacimientoAdolescente(dto.getNacimientoAdolescente());
        u.setGeneroAdolescente(dto.getGeneroAdolescente());
        u.setInteresesAdolescente(dto.getInteresesAdolescente());
        u.setEnabled(true);
        u.setFechaRegistro(dto.getFechaRegistro() != null ? dto.getFechaRegistro() : LocalDate.now());
        u.setContrasenaUsuario(passwordEncoder.encode(dto.getContrasenaUsuario()));

        Role nuevoRol = new Role();
        nuevoRol.setRol(dto.getRolUsuario());
        nuevoRol.setUser(u);

        List<Role> roles = new ArrayList<>();
        roles.add(nuevoRol);
        u.setRolUsuario(roles);

        uR.save(u);
    }

    @Override
    @Transactional
    public void update(UsuarioRegistroDTO dto) {
        Usuario u = uR.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));

        u.setNombreUsuario(dto.getNombreUsuario());
        u.setNacimientoUsuario(dto.getNacimientoUsuario());
        u.setCantidadAdolescente(dto.getCantidadAdolescente());
        u.setNacimientoAdolescente(dto.getNacimientoAdolescente());
        u.setGeneroAdolescente(dto.getGeneroAdolescente());
        u.setInteresesAdolescente(dto.getInteresesAdolescente());

        if (dto.getCorreoUsuario() != null && !dto.getCorreoUsuario().equals(u.getCorreoUsuario())) {
            Usuario existente = uR.findByCorreoUsuario(dto.getCorreoUsuario());
            if (existente != null && !existente.getIdUsuario().equals(u.getIdUsuario())) {
                throw new RuntimeException("El correo ya está registrado.");
            }
            u.setCorreoUsuario(dto.getCorreoUsuario());
        }

        if (dto.getContrasenaUsuario() != null && !dto.getContrasenaUsuario().isBlank()) {
            u.setContrasenaUsuario(passwordEncoder.encode(dto.getContrasenaUsuario()));
        }

        if (dto.getFechaRegistro() != null) {
            u.setFechaRegistro(dto.getFechaRegistro());
        }

        if (dto.getRolUsuario() != null && !dto.getRolUsuario().isBlank()) {
            if (u.getRolUsuario() != null && !u.getRolUsuario().isEmpty()) {
                u.getRolUsuario().get(0).setRol(dto.getRolUsuario());
            } else {
                Role rol = new Role();
                rol.setRol(dto.getRolUsuario());
                rol.setUser(u);
                u.getRolUsuario().add(rol);
            }
        }

        uR.save(u);
    }



    @Override
    public List<UsuarioDTO> listar() {
        return uR.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void eliminar(Long idUsuario) {
        if (!uR.existsById(idUsuario)) {
            throw new RuntimeException("No se puede eliminar: El usuario no existe.");
        }
        uR.deleteById(idUsuario);
    }

    @Override
    public UsuarioDTO listarId(Long idUsuario) {
        return uR.findById(idUsuario)
                .map(this::entityToDto)
                .orElse(null);
    }

    @Override
    public List<Usuario> buscarPorIntereses(String intereses) {
        return List.of();
    }
}
