package com.example.demo.pasajero;

import com.example.demo.piloto.Piloto;
import com.example.demo.piloto.PilotoRequest;
import com.example.demo.respuesta.CuerpoRespuesta;
import com.example.demo.util.Constantes.CodigosHttp;
import com.example.demo.util.Constantes.Mensajes;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
    public CuerpoRespuesta<PasajeroEntity> guardarPasajero(PasajeroEntity pasajeroEntity) {

        if (pasajeroEntity != null) {

            //Requerimientos
            ArrayList<String> errores = validarPasajero(pasajeroEntity);

            //Si falló en algún requerimiento
            if(!errores.isEmpty()){
                errores.add(Mensajes.ERROR_CREACION);
                return new CuerpoRespuesta<>(
                        CodigosHttp.SOLICITUD_INCORRECTA,
                        String.join(". ",errores),
                        Optional.empty()
                );
            }

            //Si NO falló en ningún requerimiento
            this.iPasajeroRepository.save(pasajeroEntity);

            return new CuerpoRespuesta<>(
                    CodigosHttp.CREADO,
                    Mensajes.EXITO_CREACION,
                    Optional.of(pasajeroEntity)
            );

        } else {
            return new CuerpoRespuesta<>(
                    CodigosHttp.SOLICITUD_INCORRECTA,
                    Mensajes.ERROR_CREACION + ". Se enviaron datos vacíos.",
                    Optional.empty()
            );
        }
    }

    @Override
    public CuerpoRespuesta<PasajeroEntity> buscarPasajeroPorId(Long id) {
        if(id != null){
            Optional<PasajeroEntity> pasajeroEntityOptional = this.iPasajeroRepository.findById(id);
            //Se encontró
            if(pasajeroEntityOptional.isPresent()){
                return new CuerpoRespuesta<>(
                        CodigosHttp.EXITO,
                        Mensajes.EXITO_BUSQUEDA,
                        pasajeroEntityOptional
                );
            }
            //No se encontró
            else
                return new CuerpoRespuesta<>(
                        CodigosHttp.NO_ENCONTRADO,
                        Mensajes.ERROR_BUSQUEDA,
                        Optional.empty()
                );
        }
        return new CuerpoRespuesta<>(
                CodigosHttp.SOLICITUD_INCORRECTA,
                Mensajes.ERROR_BUSQUEDA + ". Id vacío. ",
                Optional.empty()
        );
    }

    @Override
    public CuerpoRespuesta<PasajeroEntity> actualizarPasajero(PasajeroEntity pasajeroEntity) {
        //Si el objeto no es null
        if (pasajeroEntity != null) {

            //Requerimientos
            ArrayList<String> errores = validarPasajero(pasajeroEntity);

            //Si falló en algún requerimiento
            if(!errores.isEmpty()){
                errores.add(Mensajes.ERROR_ACTUALIZACION);
                return new CuerpoRespuesta<>(
                        CodigosHttp.SOLICITUD_INCORRECTA,
                        String.join(". ",errores),
                        Optional.empty()
                );
            }

            //Si NO falló en ningún requerimiento
            this.iPasajeroRepository.save(pasajeroEntity);

            return new CuerpoRespuesta<>(
                    CodigosHttp.EXITO, //act exitosa + devolver recurso
                    Mensajes.EXITO_ACTUALIZACION,
                    Optional.of(pasajeroEntity)
            );

        }
        //Si el objeto es null
        return new CuerpoRespuesta<>(
                CodigosHttp.SOLICITUD_INCORRECTA,
                Mensajes.ERROR_ACTUALIZACION+". Se enviaron datos vacíos.",
                Optional.empty()
        );
    }

    @Override
    public CuerpoRespuesta<PasajeroEntity> eliminarPasajero(Long id) {
        return null;
    }

    @Override
    public List<CuerpoRespuesta<PasajeroEntity>> listarPasajeros() {
        return null;
    }

    public ArrayList<String> validarPasajero(PasajeroEntity pasajeroEntity){
        ArrayList<String> errores = new ArrayList<>();

        //Requerimientos
        //isEmpty no se puede aplicar sobre nulos
        if(pasajeroEntity.getNombre() == null || pasajeroEntity.getNombre().isEmpty()) {
            errores.add("El nombre está vacío");
        }else if(pasajeroEntity.getNombre().length() > 30)
            errores.add("El nombre excede la capacidad máxima de 30 caracteres");

        if(pasajeroEntity.getApellido() == null || pasajeroEntity.getApellido().isEmpty()) {
            errores.add("El apellido está vacío");
        } else if(pasajeroEntity.getApellido().length() > 30)
            errores.add("El apellido excede la capacidad máxima de 30 caracteres");

        return errores;
    }
}
