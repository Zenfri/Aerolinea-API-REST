package com.example.demo.vuelo;

import com.example.demo.respuesta.CuerpoResponse;

import java.time.LocalDate;
import java.util.List;

public interface IVuelvoService {
    public CuerpoResponse<VueloResponse> guardarVuelo(VueloRequest vueloRequest);
    public CuerpoResponse<VueloResponse> buscarVueloPorId(Long id);
    public CuerpoResponse<List<VueloResponse>> buscarVueloPorFechaSalida(LocalDate fechaSalida);
    public CuerpoResponse<List<VueloResponse>> listarVuelos();
}
