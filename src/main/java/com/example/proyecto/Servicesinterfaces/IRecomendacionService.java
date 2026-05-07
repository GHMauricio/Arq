package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.RecomendacionDTO;
import com.example.proyecto.Entities.Recomendacion;

import java.util.List;

public interface IRecomendacionService {

        public void insertar(Recomendacion recomendacion);
        public List<RecomendacionDTO> listar();
        public List<RecomendacionDTO> listarPorUsuario(Long idUsuario);
        public void eliminar(Long id);

}
