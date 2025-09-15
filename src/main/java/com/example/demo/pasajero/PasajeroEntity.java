package com.example.demo.pasajero;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pasajeros")
public class PasajeroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pasajero_id")
    private Long id;

    private String nombre;
    private String apellido;
}
