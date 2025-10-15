package com.example.demo.avion;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAvionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tiempoCreacion", ignore = true)
    @Mapping(target = "tiempoModificacion", ignore = true)
    AvionEntity toEntity(AvionRequest request);

    AvionResponse toResponse(AvionEntity entity);
}
