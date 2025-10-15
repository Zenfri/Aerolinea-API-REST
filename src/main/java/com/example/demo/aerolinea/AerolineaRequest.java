package com.example.demo.aerolinea;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NotNull
public class AerolineaRequest {
    @NotBlank(message = "El nombre no debe estar vacio")
    @Size(min = 3, max = 30, message = "El nombre debe tener entre 3 y 30 caracteres")
    private String nombre;
}
