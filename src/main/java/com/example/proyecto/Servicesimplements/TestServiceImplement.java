package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.TestDTO;
import com.example.proyecto.Entities.Test;
import com.example.proyecto.Repositories.TestRepository;
import com.example.proyecto.Repositories.UsuarioRepository;
import com.example.proyecto.Servicesinterfaces.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestServiceImplement implements ITestService {

    @Autowired
    private TestRepository tR;

    @Autowired
    private UsuarioRepository uR;

    @Override
    public void insertar(Test test) {
        if (!uR.existsById(test.getUsuario().getIdUsuario())) {
            throw new RuntimeException("No se puede registrar el test: El usuario no existe.");
        }
        if (test.getFechaTest() == null) {
            test.setFechaTest(LocalDate.now());
        }
        tR.save(test);
    }

    @Override
    public List<TestDTO> listar() {
        return tR.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TestDTO> listarPorUsuario(Long idUsuario) {
        return tR.findByUsuarioIdUsuarioOrderByFechaTestDesc(idUsuario).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TestDTO> listarPorPuntajeAscendente() {
        return tR.listarPorPuntajeAscendente().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        if (tR.existsById(id)) {
            tR.deleteById(id);
        } else {
            throw new RuntimeException("No encontramos ese test, intente de nuevo");
        }
    }

    private TestDTO entityToDto(Test t) {
        TestDTO dto = new TestDTO();
        dto.setIdTest(t.getIdTest());
        dto.setFechaTest(t.getFechaTest());
        dto.setEstadoEmocional(t.getEstadoEmocional());
        dto.setNotasTest(t.getNotasTest());
        dto.setPuntajeTest(t.getPuntajeTest());
        if (t.getUsuario() != null) {
            dto.setIdUsuario(t.getUsuario().getIdUsuario());
        }
        return dto;
    }
}
