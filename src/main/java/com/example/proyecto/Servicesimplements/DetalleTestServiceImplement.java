package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.DetalleTestDTO;
import com.example.proyecto.Entities.DetallesTest;
import com.example.proyecto.Entities.Test;
import com.example.proyecto.Repositories.DetalleTestRepository;
import com.example.proyecto.Repositories.TestRepository;
import com.example.proyecto.Servicesinterfaces.IDetalleTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleTestServiceImplement implements IDetalleTestService {

    @Autowired
    private DetalleTestRepository dtR;

    @Autowired
    private TestRepository tR;

    @Override
    public void insertar(DetalleTestDTO dto) {
        Test test = tR.findById(dto.getIdTest())
                .orElseThrow(() -> new RuntimeException("Test no encontrado con ID: " + dto.getIdTest()));

        DetallesTest detalle = new DetallesTest();
        detalle.setTest(test);
        detalle.setPregunta(dto.getPregunta());
        detalle.setRespuesta(dto.getRespuesta());
        detalle.setObservacion(dto.getObservacion());

        dtR.save(detalle);
    }

    @Override
    public void update(DetalleTestDTO dto) {
        DetallesTest existente = dtR.findById(dto.getIdDetalleTest())
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado con ID: " + dto.getIdDetalleTest()));

        existente.setPregunta(dto.getPregunta());
        existente.setRespuesta(dto.getRespuesta());
        existente.setObservacion(dto.getObservacion());

        if (dto.getIdTest() != null) {
            Test test = tR.findById(dto.getIdTest())
                    .orElseThrow(() -> new RuntimeException("Test no encontrado con ID: " + dto.getIdTest()));
            existente.setTest(test);
        }

        dtR.save(existente);
    }

    @Override
    public List<DetalleTestDTO> listar() {
        return dtR.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DetalleTestDTO> listarPorTest(Long idTest) {
        return dtR.findByTestIdTest(idTest).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DetalleTestDTO> listarPorRespuesta(String respuesta) {
        return dtR.listarPorRespuesta(respuesta).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        if (dtR.existsById(id)) {
            dtR.deleteById(id);
        } else {
            throw new RuntimeException("Este test no tiene detalles, intente con otro test");
        }
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
