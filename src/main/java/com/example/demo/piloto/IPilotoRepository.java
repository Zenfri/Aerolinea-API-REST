package com.example.demo.piloto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPilotoRepository extends JpaRepository<PilotoEntity,Long> {
    //Buscar por nombre

    List<PilotoEntity> findByNombreContainingIgnoreCase(String nombre);

}
