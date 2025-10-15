package com.example.demo.aerolinea;

import com.example.demo.excepciones.RecursoExistenteExcepcion;
import com.example.demo.excepciones.RecursoNoEncontradoExcepcion;
import com.example.demo.excepciones.ValidacionExcepcion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class AerolineaServiceImpl implements IAerolineaService {
    private final IAerolineaRepository iAerolineaRepository;
    private final IAerolineaMapper mapper;

    public AerolineaServiceImpl(IAerolineaRepository iAerolineaRepository, IAerolineaMapper mapper){
        this.iAerolineaRepository = iAerolineaRepository;
        this.mapper = mapper;
    }
    @Transactional
    @Override
    public AerolineaResponse guardar(AerolineaRequest request) {
        validarNombreDuplicado(request.getNombre());

        AerolineaEntity aerolineaEntity = this.mapper.toEntity(request);

        AerolineaEntity aerolineaGuardada = this.iAerolineaRepository.save(aerolineaEntity);

        return this.mapper.toResponse(aerolineaGuardada);
    }

    @Override
    public List<AerolineaResponse> listar() {
        List<AerolineaEntity> aerolineas = iAerolineaRepository.findAll();
        return aerolineas.stream().map(mapper::toResponse).toList();
    }

    @Override
    public AerolineaResponse buscarPorId(Long id) {
        //buscar por id
        AerolineaEntity aerolineaEntity = buscarId(id);

        return mapper.toResponse(aerolineaEntity);
    }

    @Transactional
    @Override
    public AerolineaResponse actualizarPorId(Long id, AerolineaRequest request) {

        //Buscar y obtener aerolinea por id
        AerolineaEntity aerolineaEntity = buscarId(id);

        //Validar solo si el nombre es diferente, caso contrario, ya había pasado la prueba y no modificará este nombre
        if(!aerolineaEntity.getNombre().equals(request.getNombre())){
            //verificar que el nombre no se repita en algún otro registro excepto este mismo registro
            validarNombreDuplicadoIgnorandoIdActual(request.getNombre(), id);
        }

        //Asignar los valores necesarios para no perder los que no se modificarán
        aerolineaEntity.setNombre(request.getNombre());

        //Actualizar
        AerolineaEntity aerolineaActualizada = this.iAerolineaRepository.save(aerolineaEntity);

        //Devolver
        return mapper.toResponse(aerolineaActualizada);
    }

    private AerolineaEntity buscarId(Long id){
        Optional<AerolineaEntity> aerolineaEntityOptional = this.iAerolineaRepository.findById(id);
        if(aerolineaEntityOptional.isEmpty()){
            throw new RecursoNoEncontradoExcepcion("No se encontró el ID");
        }
        return aerolineaEntityOptional.get();
    }

    private void validarNombreDuplicado(String nombre){
        if(iAerolineaRepository.existsByNombre(nombre)){
            throw new RecursoExistenteExcepcion("El nombre de la Aerolínea ya existe");
        }
    }

    private void validarNombreDuplicadoIgnorandoIdActual(String nombre, Long id){
        if(iAerolineaRepository.existsByNombreAndIdNot(nombre, id)){
            throw new RecursoExistenteExcepcion("El nombre ya existe en otro registro");
        }
    }


}
