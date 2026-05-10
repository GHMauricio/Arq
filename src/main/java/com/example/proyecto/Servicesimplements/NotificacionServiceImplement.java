package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.NotificacionDTO;
import com.example.proyecto.Entities.Notificacion;
import com.example.proyecto.Entities.Usuario;
import com.example.proyecto.Repositories.NotificacionRepository;
import com.example.proyecto.Repositories.UsuarioRepository;
import com.example.proyecto.Servicesinterfaces.INotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificacionServiceImplement implements INotificacionService {

    @Autowired
    private NotificacionRepository nR;

    @Autowired
    private UsuarioRepository uR;

    @Override
    public void guardar(NotificacionDTO dto) {
        Usuario usuario = uR.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));

        Notificacion notificacion = new Notificacion();
        notificacion.setMensajeNotificacion(dto.getMensajeNotificacion());
        notificacion.setFechaEnvio(dto.getFechaEnvio());
        notificacion.setLeido(dto.isLeido());
        notificacion.setUsuario(usuario);

        nR.save(notificacion);
    }

    @Override
    public List<NotificacionDTO> listar() {
        return nR.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificacionDTO> listarPorUsuario(Long idUsuario) {
        return nR.findByUsuarioIdUsuarioOrderByFechaEnvioDesc(idUsuario).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificacionDTO> listarLeidasPorAnio(int anio) {
        return nR.listarLeidasPorAnio(anio).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void marcarComoLeido(Long idUsuario) {
        nR.marcarComoLeido(idUsuario);
    }

    @Override
    public void actualizar(Long id, NotificacionDTO dto) {
        Notificacion existente = nR.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada con ID: " + id));

        existente.setMensajeNotificacion(dto.getMensajeNotificacion());
        existente.setFechaEnvio(dto.getFechaEnvio());
        existente.setLeido(dto.isLeido());

        if (dto.getIdUsuario() != null) {
            Usuario usuario = uR.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));
            existente.setUsuario(usuario);
        }

        nR.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        if (!nR.existsById(id)) {
            throw new RuntimeException("Notificación no encontrada con ID: " + id);
        }
        nR.deleteById(id);
    }

    private NotificacionDTO entityToDto(Notificacion n) {
        NotificacionDTO dto = new NotificacionDTO();
        dto.setIdNotificacion(n.getIdNotificacion());
        dto.setMensajeNotificacion(n.getMensajeNotificacion());
        dto.setFechaEnvio(n.getFechaEnvio());
        dto.setLeido(n.isLeido());
        dto.setIdUsuario(n.getUsuario().getIdUsuario());
        return dto;
    }
}
