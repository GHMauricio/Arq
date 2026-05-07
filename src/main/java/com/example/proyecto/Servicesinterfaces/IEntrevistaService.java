package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.EntrevistaDTO;
import com.example.proyecto.Entities.Entrevista;

import java.util.List;

public interface IEntrevistaService {
    public void insertar(Entrevista entrevista);
    public List<EntrevistaDTO> listar();
    public List<EntrevistaDTO> listarPorRecomendacion(Long idRecomendacion);
    public void eliminar(Long id);
}
