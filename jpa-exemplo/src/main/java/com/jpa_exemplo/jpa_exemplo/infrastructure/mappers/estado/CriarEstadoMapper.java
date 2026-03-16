package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.estado;

import com.jpa_exemplo.jpa_exemplo.domain.model.Estado;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.estado.Request.CriarEstadoRequestDto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.estado.Response.CriarEstadoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CriarEstadoMapper {

    Estado toEntity(CriarEstadoRequestDto dto);

    CriarEstadoResponse toResponse(Estado estado);

}
