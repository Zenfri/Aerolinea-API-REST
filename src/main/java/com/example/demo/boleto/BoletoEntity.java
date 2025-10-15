package com.example.demo.boleto;

import com.example.demo.pasajero.PasajeroEntity;
import com.example.demo.vuelo.VueloEntity;
import jakarta.persistence.*;
import lombok.*;

@Setter @Getter @Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="boletos")
public class BoletoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boleto_id")
    private Long id;
    private Short asiento;

    @ManyToOne
    @JoinColumn(name = "vuelo_id")
    private VueloEntity vueloEntity;

    @ManyToOne
    @JoinColumn(name = "pasajero_id")
    private PasajeroEntity pasajeroEntity;
}
