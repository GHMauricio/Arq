package com.example.proyecto.Repositories;

import com.example.proyecto.Entities.Notificacion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion,Long> {

    List<Notificacion> findByUsuarioIdUsuarioOrderByFechaEnvioDesc(Long idUsuario);

    @Modifying
    @Transactional
    @Query("UPDATE Notificacion n SET n.leido = true WHERE n.usuario.idUsuario = :idUsuario AND n.leido = false")
    void marcarComoLeido(Long idUsuario);
}
