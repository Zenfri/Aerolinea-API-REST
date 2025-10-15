package com.example.demo.respuesta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Getter @Setter
public class CuerpoResponse<T> {
    private int codigo;
    private String mensaje;
    private Optional<T> dato;
}