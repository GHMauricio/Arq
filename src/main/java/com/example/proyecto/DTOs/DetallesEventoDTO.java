package com.example.proyecto.DTOs;

import lombok.Data;

@Data
public class DetalleTestDTO {
    private Long idDetalleTest;
    private Long idTest;
    private String pregunta;
    private String respuesta;
    private String observacion;

    public Long getIdDetalleTest() {
        return idDetalleTest;
    }

    public void setIdDetalleTest(Long idDetalleTest) {
        this.idDetalleTest = idDetalleTest;
    }

    public Long getIdTest() {
        return idTest;
    }

    public void setIdTest(Long idTest) {
        this.idTest = idTest;
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
