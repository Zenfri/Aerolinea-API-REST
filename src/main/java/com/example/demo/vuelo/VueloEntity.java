package com.example.demo.vuelo;

import com.example.demo.avion.AvionEntity;
import com.example.demo.piloto.PilotoEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vuelos")

public class VueloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vuelo_id")
    private Long id;

    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @Column(name = "fecha_llegada")
    private LocalDate fechaLlegada;

    private String origen;
    private String destino;

    @ManyToOne
    @JoinColumn(name = "avion_id")
    private AvionEntity avionEntity;

    @ManyToMany
    @JoinTable(
            name = "vuelo_piloto", //mapea con el nombre de la tabla (si no existe la crea)
            joinColumns = @JoinColumn(name = "vuelo_id"), //fk de la tabla
            inverseJoinColumns = @JoinColumn(name = "piloto_id") //fk de la tabla
    )
    private List<PilotoEntity> lPilotoEntities;

}
