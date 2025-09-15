package com.example.demo.pasajero;

import com.example.demo.respuesta.CuerpoRespuesta;

import java.util.List;

public interface IPasajeroService {
    public CuerpoRespuesta<PasajeroEntity> guardarPasajero(PasajeroEntity pasajeroEntity);
    public CuerpoRespuesta<PasajeroEntity> buscarPasajeroPorId(Long id);
    public CuerpoRespuesta<PasajeroEntity> actualizarPasajero(PasajeroEntity pasajeroEntity);
    public CuerpoRespuesta<PasajeroEntity> eliminarPasajero(Long id);
    public List<CuerpoRespuesta<PasajeroEntity>> listarPasajeros();


}
