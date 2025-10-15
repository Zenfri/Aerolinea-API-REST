package com.example.demo.vuelo;

import com.example.demo.piloto.PilotoResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter @Builder @AllArgsConstructor
public class VueloResponse {
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy") //devolver salida
    private LocalDate fechaSalida;
    @JsonFormat(pattern = "dd-MM-yyyy") //devolver salida
    private LocalDate fechaLlegada;
    private String origen;
    private String destino;

    //Avion
    private short capacidadAvion;
    private String modeloAvion;

    //Aerolinea
    private String nombreAerolinea;

    //Pilotos
    private List<PilotoResponse> lPilotoResponses;
}
