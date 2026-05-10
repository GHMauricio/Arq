package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.ArticuloDTO;

import java.util.List;

public interface IArticuloService {
    public void insertar(Articulos articulo);
    public List<ArticuloDTO> listar();
    public List<ArticuloDTO> listarPorRecomendacion(Long idRecomendacion);

    public void eliminar(Long id);
}
