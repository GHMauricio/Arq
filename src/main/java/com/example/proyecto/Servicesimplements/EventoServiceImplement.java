package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.EventosDTO;
import com.example.proyecto.Entities.Eventos;
import com.example.proyecto.Repositories.EventoRepository;
import com.example.proyecto.Repositories.UsuarioRepository;
import com.example.proyecto.Servicesinterfaces.IEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoServiceImplement implements IEventoService {

    @Autowired
    private EventoRepository eR;

    @Autowired
    private UsuarioRepository uR;

    @Override
    public List<EventosDTO> listarEventosDTO() {
        List<Eventos> lista = eR.findAll(); // Trae las entidades normales
        // Aquí tendrías que usar un Stream o un bucle para pasar de Eventos a EventosDTO
        return lista.stream().map(e -> new EventosDTO(
                e.getIdEvento(), e.getTituloEvento(), e.getFechaInicio(), e.getFechaFin(), e.getDescripcionEvento(), e.getUsuario().getIdUsuario()
        )).collect(Collectors.toList());
    }

    @Override
    public Eventos guardar(Eventos evento) {
        // 1. Validar Usuario
        if (!uR.existsById(evento.getUsuario().getIdUsuario())) {
            throw new RuntimeException("Error: El usuario organizador no existe.");
        }

        // 2. Validación de fechas (Lógica de negocio extra)
        if (evento.getFechaFin().isBefore(evento.getFechaInicio())) {
            throw new RuntimeException("Error: La fecha de fin no puede ser anterior a la de inicio.");
        }

        return eR.save(evento);
    }

    @Override
    public List<EventosDTO> listarPorUsuario(Long idUsuario) {
        return eR.findByUsuarioIdUsuario(idUsuario).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        eR.deleteById(id);
    }

    @Override
    public List<EventosDTO> listarTodo() {
        return eR.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    private EventosDTO entityToDto(Eventos e) {
        EventosDTO dto = new EventosDTO();
        dto.setIdEvento(e.getIdEvento());
        dto.setTituloEvento(e.getTituloEvento());
        dto.setDescripcionEvento(e.getDescripcionEvento());
        dto.setFechaInicio(e.getFechaInicio());
        dto.setFechaFin(e.getFechaFin());
        dto.setTipoEvento(e.getTipoEvento());
        if (e.getUsuario() != null) {
            dto.setIdUsuario(e.getUsuario().getIdUsuario());
        }
        return dto;

    }
}
