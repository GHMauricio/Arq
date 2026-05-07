package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.DetallesEventoDTO;
import com.example.proyecto.Entities.DetallesEvento;
import com.example.proyecto.Entities.Entrevista;
import com.example.proyecto.Repositories.DetallesEventoRepository;
import com.example.proyecto.Servicesinterfaces.IDetallesEventosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetallesEventoServiceImplement implements IDetallesEventosService {

    @Autowired
    private DetallesEventoRepository deR;

    @Override
    public void insertar(DetallesEvento detalle) {
        deR.save(detalle);
    }

    @Override
    public List<DetallesEventoDTO> listarPorEvento(Long idEvento) {
        return deR.findByEventoIdEvento(idEvento).stream()
                .map(d -> {
                    DetallesEventoDTO dto = new DetallesEventoDTO();
                    dto.setIdDetalleEvento(d.getIdDetalleEvento());
                    dto.setIdEvento(d.getEvento().getIdEvento());
                    dto.setActividad(d.getActividad());
                    dto.setResponsable(d.getResponsable());
                    dto.setHoraInicio(d.getHoraInicio());
                    dto.setHoraFin(d.getHoraFin());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        if(deR.existsById(id)){
            deR.deleteById(id);
        }else{
            throw new RuntimeException("Ingrese un id valido");
        }
    }

    @Override
    public List<DetallesEventoDTO> listar() {
        return deR.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    private DetallesEventoDTO entityToDto(DetallesEvento e){
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
