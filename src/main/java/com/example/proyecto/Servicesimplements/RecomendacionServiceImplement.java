package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.RecomendacionDTO;
import com.example.proyecto.Entities.Recomendacion;
import com.example.proyecto.Repositories.ArticuloRepository;
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

    @Override
    public void insertar(Recomendacion recomendacion) {
        if (recomendacion.getFechaEnvio() == null) {
            recomendacion.setFechaEnvio(LocalDate.now());
        }
        rR.save(recomendacion);
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
