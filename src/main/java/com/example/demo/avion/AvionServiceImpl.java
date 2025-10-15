package com.example.demo.avion;

import com.example.demo.aerolinea.IAerolineaRepository;
import com.example.demo.respuesta.CuerpoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvionServiceImpl implements IAvionService{

    private IAvionRepository iAvionRepository;
    private IAerolineaRepository iAerolineaRepository;

    @Override
    public CuerpoResponse<AvionResponse> guardarAvion(AvionRequest avionRequest) {
        return null;
    }

    @Override
    public CuerpoResponse<AvionResponse> buscarAvionPorId(Long id) {
        return null;
    }

    @Override
    public CuerpoResponse<List<AvionResponse>> listarAviones() {
        return null;
    }
/*
    public AvionServiceImpl(IAvionRepository iAvionRepository, IAerolineaRepository iAerolineaRepository){
        this.iAvionRepository = iAvionRepository;
        this.iAerolineaRepository = iAerolineaRepository;
    }
    @Override
    public ApiResponse<AvionResponse> guardarAvion(AvionRequest avionRequest) {

        //Validar existencia de aerolinea
        Long idAerolinea = avionRequest.getIdAerolinea();
        Optional<AerolineaEntity> aerolineaEntityOptional = iAerolineaRepository.findById(idAerolinea);

        if(aerolineaEntityOptional.isEmpty()){
            return new ApiResponse<>(
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
            return new ApiResponse<>(
                    400,
                    String.join(". ",mensajesErrores),
                    Optional.empty()
            );
        }

        //NO hubo errores
        AvionEntity avionEntity = fromAvionRequestToAvionEntity(avionRequest);
        avionEntity.setAerolineaEntity(aerolineaEntityOptional.get());

        AvionEntity avionGuardado = this.iAvionRepository.save(avionEntity);

        AvionResponse avionResponse = fromAvionEntityToAvionResponse(avionGuardado);

        return new ApiResponse<>(
                201,
                "Avión guardado correctamente",
                Optional.of(avionResponse)
        );

    }

    private AvionResponse fromAvionEntityToAvionResponse(AvionEntity avionEntity){
        return new AvionResponse(
                avionEntity.getId(),
                avionEntity.getCapacidad(),
                avionEntity.getPeso(),
                avionEntity.getModelo(),
                new AerolineaResponse(
                        avionEntity.getAerolineaEntity().getId(),
                        avionEntity.getAerolineaEntity().getNombre()
                )
        );
        return null;
    }

    private AvionEntity fromAvionRequestToAvionEntity(AvionRequest avionRequest){
        return AvionEntity.builder()
                .capacidad(avionRequest.getCapacidad())
                .peso(avionRequest.getPeso())
                .modelo(avionRequest.getModelo())
                .build();
    }

    @Override
    public ApiResponse<AvionResponse> buscarAvionPorId(Long id) {
        if(id == null){
            return new ApiResponse<>(
                    CodigosHttp.SOLICITUD_INCORRECTA,
                    Mensajes.EXITO_BUSQUEDA,
                    Optional.empty()
            );
        } else if(id <= 0){
            return new ApiResponse<>(
                    CodigosHttp.SOLICITUD_INCORRECTA,
                    Mensajes.ERROR_BUSQUEDA + ". El id debe ser mayor que cero",
                    Optional.empty()
            );
        }

        Optional<AvionEntity> avionEntityOptional = this.iAvionRepository.findById(id);

        if(avionEntityOptional.isEmpty()){
            return new ApiResponse<>(
                    CodigosHttp.NO_ENCONTRADO,
                    Mensajes.ERROR_BUSQUEDA,
                    Optional.empty()
            );
        }

        AvionResponse avionResponse = fromAvionEntityToAvionResponse(avionEntityOptional.get());
        return new ApiResponse<>(
                CodigosHttp.EXITO,
                Mensajes.EXITO_BUSQUEDA,
                Optional.of(avionResponse)
        );

    }

    @Override
    public ApiResponse<List<AvionResponse>> listarAviones() {
        return null;
    }*/
}
