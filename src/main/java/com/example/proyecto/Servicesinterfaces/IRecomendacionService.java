package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.RecomendacionDTO;

import java.util.List;

public interface IRecomendacionService {

        void insertar(RecomendacionDTO dto);
        List<RecomendacionDTO> listar();
        List<RecomendacionDTO> listarPorUsuario(Long idUsuario);
        void eliminar(Long id);
        void update(RecomendacionDTO dto);
}
