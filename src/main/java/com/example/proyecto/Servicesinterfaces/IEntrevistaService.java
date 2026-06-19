package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.EntrevistaDTO;

import java.util.List;

public interface IEntrevistaService {
    void insertar(EntrevistaDTO dto);
    void update(EntrevistaDTO dto);
    List<EntrevistaDTO> listar();
    List<EntrevistaDTO> listarPorRecomendacion(Long idRecomendacion);
    List<EntrevistaDTO> listarPorTema(String temaEntrevista);
    void eliminar(Long id);
    EntrevistaDTO listarId(Long idEntrevista);
}
