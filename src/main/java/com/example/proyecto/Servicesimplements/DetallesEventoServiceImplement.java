package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.EntrevistaDTO;
import com.example.proyecto.Entities.Entrevista;
import com.example.proyecto.Repositories.EntrevistaRepository;
import com.example.proyecto.Repositories.UsuarioRepository;
import com.example.proyecto.Servicesinterfaces.IEntrevistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntrevistaServiceImplement implements IEntrevistaService {
    @Autowired
    private EntrevistaRepository eR;

    @Autowired
    private EventoRepository eR;

    @Override
    public void insertar(Entrevista entrevista) {
        eR.save(entrevista);
    }

    @Override
    public List<EntrevistaDTO> listar() {
        return eR.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntrevistaDTO> listarPorRecomendacion(Long idRecomendacion) {
        return eR.findByRecomendacionIdRecomendacion(idRecomendacion).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        if(eR.existsById(id)){
            eR.deleteById(id);
        }else{
            throw new RuntimeException("No hay entrevista registrada con este id");
        }
    }

    private EntrevistaDTO entityToDto(Entrevista e) {
        EntrevistaDTO dto = new EntrevistaDTO();
        dto.setIdEntrevista(e.getIdEntrevista());
        dto.setFechaEntrevista(e.getFechaEntrevista());
        dto.setTemaEntrevista(e.getTemaEntrevista());
        dto.setComentarioEntrevista(e.getComentarioEntrevista());

        if (e.getRecomendacion() != null) {
            dto.setIdRecomendacion(e.getRecomendacion().getIdRecomendacion());
        }
        return dto;
    }
}
