package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.ArticuloDTO;
import com.example.proyecto.Entities.Articulos;
import com.example.proyecto.Entities.Recomendacion;
import com.example.proyecto.Repositories.ArticuloRepository;
import com.example.proyecto.Repositories.RecomendacionRepository;
import com.example.proyecto.Servicesinterfaces.IArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticuloServiceImplement implements IArticuloService {

    @Autowired
    private ArticuloRepository aR;

    @Autowired
    private RecomendacionRepository rR;

    @Override
    public void insertar(ArticuloDTO dto) {
        Recomendacion recomendacion = rR.findById(dto.getIdRecomendacion())
                .orElseThrow(() -> new RuntimeException("Recomendación no encontrada con ID: " + dto.getIdRecomendacion()));

        Articulos articulo = new Articulos();
        articulo.setRecomendacion(recomendacion);
        articulo.setTituloArticulo(dto.getTituloArticulo());
        articulo.setContenidoArticulo(dto.getContenidoArticulo());
        articulo.setCategoriaArticulo(dto.getCategoriaArticulo());
        articulo.setFechaPublicacion(dto.getFechaPublicacion());
        articulo.setAutorArticulo(dto.getAutorArticulo());

        aR.save(articulo);
    }

    @Override
    public void update(ArticuloDTO dto) {
        Articulos existente = aR.findById(dto.getIdArticulo())
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado con ID: " + dto.getIdArticulo()));

        existente.setTituloArticulo(dto.getTituloArticulo());
        existente.setContenidoArticulo(dto.getContenidoArticulo());
        existente.setCategoriaArticulo(dto.getCategoriaArticulo());
        existente.setFechaPublicacion(dto.getFechaPublicacion());
        existente.setAutorArticulo(dto.getAutorArticulo());

        if (dto.getIdRecomendacion() != null) {
            Recomendacion recomendacion = rR.findById(dto.getIdRecomendacion())
                    .orElseThrow(() -> new RuntimeException("Recomendación no encontrada con ID: " + dto.getIdRecomendacion()));
            existente.setRecomendacion(recomendacion);
        }

        aR.save(existente);
    }

    @Override
    public List<ArticuloDTO> listar() {
        return aR.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticuloDTO> listarPorRecomendacion(Long idRecomendacion) {
        return aR.findByRecomendacionIdRecomendacion(idRecomendacion).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticuloDTO> listarPorFechaPublicacionDescendente() {
        return aR.listarPorFechaPublicacionDescendente().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        if (aR.existsById(id)) {
            aR.deleteById(id);
        } else {
            throw new RuntimeException("No se encuentra el artículo, verifique el identificador");
        }
    }

    private ArticuloDTO entityToDto(Articulos a) {
        ArticuloDTO dto = new ArticuloDTO();
        dto.setIdArticulo(a.getIdArticulo());
        dto.setTituloArticulo(a.getTituloArticulo());
        dto.setContenidoArticulo(a.getContenidoArticulo());
        dto.setCategoriaArticulo(a.getCategoriaArticulo());
        dto.setFechaPublicacion(a.getFechaPublicacion());
        dto.setAutorArticulo(a.getAutorArticulo());
        if (a.getRecomendacion() != null) {
            dto.setIdRecomendacion(a.getRecomendacion().getIdRecomendacion());
        }
        return dto;
    }
}
