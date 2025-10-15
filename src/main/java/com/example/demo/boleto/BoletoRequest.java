package com.example.demo.boleto;

import com.example.demo.pasajero.PasajeroEntity;
import com.example.demo.vuelo.VueloEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoletoRequest {
    private Short asiento;
    private Long idPasajero;
    private Long idVuelo;
}
