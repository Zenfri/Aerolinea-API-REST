package com.example.demo.pasajero;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PasajeroResponse {
    private Long id;
    private String nombre;
    private String apellido;
}
