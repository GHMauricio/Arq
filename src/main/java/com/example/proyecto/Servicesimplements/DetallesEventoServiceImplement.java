package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.DetallesEventoDTO;
import com.example.proyecto.Entities.DetallesEvento;
import com.example.proyecto.Entities.Eventos;
import com.example.proyecto.Repositories.DetallesEventoRepository;
import com.example.proyecto.Repositories.EventoRepository;
import com.example.proyecto.Servicesinterfaces.IDetallesEventosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetallesEventoServiceImplement implements IDetallesEventosService {

    @Autowired
    private DetallesEventoRepository deR;

    @Autowired
    private EventoRepository eR;

    @Override
    public void insertar(DetallesEventoDTO dto) {
        Eventos evento = eR.findById(dto.getIdEvento())
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + dto.getIdEvento()));

        DetallesEvento detalle = new DetallesEvento();
        detalle.setEvento(evento);
        detalle.setActividad(dto.getActividad());
        detalle.setResponsable(dto.getResponsable());
        detalle.setHoraInicio(dto.getHoraInicio());
        detalle.setHoraFin(dto.getHoraFin());

        deR.save(detalle);
    }

    @Override
    public void update(DetallesEventoDTO dto) {
        DetallesEvento existente = deR.findById(dto.getIdDetalleEvento())
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado con ID: " + dto.getIdDetalleEvento()));

        existente.setActividad(dto.getActividad());
        existente.setResponsable(dto.getResponsable());
        existente.setHoraInicio(dto.getHoraInicio());
        existente.setHoraFin(dto.getHoraFin());

        if (dto.getIdEvento() != null) {
            Eventos evento = eR.findById(dto.getIdEvento())
                    .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + dto.getIdEvento()));
            existente.setEvento(evento);
        }

        deR.save(existente);
    }

    @Override
    public List<DetallesEventoDTO> listar() {
        return deR.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DetallesEventoDTO> listarPorEvento(Long idEvento) {
        return deR.findByEventoIdEvento(idEvento).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DetallesEventoDTO> listarPorActividad(String actividad) {
        return deR.listarPorActividad(actividad).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        if (deR.existsById(id)) {
            deR.deleteById(id);
        } else {
            throw new RuntimeException("Detalle de evento no encontrado con ID: " + id);
        }
    }

    private DetallesEventoDTO entityToDto(DetallesEvento e) {
        DetallesEventoDTO dto = new DetallesEventoDTO();
        dto.setIdDetalleEvento(e.getIdDetalleEvento());
        dto.setActividad(e.getActividad());
        dto.setResponsable(e.getResponsable());
        dto.setHoraInicio(e.getHoraInicio());
        dto.setHoraFin(e.getHoraFin());
        dto.setIdEvento(e.getEvento().getIdEvento());
        return dto;
    }
}
