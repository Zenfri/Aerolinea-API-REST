package com.example.demo.avion;

import com.example.demo.aerolinea.AerolineaEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aviones")
public class AvionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avion_id")
    private Long id;
    private short capacidad;
    private short peso;
    private String modelo;

    @ManyToOne
    @JoinColumn(name = "aerolinea_id") // columna fk
    private AerolineaEntity aerolineaEntity;
}
