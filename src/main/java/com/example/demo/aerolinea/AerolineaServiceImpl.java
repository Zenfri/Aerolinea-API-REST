package com.example.demo.aerolinea;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AerolineaServiceImpl implements IAerolineaService {
    private IAerolineaRepository iAerolineaRepository;

    public AerolineaServiceImpl(IAerolineaRepository iAerolineaRepository){
        this.iAerolineaRepository = iAerolineaRepository;
    }
    @Override
    public AerolineaEntity guardar(AerolineaEntity aerolineaEntity) {
        if(aerolineaEntity.getNombre().length() > 20){
            return null;
        }
        return iAerolineaRepository.save(aerolineaEntity);
    }

    @Override
    public List<AerolineaEntity> listar() {
        return null;
    }

    @Override
    public AerolineaEntity buscarPorId(Long id) {
        return null;
    }
}
