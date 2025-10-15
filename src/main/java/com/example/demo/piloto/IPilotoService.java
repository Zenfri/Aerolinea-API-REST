package com.example.demo.piloto;

import com.example.demo.respuesta.CuerpoResponse;

import java.util.List;

public interface IPilotoService {
    CuerpoResponse<PilotoResponse> guardarPiloto(PilotoRequest pilotoRequest);
    CuerpoResponse<PilotoResponse> buscarPorId(Long id);

    CuerpoResponse<List<PilotoResponse>> buscarPorNombre(String nombre);
    List<PilotoEntity> listar();
}
