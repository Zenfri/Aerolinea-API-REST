package com.example.demo.aerolinea;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IAerolineaMapper {
    //IAerolineaMapper INSTANCIA = Mappers.getMapper(IAerolineaMapper.class);

    @Mapping(target = "id", ignore = true) // Opcional pero buena práctica
    @Mapping(target = "tiempoCreacion", ignore = true)
    @Mapping(target = "tiempoModificacion", ignore = true)
    AerolineaEntity toEntity(AerolineaRequest aerolineaRequest);

    AerolineaResponse toResponse(AerolineaEntity aerolineaEntity);
}
