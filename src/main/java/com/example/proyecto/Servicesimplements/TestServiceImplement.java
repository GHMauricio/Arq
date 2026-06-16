package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.TestDTO;
import com.example.proyecto.Entities.Test;
import com.example.proyecto.Entities.Usuario;
import com.example.proyecto.Repositories.DetalleTestRepository;
import com.example.proyecto.Repositories.TestRepository;
import com.example.proyecto.Repositories.UsuarioRepository;
import com.example.proyecto.Servicesinterfaces.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestServiceImplement implements ITestService {

    @Autowired
    private TestRepository tR;

    @Autowired
    private UsuarioRepository uR;

    @Autowired
    private DetalleTestRepository dtR;

    @Override
    public void insertar(TestDTO dto) {
        Usuario usuario = uR.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("No se puede registrar el test: El usuario no existe."));

        Test test = new Test();
        test.setUsuario(usuario);
        test.setFechaTest(dto.getFechaTest() != null ? dto.getFechaTest() : LocalDate.now());
        test.setEstadoEmocional(dto.getEstadoEmocional());
        test.setNotasTest(dto.getNotasTest());
        test.setPuntajeTest(dto.getPuntajeTest());

        tR.save(test);
    }

    @Override
    public void update(TestDTO dto) {
        Test existente = tR.findById(dto.getIdTest())
                .orElseThrow(() -> new RuntimeException("Test no encontrado con ID: " + dto.getIdTest()));

        existente.setFechaTest(dto.getFechaTest());
        existente.setEstadoEmocional(dto.getEstadoEmocional());
        existente.setNotasTest(dto.getNotasTest());
        existente.setPuntajeTest(dto.getPuntajeTest());

        if (dto.getIdUsuario() != null) {
            Usuario usuario = uR.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));
            existente.setUsuario(usuario);
        }

        tR.save(existente);
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
    @Transactional
    public void eliminar(Long id) {
        if (tR.existsById(id)) {
            dtR.deleteByTestIdTest(id);
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
