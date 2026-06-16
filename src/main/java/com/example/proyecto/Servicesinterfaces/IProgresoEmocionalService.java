package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.ProgresoEmocionalDTO;
import java.util.List;

public interface IProgresoEmocionalService {
    void guardarProgreso(ProgresoEmocionalDTO dto);
    List<ProgresoEmocionalDTO> listarProgresosDTO();
    ProgresoEmocionalDTO obtenerProgreso(Long id);
    List<ProgresoEmocionalDTO> listarProgresosPorUsuario(Long idUsuario);
    void eliminarProgreso(Long id);
}
