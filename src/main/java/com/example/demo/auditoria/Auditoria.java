package com.example.demo.auditoria;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditoria {
    @CreatedDate
    @Column(name = "tiempo_creacion", nullable = false, updatable = false)
    protected LocalDateTime  tiempoCreacion;

    @LastModifiedDate
    @Column(name = "tiempo_modificacion", nullable = false)
    protected LocalDateTime tiempoModificacion;

}
