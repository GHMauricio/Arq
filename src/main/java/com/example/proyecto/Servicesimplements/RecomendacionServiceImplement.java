package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.RecomendacionDTO;
import com.example.proyecto.Entities.Recomendacion;
import com.example.proyecto.Entities.Usuario;
import com.example.proyecto.Repositories.RecomendacionRepository;
import com.example.proyecto.Repositories.UsuarioRepository;
import com.example.proyecto.Servicesinterfaces.IRecomendacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecomendacionServiceImplement implements IRecomendacionService {

    @Autowired
    private RecomendacionRepository rR;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void insertar(RecomendacionDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Recomendacion r = new Recomendacion();
        r.setIdRecomendacion(dto.getIdRecomendacion());
        r.setUsuario(usuario);
        r.setFechaEnvio(dto.getFechaEnvio() != null ? dto.getFechaEnvio() : LocalDate.now());
        r.setEstadoRecomendacion(dto.getEstadoRecomendacion());

        rR.save(r);
    }

    @Override
    public List<RecomendacionDTO> listar() {
        return rR.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecomendacionDTO> listarPorUsuario(Long idUsuario) {
        return rR.findByUsuarioIdUsuario(idUsuario).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        rR.deleteById(id);
    }

    @Override
    public void update(RecomendacionDTO dto) {
        Recomendacion r = rR.findById(dto.getIdRecomendacion())
                .orElseThrow(() -> new RuntimeException("Recomendación no encontrada"));

        r.setFechaEnvio(dto.getFechaEnvio());
        r.setEstadoRecomendacion(dto.getEstadoRecomendacion());

        if (dto.getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            r.setUsuario(usuario);
        }

        rR.save(r);
    }

    private RecomendacionDTO entityToDto(Recomendacion r) {
        RecomendacionDTO dto = new RecomendacionDTO();
        dto.setIdRecomendacion(r.getIdRecomendacion());
        dto.setFechaEnvio(r.getFechaEnvio());
        dto.setEstadoRecomendacion(r.getEstadoRecomendacion());
        if (r.getUsuario() != null) {
            dto.setIdUsuario(r.getUsuario().getIdUsuario());
        }
        return dto;
    }
}
