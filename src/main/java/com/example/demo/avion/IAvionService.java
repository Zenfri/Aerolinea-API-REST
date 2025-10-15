package com.example.demo.avion;

import com.example.demo.respuesta.CuerpoResponse;

import java.util.List;

public interface IAvionService {
    public CuerpoResponse<AvionResponse> guardarAvion(AvionRequest avionRequest);
    public CuerpoResponse<AvionResponse> buscarAvionPorId(Long id);

    public CuerpoResponse<List<AvionResponse>> listarAviones();
}
