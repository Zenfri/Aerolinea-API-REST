package com.example.demo.pasajero;

import com.example.demo.respuesta.CuerpoResponse;
import com.example.demo.util.Constantes.CodigosHttp;
import com.example.demo.util.Constantes.Mensajes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PasajeroServiceImpl implements IPasajeroService {

    private IPasajeroRepository iPasajeroRepository;

    public PasajeroServiceImpl(IPasajeroRepository iPasajeroRepository){
        this.iPasajeroRepository = iPasajeroRepository;
    }
    @Override
    public CuerpoResponse<PasajeroResponse> guardarPasajero(PasajeroRequest pasajeroRequest) {
        //EARLY RETURNS:
        //LAS VALIDACIONES VAN PRIMERO, LA LOGICA DEL NEGOCIO DESPUES
        if (pasajeroRequest == null){
            return new CuerpoResponse<>(
                    CodigosHttp.SOLICITUD_INCORRECTA,
                    Mensajes.ERROR_CREACION + ". Se enviaron datos vacíos.",
                    Optional.empty()
            );
        }

        //REQUERIMIENTOS
        ArrayList<String> errores = validarPasajero(pasajeroRequest);

        //Si falló en algún requerimiento
        if(!errores.isEmpty()){
            errores.add(Mensajes.ERROR_CREACION);
            return new CuerpoResponse<>(
                    CodigosHttp.SOLICITUD_INCORRECTA,
                    String.join(". ",errores),
                    Optional.empty()
            );
        }

        //Si NO falló en ningún requerimiento

        //Crear Entity a partir del Request
        PasajeroEntity pasajeroEntity = convertirAEntitySinId(pasajeroRequest);

        //guardar
        PasajeroEntity pasajeroGuardado = this.iPasajeroRepository.save(pasajeroEntity);

        //Crear Response
        PasajeroResponse pasajeroResponse = convertirAResponse(pasajeroGuardado);

        return new CuerpoResponse<>(
                CodigosHttp.CREADO,
                Mensajes.EXITO_CREACION,
                Optional.of(pasajeroResponse)
        );
    }

    @Override
    public CuerpoResponse<PasajeroResponse> buscarPasajeroPorId(Long id) {
        if(id == null){
            return new CuerpoResponse<>(
                    CodigosHttp.SOLICITUD_INCORRECTA,
                    Mensajes.ERROR_BUSQUEDA + ". Id vacío. ",
                    Optional.empty()
            );
        }

        Optional<PasajeroEntity> pasajeroEntityOptional = this.iPasajeroRepository.findById(id);

        //Si no se encontró
        if(pasajeroEntityOptional.isEmpty()){
            return new CuerpoResponse<>(
                    CodigosHttp.NO_ENCONTRADO,
                    Mensajes.ERROR_BUSQUEDA,
                    Optional.empty()
            );
        }

        //Si se encontró
        PasajeroResponse pasajeroResponse = convertirAResponse(pasajeroEntityOptional.get());

        return new CuerpoResponse<>(
                CodigosHttp.EXITO,
                Mensajes.EXITO_BUSQUEDA,
                Optional.of(pasajeroResponse)
        );

    }

    @Override
    public CuerpoResponse<PasajeroResponse> actualizarPasajero(Long id, PasajeroRequest pasajeroRequest) {
        //Si el objeto es null
        if (pasajeroRequest == null || id == null) {
            return new CuerpoResponse<>(
                    CodigosHttp.SOLICITUD_INCORRECTA,
                    Mensajes.ERROR_ACTUALIZACION+". Se enviaron datos vacíos.",
                    Optional.empty()
            );
        }

        Optional<PasajeroEntity> pasajeroEncontrado = this.iPasajeroRepository.findById(id);
        //Validar si existe
        if(pasajeroEncontrado.isEmpty()){
            return new CuerpoResponse<>(
                    CodigosHttp.NO_ENCONTRADO,
                    Mensajes.ERROR_BUSQUEDA,
                    Optional.empty()
            );
        }

        //SI SE ENCONTRÓ EL ID
        //Validar Requerimientos
        ArrayList<String> errores = validarPasajero(pasajeroRequest);

        //Si falló en algún requerimiento
        if(!errores.isEmpty()){
            errores.add(Mensajes.ERROR_ACTUALIZACION);
            return new CuerpoResponse<>(
                    CodigosHttp.SOLICITUD_INCORRECTA,
                    String.join(". ",errores),
                    Optional.empty()
            );
        }

        //Si NO falló en ningún requerimiento
        //Crear Entity a partir del Encontrado para no cambiar atributos que no deberían ser actualizados
        PasajeroEntity pasajeroEntity = pasajeroEncontrado.get();
        pasajeroEntity.setNombre(pasajeroRequest.getNombre());
        pasajeroEntity.setApellido(pasajeroRequest.getApellido());
        //nunca se actualizará: fecha registro, y este ya vino con el "pasajeroEncontrado.get()"

        //actualizar
        PasajeroEntity pasajeroActualizado = this.iPasajeroRepository.save(pasajeroEntity);

        PasajeroResponse pasajeroResponse = convertirAResponse(pasajeroActualizado);

        return new CuerpoResponse<>(
                CodigosHttp.EXITO, //act exitosa + devolver recurso
                Mensajes.EXITO_ACTUALIZACION,
                Optional.of(pasajeroResponse)
        );

    }

    @Override
    public CuerpoResponse<PasajeroResponse> eliminarPasajero(Long id) {
        return null;
    }

    @Override
    public List<CuerpoResponse<PasajeroResponse>> listarPasajeros() {
        return null;
    }


    //Validar nombre y apellido - Cumplir Requerimientos
    private ArrayList<String> validarPasajero(PasajeroRequest pasajeroRequest){
        ArrayList<String> errores = new ArrayList<>();

        //Requerimientos
        //isEmpty no se puede aplicar sobre nulos
        if(pasajeroRequest.getNombre() == null || pasajeroRequest.getNombre().isEmpty()) {
            errores.add("El nombre está vacío");
        }else if(pasajeroRequest.getNombre().length() > 30)
            errores.add("El nombre excede la capacidad máxima de 30 caracteres");

        if(pasajeroRequest.getApellido() == null || pasajeroRequest.getApellido().isEmpty()) {
            errores.add("El apellido está vacío");
        } else if(pasajeroRequest.getApellido().length() > 30)
            errores.add("El apellido excede la capacidad máxima de 30 caracteres");

        return errores;
    }

    //Convertir Request a Entity
    private PasajeroEntity convertirAEntitySinId(PasajeroRequest pasajeroRequest){
        return PasajeroEntity.builder()
                .nombre(pasajeroRequest.getNombre())
                .apellido(pasajeroRequest.getApellido())
                .build();
    }


    //Convertir Entity a Response
    private PasajeroResponse convertirAResponse(PasajeroEntity pasajeroEntity){
        return PasajeroResponse.builder()
                .id(pasajeroEntity.getId())
                .nombre(pasajeroEntity.getNombre())
                .apellido(pasajeroEntity.getApellido())
                .build();
    }
}
