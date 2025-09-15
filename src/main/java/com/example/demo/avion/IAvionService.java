package com.example.demo.avion;

import com.example.demo.respuesta.CuerpoRespuesta;

import java.util.List;

public interface IAvionService {
    public CuerpoRespuesta<AvionEntity> guardarAvion(AvionRequest avionRequest);
    public CuerpoRespuesta<AvionEntity> buscarAvionPorId(Long id);

    public CuerpoRespuesta<List<AvionEntity>> listarAviones();
}
