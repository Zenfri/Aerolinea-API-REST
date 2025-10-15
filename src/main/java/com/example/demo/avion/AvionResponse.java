package com.example.demo.avion;

import com.example.demo.aerolinea.AerolineaEntity;
import com.example.demo.aerolinea.AerolineaResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Builder @AllArgsConstructor
public class AvionResponse {
    private Long id;
    private short capacidad;
    private short peso;
    private String modelo;

    @JsonFormat(pattern = "dd-MM-yyyy | HH:mm:ss")
    private LocalDateTime tiempoCreacion;
    @JsonFormat(pattern = "dd-MM-yyyy | HH:mm:ss")
    private LocalDateTime tiempoModificacion;

    private AerolineaResponse aerolineaResponse;
}
