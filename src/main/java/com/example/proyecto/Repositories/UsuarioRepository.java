package com.example.proyecto.Repositories;

import com.example.proyecto.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCorreoUsuario(String correo);
    //Usuario findOneByNombreUsuario(String username);
}
