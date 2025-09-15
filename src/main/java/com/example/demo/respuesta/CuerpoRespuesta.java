package com.example.demo.respuesta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CuerpoRespuesta<T> {
    private int codigo;
    private String mensaje;
    private Optional<T> dato;
}
