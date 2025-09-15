package com.example.demo.aerolinea;

import java.util.List;

public interface IAerolineaService {
    public AerolineaEntity guardar(AerolineaEntity aerolineaEntity);
    public List<AerolineaEntity> listar();
    public AerolineaEntity buscarPorId(Long id);
}
