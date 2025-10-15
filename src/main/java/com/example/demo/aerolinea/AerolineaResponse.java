package com.example.demo.aerolinea;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter @Setter @Builder
public class AerolineaResponse {
    private Long id;
    private String nombre;
}
