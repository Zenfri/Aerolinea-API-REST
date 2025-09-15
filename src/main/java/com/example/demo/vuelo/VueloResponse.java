package com.example.demo.vuelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @Builder @AllArgsConstructor
public class VueloResponse {
    @JsonFormat(pattern = "dd-MM-yyyy") //devolver salida
    private LocalDate fechaSalida;
    @JsonFormat(pattern = "dd-MM-yyyy") //devolver salida
    private LocalDate fechaLlegada;
    private String origen;
    private String destino;
    private short capacidadAvion;
    private String modeloAvion;
    private String nombreAerolinea;
}
