package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.ProgresoEmocionalDTO;
import com.example.proyecto.Entities.ProgresoEmocional;

import java.util.List;

public interface IProgresoEmocionalService {
    ProgresoEmocional guardarProgreso(ProgresoEmocional progreso);
    List<ProgresoEmocionalDTO> listarProgresosDTO();
    ProgresoEmocionalDTO obtenerProgreso(Long id);
    List<ProgresoEmocionalDTO> listarProgresosPorUsuario( Long idUsuario);
    void eliminarProgreso (Long id);
}
