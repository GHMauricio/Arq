package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.ProgresoEmocionalDTO;
import com.example.proyecto.Entities.ProgresoEmocional;
import com.example.proyecto.Entities.Usuario;
import com.example.proyecto.Repositories.ProgresoEmocionalRepository;
import com.example.proyecto.Repositories.UsuarioRepository;
import com.example.proyecto.Servicesinterfaces.IProgresoEmocionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgresoEmocionalServiceImplement implements IProgresoEmocionalService {

    @Autowired
    private ProgresoEmocionalRepository pR;

    @Autowired
    private UsuarioRepository uR;

    @Override
    public void guardarProgreso(ProgresoEmocionalDTO dto) {
        if (dto.getFechaProgreso() == null) {
            throw new RuntimeException("La fecha de progreso no puede ser nula.");
        }
        if (dto.getFechaProgreso().isAfter(LocalDate.now())) {
            throw new RuntimeException("No puedes registrar un progreso con fecha futura.");
        }
        if (dto.getIdUsuario() == null) {
            throw new RuntimeException("Ingrese un usuario válido.");
        }

        Usuario usuario = uR.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));

        if (dto.getPuntajeEmocional() == null || dto.getPuntajeEmocional() < 0 || dto.getPuntajeEmocional() > 100) {
            throw new RuntimeException("Escriba un puntaje de 0 a 100.");
        }

        ProgresoEmocional progreso = new ProgresoEmocional();
        progreso.setUsuario(usuario);
        progreso.setFechaProgreso(dto.getFechaProgreso());
        progreso.setEstadoEmocional(dto.getEstadoEmocional());
        progreso.setComentariosProgreso(dto.getComentariosProgreso());
        progreso.setPuntajeEmocional(dto.getPuntajeEmocional());

        pR.save(progreso);
    }

    @Override
    public List<ProgresoEmocionalDTO> listarProgresosDTO() {
        return pR.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProgresoEmocionalDTO obtenerProgreso(Long id) {
        return pR.findById(id)
                .map(this::entityToDto)
                .orElse(null);
    }

    @Override
    public List<ProgresoEmocionalDTO> listarProgresosPorUsuario(Long idUsuario) {
        return pR.findByUsuarioIdUsuario(idUsuario).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarProgreso(Long id) {
        if (pR.existsById(id)) {
            pR.deleteById(id);
        } else {
            throw new RuntimeException("Progreso emocional no encontrado con ID: " + id);
        }
    }

    private ProgresoEmocionalDTO entityToDto(ProgresoEmocional progreso) {
        ProgresoEmocionalDTO dto = new ProgresoEmocionalDTO();
        dto.setIdProgreso(progreso.getIdProgreso());
        dto.setFechaProgreso(progreso.getFechaProgreso());
        dto.setEstadoEmocional(progreso.getEstadoEmocional());
        dto.setPuntajeEmocional(progreso.getPuntajeEmocional());
        dto.setComentariosProgreso(progreso.getComentariosProgreso());
        if (progreso.getUsuario() != null) {
            dto.setIdUsuario(progreso.getUsuario().getIdUsuario());
        }
        return dto;
    }
}
