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

     @Query(value = "SELECT EXTRACT(YEAR FROM nacimiento_adolescente) AS anio, COUNT(*) AS cantidad FROM usuarios GROUP BY EXTRACT(YEAR FROM nacimiento_adolescente) ORDER BY anio ASC", nativeQuery = true)
    List<Object[]> contabilizarAdolescentesPorAnio();

    @Query(value = "SELECT genero_adolescente AS genero, COUNT(*) AS cantidad FROM usuarios GROUP BY genero_adolescente ORDER BY genero ASC", nativeQuery = true)
    List<Object[]> contabilizarUsuariosPorGenero();
}
