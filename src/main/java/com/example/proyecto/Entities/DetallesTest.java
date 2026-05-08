package com.example.proyecto.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DetallesTests")
@Data
public class DetallesTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleTest;

    @ManyToOne
    @JoinColumn(name = "idTest")
    private Test test;

    private String pregunta;
    private String respuesta;
    private String observacion;

    public Long getIdDetalleTest() {
        return idDetalleTest;
    }

    public void setIdDetalleTest(Long idDetalleTest) {
        this.idDetalleTest = idDetalleTest;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
