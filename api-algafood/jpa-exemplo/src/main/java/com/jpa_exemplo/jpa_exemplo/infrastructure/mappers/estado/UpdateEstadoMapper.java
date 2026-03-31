package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.estado;

import com.jpa_exemplo.jpa_exemplo.domain.model.Estado;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cozinha.Request.UpdateCozinhaRequestDto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.estado.Request.UpdateEstadoRequest;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.estado.Response.UpdateEstadoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UpdateEstadoMapper {

    Estado updateEntity(UpdateEstadoRequest dto, @MappingTarget Estado estado);

    UpdateEstadoResponse toResponse(Estado estado);

}
