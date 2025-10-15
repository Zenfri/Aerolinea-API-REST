package com.example.demo.excepciones;

import lombok.Getter;

@Getter
public class RecursoExistenteExcepcion extends RuntimeException{
    public RecursoExistenteExcepcion(String mensaje) {
        super(mensaje);
    }
}
