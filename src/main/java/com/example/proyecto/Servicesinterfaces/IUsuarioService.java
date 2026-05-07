package com.example.proyecto.Servicesinterfaces;

import com.example.proyecto.DTOs.UsuarioDTO;
import com.example.proyecto.DTOs.UsuarioRegistroDTO;
import com.example.proyecto.Entities.Usuario;

import java.util.List;

public interface IUsuarioService {

    public void insertar(UsuarioRegistroDTO dto);
    public List<UsuarioDTO> listar();
    public void eliminar(Long idUsuario);
    public UsuarioDTO listarId(Long idUsuario);
    public List<Usuario> buscarPorIntereses(String intereses);
}
