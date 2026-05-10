package com.example.proyecto.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "Entrevistas")
@Data
public class Entrevista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrevista;

    @ManyToOne
    @JoinColumn(name = "idRecomendacion")
    private Recomendacion recomendacion;

    private LocalDate fechaEntrevista;
    private String temaEntrevista;
    private String comentarioEntrevista;
}
