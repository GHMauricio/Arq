package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.DetalleTestDTO;
import com.example.proyecto.Entities.DetallesTest;
import com.example.proyecto.Repositories.DetalleTestRepository;
import com.example.proyecto.Servicesinterfaces.IDetalleTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class DetalleTestServiceImplement implements IDetalleTestService {
    @Autowired
    private DetalleTestRepository dtR;

    @Override
    public void insertar(DetallesTest detalle) {
        dtR.save(detalle);
    }

    @Override
    public List<DetalleTestDTO> listarPorTest(Long idTest) {
        return dtR.findByTestIdTest(idTest).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        if(dtR.existsById(id)){
            dtR.deleteById(id);
        }else{
            throw new RuntimeException("Este test no tiene detalles, intente con otro test");
        }
    }

    @Override
    public List<DetalleTestDTO> listar() {
        return dtR.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    private DetalleTestDTO entityToDto(DetallesTest d) {
        DetalleTestDTO dto = new DetalleTestDTO();
        dto.setIdDetalleTest(d.getIdDetalleTest());
        dto.setIdTest(d.getTest().getIdTest());
        dto.setPregunta(d.getPregunta());
        dto.setRespuesta(d.getRespuesta());
        dto.setObservacion(d.getObservacion());
        return dto;
    }
}
