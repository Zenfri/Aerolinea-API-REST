package com.example.demo.vuelo;

import com.example.demo.avion.AvionEntity;
import com.example.demo.avion.IAvionRepository;
import com.example.demo.piloto.IPilotoRepository;
import com.example.demo.piloto.PilotoEntity;
import com.example.demo.piloto.PilotoResponse;
import com.example.demo.respuesta.CuerpoResponse;
import com.example.demo.util.Constantes.CodigosHttp;
import com.example.demo.util.Constantes.Mensajes;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImpl implements IVuelvoService{
    private IVueloRepository iVueloRepository;
    private IAvionRepository iAvionRepository;
    private IPilotoRepository iPilotoRepository;

    public VueloServiceImpl(IVueloRepository iVueloRepository, IAvionRepository iAvionRepository, IPilotoRepository iPilotoRepository){
        this.iVueloRepository = iVueloRepository;
        this.iAvionRepository = iAvionRepository;
        this.iPilotoRepository = iPilotoRepository;
    }
    @Override
    public CuerpoResponse<VueloResponse> guardarVuelo(VueloRequest vueloRequest) {
        //visualizar
        System.out.println("FECHA LLEGADA:"+ vueloRequest.getFechaLlegada());

        //Validar si existe el id del avion
        Long idAvion = vueloRequest.getIdAvion();
        Optional<AvionEntity> avionEntityOptional = iAvionRepository.findById(idAvion);
        if(avionEntityOptional.isEmpty()){
            return new CuerpoResponse<>(
                    CodigosHttp.NO_ENCONTRADO,
                     Mensajes.ERROR_CREACION + ". No se encontró el Avión con id: "+idAvion,
                    Optional.empty()
            );
        }
        AvionEntity avionEncontrado = avionEntityOptional.get();

        //Validad ids de pilotos
        List<Long> lIdPilotos = vueloRequest.getIdPilotos();

        //Validar que existan los ids de los pilotos
        List<PilotoEntity> pilotosEncontradosPorId = this.iPilotoRepository.findAllById(lIdPilotos);

        if(pilotosEncontradosPorId.size() !=  lIdPilotos.size()){
            return new CuerpoResponse<>(
                    CodigosHttp.NO_ENCONTRADO,
                    Mensajes.ERROR_BUSQUEDA+ ". Algunos pilotos no existen",
                    Optional.empty()
            );
        }

        //Validar que la fecha de salida sea menor a la fecha de llegada
        if(vueloRequest.getFechaLlegada().isBefore(vueloRequest.getFechaSalida())){
            return new CuerpoResponse<>(
                    CodigosHttp.SOLICITUD_INCORRECTA,
                    Mensajes.EXITO_CREACION + ". La fecha de llegada no puede ser menor que la fecha de salida",
                    Optional.empty()
            );
        }

        //Convertir VueloRequest a Vuelo Entity
        VueloEntity vueloEntity = fromVueloRequestToVueloEntity(vueloRequest);
        vueloEntity.setAvionEntity(avionEncontrado);
        vueloEntity.setLPilotoEntities(pilotosEncontradosPorId);

        VueloEntity vueloGuardado = this.iVueloRepository.save(vueloEntity);

        //crear una lista de PilotosResponse, para darsela al VueloResponse
        List<PilotoResponse> lPilotosResponse = lPilotosEntityTolPilotosResponse(vueloGuardado.getLPilotoEntities());

        //Devolver al cliente VueloResponse
        VueloResponse vueloResponse = convertirAVueloResponse(vueloGuardado, lPilotosResponse);

        return new CuerpoResponse<>(
                CodigosHttp.CREADO,
                Mensajes.EXITO_CREACION,
                Optional.of(vueloResponse)
        );

    }
    @Override
    public CuerpoResponse<VueloResponse> buscarVueloPorId(Long id) {
        if(id == null || id <= 0){
            return new CuerpoResponse<>(
                    CodigosHttp.SOLICITUD_INCORRECTA,
                    Mensajes.ERROR_BUSQUEDA,
                    Optional.empty()
            );
        }

        Optional<VueloEntity> vueloEncontradoOptional = this.iVueloRepository.findById(id);

        if(vueloEncontradoOptional.isEmpty()){
            return new CuerpoResponse<>(
                    CodigosHttp.NO_ENCONTRADO,
                    Mensajes.ERROR_BUSQUEDA,
                    Optional.empty()
            );
        }
        VueloEntity vueloEncontrado = vueloEncontradoOptional.get();

        List<PilotoResponse> lPilotosResponse = lPilotosEntityTolPilotosResponse(vueloEncontrado.getLPilotoEntities());

        VueloResponse vueloResponse = convertirAVueloResponse(vueloEncontrado, lPilotosResponse);

        return new CuerpoResponse<>(
                CodigosHttp.EXITO,
                Mensajes.EXITO_BUSQUEDA,
                Optional.of(vueloResponse)
        );

    }

    @Override
    public CuerpoResponse<List<VueloResponse>> buscarVueloPorFechaSalida(LocalDate fechaSalida) {
        if(fechaSalida == null){
            return new CuerpoResponse<>(
                    CodigosHttp.SOLICITUD_INCORRECTA,
                    Mensajes.ERROR_BUSQUEDA,
                    Optional.empty()
            );
        }

        List<VueloEntity> lVueloEntities = this.iVueloRepository.findByFechaSalida(fechaSalida);

        if(lVueloEntities.isEmpty()){
            return new CuerpoResponse<>(
                    CodigosHttp.NO_ENCONTRADO,
                    Mensajes.ERROR_BUSQUEDA,
                    Optional.empty()
            );
        }

        List<VueloResponse> lvuelosResponse = lVueloEntities.stream().map(vuelo -> {
            return new VueloResponse(
                    vuelo.getId(),
                    vuelo.getFechaSalida(),
                    vuelo.getFechaLlegada(),
                    vuelo.getOrigen(),
                    vuelo.getDestino(),
                    vuelo.getAvionEntity().getCapacidad(),
                    vuelo.getAvionEntity().getModelo(),
                    vuelo.getAvionEntity().getAerolineaEntity().getNombre(),
                    lPilotosEntityTolPilotosResponse(vuelo.getLPilotoEntities())
            );
        }).toList();


        return new CuerpoResponse<>(
                CodigosHttp.EXITO,
                Mensajes.EXITO_BUSQUEDA,
                Optional.of(lvuelosResponse)
        );
    }

    @Override
    public CuerpoResponse<List<VueloResponse>> listarVuelos() {
        return null;
    }

    private VueloEntity fromVueloRequestToVueloEntity(VueloRequest vueloRequest){
        return VueloEntity.builder()
                .fechaSalida(vueloRequest.getFechaSalida())
                .fechaLlegada(vueloRequest.getFechaLlegada())
                .origen(vueloRequest.getOrigen())
                .destino(vueloRequest.getDestino())
                //luego se setea avion
                // luego se setea lPilotos
                .build();
    }

    private VueloResponse convertirAVueloResponse(VueloEntity vuelo, List<PilotoResponse> lPilotosResponse){
        return new VueloResponse(
                vuelo.getId(),
                vuelo.getFechaSalida(),
                vuelo.getFechaLlegada(),
                vuelo.getOrigen(),
                vuelo.getDestino(),
                vuelo.getAvionEntity().getCapacidad(),
                vuelo.getAvionEntity().getModelo(),
                vuelo.getAvionEntity().getAerolineaEntity().getNombre(),
                lPilotosResponse
        );
    }

    private List<PilotoResponse> lPilotosEntityTolPilotosResponse(List<PilotoEntity> pilotos){
        /*return  pilotos.stream().map(
                piloto ->
                        new PilotoResponse(
                                piloto.getId(),
                                piloto.getNombre(),
                                piloto.getApellido(),
                                piloto.getEstado(),
                                //Aerolinea Response
                                new AerolineaResponse(
                                        piloto.getAerolineaEntity().getId(),
                                        piloto.getAerolineaEntity().getNombre()
                                )
                        )
        ).toList();*/
        return null;
    }

}
