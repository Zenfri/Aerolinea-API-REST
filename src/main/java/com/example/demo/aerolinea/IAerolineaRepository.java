package com.example.demo.aerolinea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //no es necesario colcoarlo?
public interface IAerolineaRepository extends JpaRepository<AerolineaEntity,Long> {
    Optional<AerolineaEntity> findFirstByNombre(String nombre);
    Boolean existsByNombre(String nombre);
    Boolean existsByNombreAndIdNot(String nombre, Long id);
}
