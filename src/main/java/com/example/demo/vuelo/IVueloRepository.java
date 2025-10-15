package com.example.demo.vuelo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IVueloRepository extends JpaRepository<VueloEntity,Long> {

    //buscar por fecha de salida
    List<VueloEntity> findByFechaSalida(LocalDate fechaSalida);
}
