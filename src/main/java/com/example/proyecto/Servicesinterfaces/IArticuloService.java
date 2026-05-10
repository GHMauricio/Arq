package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.ArticuloDTO;
import java.util.List;

public interface IArticuloService {
    void insertar(ArticuloDTO dto);
    void update(ArticuloDTO dto);
    List<ArticuloDTO> listar();
    List<ArticuloDTO> listarPorRecomendacion(Long idRecomendacion);
    List<ArticuloDTO> listarPorFechaPublicacionDescendente();
    void eliminar(Long id);
}
