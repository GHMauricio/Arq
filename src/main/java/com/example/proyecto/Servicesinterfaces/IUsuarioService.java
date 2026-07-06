package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.UsuarioDTO;
import com.example.proyecto.DTOs.UsuarioRegistroDTO;
import com.example.proyecto.Entities.Usuario;

import java.util.List;

public interface IUsuarioService {

    void insertar(UsuarioRegistroDTO dto);
    void update(UsuarioRegistroDTO dto);
    List<UsuarioDTO> listar();
    List<UsuarioDTO> listarPorNacimientoAdolescenteAscendente();
    void eliminar(Long idUsuario);
    UsuarioDTO listarId(Long idUsuario);
    List<UsuarioAnioDTO> contabilizarAdolescentesPorAnio();
    List<UsuarioGeneroDTO> contabilizarUsuariosPorGenero();
    List<Usuario> buscarPorIntereses(String intereses);
}
