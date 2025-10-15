package com.example.demo.excepciones;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidacionExcepcion extends RuntimeException{
    private final List<String> errores;
    public ValidacionExcepcion(List<String> errores){
        this.errores = errores;
    }
}
