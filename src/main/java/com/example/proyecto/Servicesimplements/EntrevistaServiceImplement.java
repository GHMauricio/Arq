package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.EntrevistaDTO;
import com.example.proyecto.Entities.Entrevista;
import com.example.proyecto.Entities.Recomendacion;
import com.example.proyecto.Repositories.EntrevistaRepository;
import com.example.proyecto.Repositories.RecomendacionRepository;
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
    private RecomendacionRepository rR;

    @Override
    public void insertar(EntrevistaDTO dto) {
        Recomendacion recomendacion = rR.findById(dto.getIdRecomendacion())
                .orElseThrow(() -> new RuntimeException("Recomendación no encontrada con ID: " + dto.getIdRecomendacion()));

        Entrevista entrevista = new Entrevista();
        entrevista.setRecomendacion(recomendacion);
        entrevista.setFechaEntrevista(dto.getFechaEntrevista());
        entrevista.setTemaEntrevista(dto.getTemaEntrevista());
        entrevista.setComentarioEntrevista(dto.getComentarioEntrevista());

        eR.save(entrevista);
    }

    @Override
    public void update(EntrevistaDTO dto) {
        Entrevista existente = eR.findById(dto.getIdEntrevista())
                .orElseThrow(() -> new RuntimeException("Entrevista no encontrada con ID: " + dto.getIdEntrevista()));

        existente.setFechaEntrevista(dto.getFechaEntrevista());
        existente.setTemaEntrevista(dto.getTemaEntrevista());
        existente.setComentarioEntrevista(dto.getComentarioEntrevista());

        if (dto.getIdRecomendacion() != null) {
            Recomendacion recomendacion = rR.findById(dto.getIdRecomendacion())
                    .orElseThrow(() -> new RuntimeException("Recomendación no encontrada con ID: " + dto.getIdRecomendacion()));
            existente.setRecomendacion(recomendacion);
        }

        eR.save(existente);
    }

    @Override
    public List<EntrevistaDTO> listar() {
        return eR.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EntrevistaDTO listarId(Long idEntrevista) {
        return eR.findById(idEntrevista)
                .map(this::entityToDto)
                .orElse(null);
    }
    
    @Override
    public List<EntrevistaDTO> listarPorRecomendacion(Long idRecomendacion) {
        return eR.findByRecomendacionIdRecomendacion(idRecomendacion).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntrevistaDTO> listarPorTema(String temaEntrevista) {
        return eR.listarPorTema(temaEntrevista).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        if (eR.existsById(id)) {
            eR.deleteById(id);
        } else {
            throw new RuntimeException("Entrevista no encontrada con ID: " + id);
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
