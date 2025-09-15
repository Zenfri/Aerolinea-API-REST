package com.example.demo.piloto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPilotoRepository extends JpaRepository<Piloto,Long> {
}
