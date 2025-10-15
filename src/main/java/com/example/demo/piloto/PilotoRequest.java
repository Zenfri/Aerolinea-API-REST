package com.example.demo.piloto;

import lombok.Getter;
import lombok.Setter;

//Solo los atributos que son necesarios que envie el cliente
@Getter
@Setter
public class PilotoRequest {
    private String nombres;
    private String apellidos;
    private Long idAerolinea;
}
