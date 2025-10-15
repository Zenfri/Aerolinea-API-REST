package com.example.demo.excepciones;

public class RecursoNoEncontradoExcepcion extends RuntimeException {
    public RecursoNoEncontradoExcepcion(String mensaje) {
        super(mensaje);
    }
}
