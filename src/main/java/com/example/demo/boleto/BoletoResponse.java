package com.example.demo.boleto;

import com.example.demo.pasajero.PasajeroEntity;
import com.example.demo.pasajero.PasajeroResponse;
import com.example.demo.vuelo.VueloEntity;
import com.example.demo.vuelo.VueloResponse;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class BoletoResponse {
    private Long id;
    private Short asiento;
    // private VueloEntity vueloEntity; NUNCA DEVOLVER ENTIDADES AL CLIENTE
    // private PasajeroEntity pasajeroEntity; NUNCA DEVOLVER ENTIDADES AL CLIENTE

    private VueloResponse vueloResponse;
    private PasajeroResponse pasajeroResponse;
}
