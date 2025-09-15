package com.example.demo.vuelo;

import com.example.demo.avion.AvionEntity;
import com.example.demo.avion.IAvionRepository;
import com.example.demo.respuesta.CuerpoRespuesta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImpl implements IVuelvoService{
    private IVueloRepository iVueloRepository;
    private IAvionRepository iAvionRepository;

    public VueloServiceImpl(IVueloRepository iVueloRepository, IAvionRepository iAvionRepository){
        this.iVueloRepository = iVueloRepository;
        this.iAvionRepository = iAvionRepository;
    }
    @Override
    public CuerpoRespuesta<VueloResponse> guardarVuelo(VueloRequest vueloRequest) {
        //visualizar
        System.out.println("FECHA LLEGADA:"+ vueloRequest.getFechaLlegada());

        //Validar si existe el id del Vuelo
        Long idAvion = vueloRequest.getIdAvion();
        Optional<AvionEntity> avionEntityOptional = iAvionRepository.findById(idAvion);

        if(avionEntityOptional.isEmpty()){
            return new CuerpoRespuesta<>(
                    404,
                    "No se encontró el Avión con id: "+idAvion,
                    Optional.empty()
            );
        }

        //Validar que la fecha de salida sea menor a la fecha de llegada
        if(vueloRequest.getFechaLlegada().isBefore(vueloRequest.getFechaSalida())){
            return new CuerpoRespuesta<>(
                    400,
                    "La fecha de llegada no puede ser menor que la fecha de salida",
                    Optional.empty()
            );
        }

        //Todo bien
        VueloEntity vueloEntity = VueloEntity.builder()
                .fechaSalida(vueloRequest.getFechaSalida())
                .fechaLlegada(vueloRequest.getFechaLlegada())
                .origen(vueloRequest.getOrigen())
                .destino(vueloRequest.getDestino())
                .avionEntity(avionEntityOptional.get())
                .build();

        this.iVueloRepository.save(vueloEntity);

        VueloResponse vueloResponse = VueloResponse.builder()
                .fechaSalida(vueloEntity.getFechaSalida())
                .fechaLlegada(vueloEntity.getFechaLlegada())
                .origen(vueloEntity.getOrigen())
                .destino(vueloEntity.getDestino())
                .capacidadAvion(vueloEntity.getAvionEntity().getCapacidad())
                .modeloAvion(vueloEntity.getAvionEntity().getModelo())
                .nombreAerolinea(vueloEntity.getAvionEntity().getAerolineaEntity().getNombre())
                .build();

        return new CuerpoRespuesta<>(
                201,
                "El Vuelo se registró exitósamente",
                Optional.of(vueloResponse)
        );

    }

    @Override
    public CuerpoRespuesta<VueloEntity> buscarVueloPorId(Long id) {
        return null;
    }

    @Override
    public List<CuerpoRespuesta<VueloEntity>> listarVuelos() {
        return null;
    }
}
