package com.example.demo.avion;

import com.example.demo.aerolinea.AerolineaEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvionRequest {
    private Long id;
    private short capacidad;
    private short peso;
    private String modelo;
    private Long idAerolinea;
}
