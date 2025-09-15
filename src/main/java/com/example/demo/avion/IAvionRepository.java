package com.example.demo.avion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAvionRepository extends JpaRepository<AvionEntity,Long> {
}
