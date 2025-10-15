package com.example.demo.piloto;

import com.example.demo.aerolinea.AerolineaEntity;
import com.example.demo.aerolinea.AerolineaResponse;
import com.example.demo.aerolinea.IAerolineaRepository;
import com.example.demo.respuesta.CuerpoResponse;
import com.example.demo.util.Constantes.CodigosHttp;
import com.example.demo.util.Constantes.Mensajes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PilotoServiceImpl implements IPilotoService{
    private IPilotoRepository iPilotoRepository;
    private IAerolineaRepository iAerolineaRepository;

    public PilotoServiceImpl(IPilotoRepository iPilotoRepository, IAerolineaRepository iAerolineaRepository){
        this.iPilotoRepository = iPilotoRepository;
        this.iAerolineaRepository = iAerolineaRepository;
    }
    @Override
    public CuerpoResponse<PilotoResponse> guardarPiloto(PilotoRequest pilotoRequest) {

        if(pilotoRequest == null){
            return new CuerpoResponse<>(
                    CodigosHttp.SOLICITUD_INCORRECTA,
                    Mensajes.ERROR_CREACION,
                    Optional.empty()
            );
        }

        //Validacion de PilotoRequest
        List<String> errores = validarPilotoRequest(pilotoRequest);

        if(!errores.isEmpty()){
            return new CuerpoResponse<>(
                    CodigosHttp.SOLICITUD_INCORRECTA,
                    Mensajes.ERROR_CREACION + " " + String.join(". ",errores),
                    Optional.empty()
            );
        }

        Long idAerolinea = pilotoRequest.getIdAerolinea();

        Optional<AerolineaEntity> aerolineaEntityOptional = this.iAerolineaRepository.findById(idAerolinea);

        //Si no se encotró el id de la Aerolinea
        if(aerolineaEntityOptional.isEmpty()){
            return new CuerpoResponse<>(
                    CodigosHttp.NO_ENCONTRADO,
                    Mensajes.ERROR_BUSQUEDA,
                    Optional.empty()
            );
        }

        //Si SE ENCONTRÓ el id de la Aerolinea


        PilotoEntity pilotoEntity = new PilotoEntity();
        pilotoEntity.setNombre(pilotoRequest.getNombres());
        pilotoEntity.setApellido(pilotoRequest.getApellidos());
        pilotoEntity.setEstado(true);
        pilotoEntity.setAerolineaEntity(aerolineaEntityOptional.get());

        PilotoEntity pilotoEntityGuardado = this.iPilotoRepository.save(pilotoEntity);

        AerolineaEntity aerolineaEncontrada = aerolineaEntityOptional.get();

        AerolineaResponse aerolineaResponse = AerolineaResponse.builder()
                .id(aerolineaEncontrada.getId())
                .nombre(aerolineaEncontrada.getNombre())
                .build();

        PilotoResponse pilotoResponse = PilotoResponse.builder()
                .id(pilotoEntityGuardado.getId())
                .nombre(pilotoEntityGuardado.getNombre())
                .apellido(pilotoEntityGuardado.getApellido())
                .estado(pilotoEntityGuardado.getEstado())
                .aerolineaResponse(aerolineaResponse)
                .build();

        return new CuerpoResponse<>(
                CodigosHttp.CREADO,
                Mensajes.EXITO_CREACION,
                Optional.of(pilotoResponse)
        );
    }

    private List<String> validarPilotoRequest(PilotoRequest pilotoRequest){
        List<String> errores = new ArrayList<>();
        if(pilotoRequest.getNombres() == null || pilotoRequest.getNombres().trim().isEmpty()){
            errores.add("El nombre no puede ser vacío");
        }
        //Validar que la longitud del nombre no supere los 30 caracteres
        else if(pilotoRequest.getNombres().length() > 30){
            errores.add("El nombre no puede exceder los 30 caracteres");
        }

        if(pilotoRequest.getApellidos() == null || pilotoRequest.getApellidos().trim().isEmpty()){
            errores.add("El apellido no puede ser vacío");
        }else if(pilotoRequest.getApellidos().length() > 30){
            errores.add("El apellido no puede exceder los 30 caracteres");
        }

        if(pilotoRequest.getIdAerolinea() == null){
            errores.add("El id de la Aerolínea no puede ser vacío");
        } else if (pilotoRequest.getIdAerolinea() <= 0) {
            errores.add("El id de la Aerolínea debe ser mayor que cero");
        }
        return errores;
    }


    @Override
    public CuerpoResponse<PilotoResponse> buscarPorId(Long id) {
        Optional<PilotoEntity> pilotoOptional = this.iPilotoRepository.findById(id);
        //Si no se encontró
        if(pilotoOptional.isEmpty()) {
            //El estandar dice que solo <> es la mejor forma para devolver esta respuesta "sin especificar el tipo, que sería redundante"
            return new CuerpoResponse<>(
                    404,
                    "No se encontró el Piloto con el id: "+id,
                    Optional.empty()
            );
        }
        return null;
        //Si se encontró
        /*return new CuerpoRespuesta<>(
                200,
                "Se encontró el Piloto con el id: "+id,
                pilotoOptional
        );*/
    }

    @Override
    public CuerpoResponse<List<PilotoResponse>> buscarPorNombre(String nombre) {

        if(nombre == null || nombre.trim().isEmpty()){
            return new CuerpoResponse<>(
                    CodigosHttp.SOLICITUD_INCORRECTA,
                    Mensajes.ERROR_BUSQUEDA + ". El nombre no puede estar vacío",
                    Optional.empty()
            );
        }

        List<PilotoEntity> lPilotosOptional = this.iPilotoRepository.findByNombreContainingIgnoreCase(nombre);
        if(lPilotosOptional.isEmpty()){
            return new CuerpoResponse<>(
                    CodigosHttp.NO_ENCONTRADO,
                    Mensajes.ERROR_BUSQUEDA,
                    Optional.empty()
            );
        }
        return  null;
        /*
        return new CuerpoRespuesta<>(
                CodigosHttp.EXITO,
                Mensajes.EXITO_BUSQUEDA,
                Optional.of(lPilotosOptional)
        );*/

    }

    @Override
    public List<PilotoEntity> listar() {
        return this.iPilotoRepository.findAll();
    }
}
