package com.example.demo.piloto;

import com.example.demo.aerolinea.AerolineaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pilotos")
public class PilotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="piloto_id")
    private Long id;

    private String nombre;
    private String apellido;
    private Boolean estado;
    @ManyToOne
    @JoinColumn(name = "aerolinea_id")
    private AerolineaEntity aerolineaEntity;
}
