package com.example.demo.vuelo;

import com.example.demo.respuesta.CuerpoRespuesta;

import java.util.List;

public interface IVuelvoService {
    public CuerpoRespuesta<VueloResponse> guardarVuelo(VueloRequest vueloRequest);
    public CuerpoRespuesta<VueloEntity> buscarVueloPorId(Long id);
    public List<CuerpoRespuesta<VueloEntity>> listarVuelos();
}
