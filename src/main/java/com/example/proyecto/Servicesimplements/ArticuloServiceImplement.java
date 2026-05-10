package com.example.proyecto.Servicesimplements;

import com.example.proyecto.DTOs.ArticuloDTO;
import com.example.proyecto.Entities.Articulos;
import com.example.proyecto.Repositories.ArticuloRepository;
import com.example.proyecto.Servicesinterfaces.IArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticuloServiceImplement implements IArticuloService {

    @Autowired
    private ArticuloRepository aR;

    @Override
    public void insertar(Articulos articulo) {
        if (articulo.getTituloArticulo() == null || articulo.getTituloArticulo().isBlank()) {
            throw new RuntimeException("El título del artículo es obligatorio");
        }
        if (articulo.getContenidoArticulo() == null || articulo.getContenidoArticulo().isBlank()) {
            throw new RuntimeException("El contenido del artículo es obligatorio");
        }
        if (articulo.getAutorArticulo() == null || articulo.getAutorArticulo().isBlank()) {
            throw new RuntimeException("El autor del artículo es obligatorio");
        }
        aR.save(articulo);
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
    public void eliminar(Long id) {
        if (aR.existsById(id)) {
            aR.deleteById(id);
        } else {
            throw new RuntimeException("No se encuentra el articulo, verifique el identificador");
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
