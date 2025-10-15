package com.example.demo.piloto;

import com.example.demo.aerolinea.AerolineaEntity;
import com.example.demo.aerolinea.AerolineaResponse;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
@Builder
public class PilotoResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private Boolean estado;
    private AerolineaResponse aerolineaResponse;
}
