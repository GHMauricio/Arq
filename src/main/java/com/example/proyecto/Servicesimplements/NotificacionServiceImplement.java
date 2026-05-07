package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.NotificacionDTO;
import com.example.proyecto.Entities.Notificacion;
import com.example.proyecto.Repositories.NotificacionRepository;
import com.example.proyecto.Repositories.UsuarioRepository;
import com.example.proyecto.Servicesinterfaces.INotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificacionServiceImplement  implements INotificacionService {

    @Autowired
    private NotificacionRepository nR;

    @Autowired
    private UsuarioRepository uR;

    @Override
    public Notificacion guardar(Notificacion notificacion) {

        // Validamos que el usuario exista antes de enviarle una notificación
        if (!uR.existsById(notificacion.getUsuario().getIdUsuario())) {
            throw new RuntimeException("No se puede crear la notificación: El usuario no existe.");
        }

        // Asignamos la fecha y hora actual exacta si no viene en el JSON
        if (notificacion.getFechaEnvio() == null) {
            notificacion.setFechaEnvio(LocalDateTime.now());
        }
        return nR.save(notificacion);
    }

    @Override
    public List<NotificacionDTO> listarPorUsuario(Long idUsuario) {
        return nR.findByUsuarioIdUsuarioOrderByFechaEnvioDesc(idUsuario).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void marcarComoLeido(Long idUsuario) {
        nR.marcarComoLeido(idUsuario);
    }

    @Override
    public void eliminar(Long id) {
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
