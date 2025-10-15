package com.example.demo.respuesta;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter @Builder
@AllArgsConstructor
public class ApiResponse<T>{
    private int codigo;
    private String mensaje;
    private T dato;
    private String recurso;
    @JsonFormat(pattern = "dd-MM-yyyy | HH:mm:ss")
    private LocalDateTime tiempo;

    public ApiResponse(int codigo, String mensaje, T dato, String recurso) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.dato = dato;
        this.recurso = recurso;
        this.tiempo = LocalDateTime.now();
    }
}
