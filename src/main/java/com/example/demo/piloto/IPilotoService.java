package com.example.demo.piloto;

import com.example.demo.respuesta.CuerpoRespuesta;

import java.util.List;

public interface IPilotoService {
    public CuerpoRespuesta<Piloto> guardarPiloto(PilotoRequest pilotoRequest);
    public CuerpoRespuesta<Piloto> buscarPorId(Long id);

    public List<Piloto> listar();
}
