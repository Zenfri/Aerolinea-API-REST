package com.example.demo.aerolinea;

import java.util.List;

public interface IAerolineaService {
    AerolineaResponse guardar(AerolineaRequest aerolineaRequest);

    AerolineaResponse buscarPorId(Long id);

    AerolineaResponse actualizarPorId(Long id, AerolineaRequest aerolineaRequest);
    List<AerolineaResponse> listar();
}
