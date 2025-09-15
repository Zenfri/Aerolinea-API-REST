package com.example.demo.avion;

import com.example.demo.aerolinea.AerolineaEntity;
import com.example.demo.aerolinea.IAerolineaRepository;
import com.example.demo.respuesta.CuerpoRespuesta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvionServiceImpl implements IAvionService{

    private IAvionRepository iAvionRepository;
    private IAerolineaRepository iAerolineaRepository;

    public AvionServiceImpl(IAvionRepository iAvionRepository, IAerolineaRepository iAerolineaRepository){
        this.iAvionRepository = iAvionRepository;
        this.iAerolineaRepository = iAerolineaRepository;
    }
    @Override
    public CuerpoRespuesta<AvionEntity> guardarAvion(AvionRequest avionRequest) {

        //Validar existencia de aerolinea
        Long idAerolinea = avionRequest.getIdAerolinea();
        Optional<AerolineaEntity> aerolineaEntityOptional = iAerolineaRepository.findById(idAerolinea);

        if(aerolineaEntityOptional.isEmpty()){
            return new CuerpoRespuesta<>(
                    404,
                    "No se encontró una Aerolinea con el id: "+idAerolinea,
                    Optional.empty()
            );
        }

        //capacidad y peso positivos
        ArrayList<String> mensajesErrores = new ArrayList<>();
        if(avionRequest.getCapacidad() <= 0){
            mensajesErrores.add("La capacidad no puede ser mayor a cero");
        }
        if(avionRequest.getPeso() <= 0){
            mensajesErrores.add("El peso no puede ser mayor a cero");
        }
        if (!mensajesErrores.isEmpty()) {
            return new CuerpoRespuesta<>(
                    400,
                    String.join(". ",mensajesErrores),
                    Optional.empty()
            );
        }

        //NO hubo errores
        AvionEntity avionEntity = AvionEntity.builder()
                .capacidad(avionRequest.getCapacidad())
                .peso(avionRequest.getPeso())
                .modelo(avionRequest.getModelo())
                .aerolineaEntity(aerolineaEntityOptional.get())
                .build();

        this.iAvionRepository.save(avionEntity);

        return new CuerpoRespuesta<>(
                201,
                "Avión guardado correctamente",
                Optional.of(avionEntity)
        );

    }

    @Override
    public CuerpoRespuesta<AvionEntity> buscarAvionPorId(Long id) {
        return null;
    }

    @Override
    public CuerpoRespuesta<List<AvionEntity>> listarAviones() {
        return null;
    }
}
