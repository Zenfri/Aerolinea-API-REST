package com.example.demo.pasajero;

import com.example.demo.respuesta.CuerpoResponse;

import java.util.List;

public interface IPasajeroService {
    public CuerpoResponse<PasajeroResponse> guardarPasajero(PasajeroRequest pasajeroRequest);
    public CuerpoResponse<PasajeroResponse> buscarPasajeroPorId(Long id);
    public CuerpoResponse<PasajeroResponse> actualizarPasajero(Long id, PasajeroRequest pasajeroRequest);
    public CuerpoResponse<PasajeroResponse> eliminarPasajero(Long id);
    public List<CuerpoResponse<PasajeroResponse>> listarPasajeros();


}
