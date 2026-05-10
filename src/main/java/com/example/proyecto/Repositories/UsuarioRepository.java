package com.example.proyecto.Repositories;

import com.example.proyecto.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByCorreoUsuario(String correo);

    @Query("SELECT u FROM Usuario u ORDER BY u.nacimientoAdolescente ASC")
    List<Usuario> listarPorNacimientoAdolescenteAscendente();
}
