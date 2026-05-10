package com.example.proyecto.Servicesimplements;
import com.example.proyecto.DTOs.EventosDTO;
import com.example.proyecto.Entities.Eventos;
import com.example.proyecto.Entities.Usuario;
import com.example.proyecto.Repositories.EventoRepository;
import com.example.proyecto.Repositories.UsuarioRepository;
import com.example.proyecto.Servicesinterfaces.IEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoServiceImplement implements IEventoService {

    @Autowired
    private EventoRepository eR;
    @Autowired
    private UsuarioRepository uR;

    @Override
    public EventosDTO guardar(EventosDTO dto) {
        if (dto.getTituloEvento() == null || dto.getTituloEvento().trim().isEmpty()) {
            throw new RuntimeException("El título del evento no puede estar vacío.");
        }

        if (dto.getTipoEvento() == null || dto.getTipoEvento().trim().isEmpty()) {
            throw new RuntimeException("El tipo de evento no puede estar vacío.");
        }

        if (dto.getFechaInicio().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("La fecha de inicio no puede ser en el futuro, ya que sería imposible realizarlo.");
        }

        if (dto.getFechaFin().isBefore(dto.getFechaInicio())) {
            throw new RuntimeException("La fecha de fin no puede ser anterior a la de inicio.");
        }

        Usuario usuario = uR.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("El usuario organizador no existe."));

        Eventos evento = new Eventos();
        evento.setUsuario(usuario);
        evento.setTituloEvento(dto.getTituloEvento());
        evento.setDescripcionEvento(dto.getDescripcionEvento());
        evento.setFechaInicio(dto.getFechaInicio());
        evento.setFechaFin(dto.getFechaFin());
        evento.setTipoEvento(dto.getTipoEvento());

        return entityToDto(eR.save(evento));
    }

    @Override
    public EventosDTO actualizar(Long id, EventosDTO dto) {
        Eventos evento = eR.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el evento con ID: " + id));

        if (dto.getTituloEvento() == null || dto.getTituloEvento().trim().isEmpty()) {
            throw new RuntimeException("El título del evento no puede estar vacío.");
        }

        if (dto.getFechaInicio().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("La fecha de inicio no puede ser en el futuro, ya que sería imposible realizarlo.");
        }

        if (dto.getFechaFin().isBefore(dto.getFechaInicio())) {
            throw new RuntimeException("La fecha de fin no puede ser anterior a la de inicio.");
        }

        evento.setTituloEvento(dto.getTituloEvento());
        evento.setDescripcionEvento(dto.getDescripcionEvento());
        evento.setFechaInicio(dto.getFechaInicio());
        evento.setFechaFin(dto.getFechaFin());
        evento.setTipoEvento(dto.getTipoEvento());

        return entityToDto(eR.save(evento));
    }

    @Override
    public List<EventosDTO> listarEventosDTO() {
        return eR.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventosDTO> listarPorUsuario(Long idUsuario) {
        return eR.findByUsuarioIdUsuario(idUsuario).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventosDTO> listarTodo() {
        return eR.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventosDTO> listarPorAnioDescendente() {
        return eR.findAllOrderByAnioDesc().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        if (!eR.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: no existe ningún evento con ID: " + id);
        }
        eR.deleteById(id);
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
