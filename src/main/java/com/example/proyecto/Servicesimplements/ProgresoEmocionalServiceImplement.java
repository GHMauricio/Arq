package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.ProgresoEmocionalDTO;
import com.example.proyecto.Entities.ProgresoEmocional;
import com.example.proyecto.Repositories.ProgresoEmocionalRepository;
import com.example.proyecto.Repositories.UsuarioRepository;
import com.example.proyecto.Servicesinterfaces.IProgresoEmocionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgresoEmocionalServiceImplement  implements IProgresoEmocionalService {

    @Autowired
    private ProgresoEmocionalRepository pR;

    @Autowired
    private UsuarioRepository uR;

    @Override
    public ProgresoEmocional guardarProgreso(ProgresoEmocional progreso) {

        if(progreso.getFechaProgreso().isAfter(LocalDate.now())){
            throw new RuntimeException("No puedes registrar un progreso con fecha futura.");
        }

        if(progreso.getUsuario()==null || !uR.existsById(progreso.getUsuario().getIdUsuario())){
            throw new RuntimeException("Ingrese un usuario valido.");
        }

        if(progreso.getPuntajeEmocional()<0|| progreso.getPuntajeEmocional()>100){
            throw new RuntimeException("Escriba un puntaje de 0 a 100.");
        }
        return pR.save(progreso);
    }

    @Override
    public List<ProgresoEmocionalDTO> listarProgresosDTO() {
        return pR.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProgresoEmocionalDTO obtenerProgreso(Long id) {
        return pR.findById(id)
                .map(this::entityToDto)
                .orElse(null);
    }

    @Override
    public List<ProgresoEmocionalDTO> listarProgresosPorUsuario(Long idUsuario) {
        return pR.findByUsuarioIdUsuario(idUsuario)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarProgreso(Long id) {
        if(pR.existsById(id)){
            pR.deleteById(id);
        }else{
            throw new RuntimeException("Este usuario no existe.");
        }

    }

    private ProgresoEmocionalDTO entityToDto(ProgresoEmocional progreso) {
        ProgresoEmocionalDTO dto = new ProgresoEmocionalDTO();
        dto.setIdProgreso(progreso.getIdProgreso());
        dto.setFechaProgreso(progreso.getFechaProgreso());
        dto.setEstadoEmocional(progreso.getEstadoEmocional());
        dto.setPuntajeEmocional(progreso.getPuntajeEmocional());
        dto.setComentariosProgreso(progreso.getComentariosProgreso());

        if (progreso.getUsuario() != null) {
            dto.setIdUsuario(progreso.getUsuario().getIdUsuario());
        }
        return dto;
    }
}
