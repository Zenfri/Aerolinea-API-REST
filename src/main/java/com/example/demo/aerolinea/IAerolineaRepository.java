package com.example.demo.aerolinea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //no es necesario colcoarlo?
public interface IAerolineaRepository extends JpaRepository<AerolineaEntity,Long> {
}
