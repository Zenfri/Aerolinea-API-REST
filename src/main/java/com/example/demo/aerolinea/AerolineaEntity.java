package com.example.demo.aerolinea;

import com.example.demo.auditoria.Auditoria;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aerolineas")
public class AerolineaEntity extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aerolinea_id")
    private Long id;

    private String nombre;
}
