Skip to content
GHMauricio
Arq
Repository navigation
Code
Issues
Pull requests
Agents
Actions
Projects
Wiki
Security and quality
Insights
Settings
Important update
On April 24 we'll start using GitHub Copilot interaction data for AI model training unless you opt out. Review this update and manage your preferences in your GitHub account settings.
Files
Go to file
t
T
Servicesimplements content loaded
.mvn
src
main
java/com/example/proyecto
Controllers
ArticuloController.java
DetalleTestController.java
DetallesEventoController.java
EntrevistaController.java
EventoController.java
JwtAuthenticationController.java
NotificacionController.java
ProgresoEmocionalController.java
RecomendacionController.java
TestController.java
UsuarioController.java
DTOs
ArticuloDTO.java
DetalleTestDTO.java
DetallesEventoDTO.java
EntrevistaDTO.java
EventosDTO.java
JwtRequestDTO.java
JwtResponseDTO.java
NotificacionDTO.java
ProgresoEmocionalDTO.java
RecomendacionDTO.java
TestDTO.java
UsuarioDTO.java
UsuarioRegistroDTO.java
Entities
Articulos.java
DetallesEvento.java
DetallesTest.java
Entrevista.java
Eventos.java
Notificacion.java
ProgresoEmocional.java
Recomendacion.java
Role.java
Test.java
Usuario.java
Repositories
ArticuloRepository.java
DetalleTestRepository.java
DetallesEventoRepository.java
EntrevistaRepository.java
EventoRepository.java
NotificacionRepository.java
ProgresoEmocionalRepository.java
RecomendacionRepository.java
TestRepository.java
UsuarioRepository.java
Securities
Servicesimplements
ArticuloServiceImplement.java
DetalleTestServiceImplement.java
DetallesEventoServiceImplement.java
EntrevistaServiceImplement.java
EventoServiceImplement.java
JwtUserDetailsService.java
NotificacionServiceImplement.java
ProgresoEmocionalServiceImplement.java
RecomendacionServiceImplement.java
TestServiceImplement.java
UsuarioServiceImplement.java
Servicesinterfaces
IArticuloService.java
IDetalleTestService.java
IDetallesEventosService.java
IEntrevistaService.java
IEventoService.java
INotificacionService.java
IProgresoEmocionalService.java
IRecomendacionService.java
ITestService.java
IUsuarioService.java
ProyectoApplication.java
resources
test/java/com/example/proyecto
fdsdf
.gitattributes
.gitignore
Dockerfile
mvnw
mvnw.cmd
pom.xml
Arq/src/main/java/com/example/proyecto/Servicesimplements
/
TestServiceImplement.java
in
main

Edit

Preview
Indent mode

Spaces
Indent size

4
Line wrap mode

No wrap
Editing TestServiceImplement.java file contents
  1
  2
  3
  4
  5
  6
  7
  8
  9
 10
 11
 12
 13
 14
 15
 16
 17
 18
 19
 20
 21
 22
 23
 24
 25
 26
 27
 28
 29
 30
 31
 32
 33
 34
 35
 36
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
// Validación: El usuario debe existir en la BD
        if (!uR.existsById(test.getUsuario().getIdUsuario())) {
            throw new RuntimeException("No se puede registrar el test: El usuario no existe.");
        }

        // Fecha automática si llega nula
        if (test.getFechaTest() == null) {
            test.setFechaTest(LocalDate.now());
        }

        tR.save(test);
Use Control + Shift + m to toggle the tab key moving focus. Alternatively, use esc then tab to move to the next interactive element on the page.
 
Servicesimplements content loaded
