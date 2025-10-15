package com.example.demo.vuelo;

import com.example.demo.avion.AvionEntity;
import com.example.demo.piloto.PilotoEntity;
import com.example.demo.piloto.PilotoRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class VueloRequest {
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaSalida;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaLlegada;
    private String origen;
    private String destino;
    private Long idAvion;

    private List<Long> idPilotos;
}
