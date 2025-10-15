package com.example.demo.boleto;

import com.example.demo.pasajero.IPasajeroRepository;
import com.example.demo.respuesta.CuerpoResponse;
import com.example.demo.vuelo.IVueloRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BoletoServiceImpl implements IBoletoService{

    private IBoletoRepository iBoletoRepository;
    private IVueloRepository iVueloRepository;
    private IPasajeroRepository iPasajeroRepository;

    @Override
    public CuerpoResponse<BoletoResponse> guardarBoleto(BoletoRequest boletoRequest) {
        return null;
    }
    /*
    @Override
    public ApiResponse<BoletoResponse> guardarBoleto(BoletoRequest boletoRequest) {
        if(boletoRequest == null){
            return new ApiResponse<>(
                    CodigosHttp.SOLICITUD_INCORRECTA,
                    Mensajes.ERROR_CREACION,
                    Optional.empty()
            );
        }

        //Validaciones
        List<String> mensajes = validacionBoleto(boletoRequest);
        if(!mensajes.isEmpty()){
            return new ApiResponse<>(
                    CodigosHttp.SOLICITUD_INCORRECTA,
                    Mensajes.ERROR_CREACION + ". " + String.join(". ",mensajes),
                    Optional.empty()
            );
        }

        //Buscar id del pasajero
        Long idPasajero = boletoRequest.getIdPasajero();
        Optional<PasajeroEntity> pasajeroEncontrado = this.iPasajeroRepository.findById(idPasajero);

        if(pasajeroEncontrado.isEmpty()){
            return new ApiResponse<>(
                    CodigosHttp.NO_ENCONTRADO,
                    Mensajes.ERROR_BUSQUEDA + ". No se encontró el pasajero con el id: " + idPasajero,
                    Optional.empty()
            );
        }

        //Buscar id del vuelo
        Long idVuelo = boletoRequest.getIdVuelo();
        Optional<VueloEntity> vueloEncontrado = this.iVueloRepository.findById(idVuelo);

        if(vueloEncontrado.isEmpty()){
            return new ApiResponse<>(
                    CodigosHttp.NO_ENCONTRADO,
                    Mensajes.ERROR_BUSQUEDA + ". No se encontró el vuelo con el id: " + idVuelo,
                    Optional.empty()
            );
        }


        //Validar que asiento no supere a capacidad del avion
        short capacidadVuelo = vueloEncontrado.get().getAvionEntity().getCapacidad();
        if(boletoRequest.getAsiento() > capacidadVuelo){
            return new ApiResponse<>(
                    CodigosHttp.SOLICITUD_INCORRECTA,
                    Mensajes.ERROR_CREACION + ". El valor del asiento no puede ser mayor a "+capacidadVuelo,
                    Optional.empty()
            );
        }


        //Si se encontraron ambos id
        BoletoEntity boletoEntity = BoletoEntity.builder()
                .asiento(boletoRequest.getAsiento())
                .vueloEntity(vueloEncontrado.get())
                .pasajeroEntity(pasajeroEncontrado.get())
                .build();

        BoletoEntity boletoCreado = this.iBoletoRepository.save(boletoEntity);

        VueloResponse vueloResponse = crearVueloResponse(boletoCreado);

        PasajeroResponse pasajeroResponse = crearPasajeroResponse(boletoCreado);

        //Creamos boletoResponse
        BoletoResponse boletoResponse = BoletoResponse.builder()
                .id(boletoCreado.getId())
                .asiento(boletoCreado.getAsiento())
                .vueloResponse(vueloResponse)
                .pasajeroResponse(pasajeroResponse)
                .build();

        return new ApiResponse<>(
                CodigosHttp.CREADO,
                Mensajes.EXITO_CREACION,
                Optional.of(boletoResponse)
        );
    }

    private List<String> validacionBoleto(BoletoRequest boletoRequest){
        List<String> errores = new ArrayList<>();
        if(boletoRequest.getAsiento() == null ){
            errores.add("El valor del asiento no puede estar vacío");

        } else if(boletoRequest.getAsiento() <= 0 ){
            errores.add("El valor del asiento no puede ser menor o igual que cero");
        }


        if(boletoRequest.getIdPasajero() == null ){
            errores.add("El id del pasajero no puede estar vacío");
        } else if (boletoRequest.getIdPasajero() <= 0) {
            errores.add("El id del pasajero debe ser mayor que cero");
        }

        if(boletoRequest.getIdVuelo() == null ){
            errores.add("El id del vuelo no puede estar vacío");
        }else if (boletoRequest.getIdVuelo() <= 0) {
            errores.add("El id del vuelo debe ser mayor que cero");
        }

        return errores;
    }

    private VueloResponse crearVueloResponse(BoletoEntity boletoCreado){
        return VueloResponse.builder()
                .id(boletoCreado.getVueloEntity().getId())
                .fechaSalida(boletoCreado.getVueloEntity().getFechaSalida())
                .fechaLlegada(boletoCreado.getVueloEntity().getFechaLlegada())
                .origen(boletoCreado.getVueloEntity().getOrigen())
                .destino(boletoCreado.getVueloEntity().getDestino())
                .capacidadAvion(boletoCreado.getVueloEntity().getAvionEntity().getCapacidad())
                .modeloAvion(boletoCreado.getVueloEntity().getAvionEntity().getModelo())
                .nombreAerolinea(boletoCreado.getVueloEntity().getAvionEntity().getAerolineaEntity().getNombre())
                .build();
    }

    private PasajeroResponse crearPasajeroResponse(BoletoEntity boletoCreado){
        return PasajeroResponse.builder()
                .id(boletoCreado.getPasajeroEntity().getId())
                .nombre(boletoCreado.getPasajeroEntity().getNombre())
                .apellido(boletoCreado.getPasajeroEntity().getApellido())
                .build();
    }
    */

}
