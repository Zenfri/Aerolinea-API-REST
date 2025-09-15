package com.example.demo.vuelo;

import com.example.demo.avion.AvionEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class VueloRequest {
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaSalida;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaLlegada;
    private String origen;
    private String destino;
    private Long idAvion;
}
