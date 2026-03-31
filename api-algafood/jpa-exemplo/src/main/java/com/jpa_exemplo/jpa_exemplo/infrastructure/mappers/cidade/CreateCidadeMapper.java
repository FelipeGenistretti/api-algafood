package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.cidade;

import com.jpa_exemplo.jpa_exemplo.domain.model.Cidade;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cidade.Request.CreateCidadeRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cidade.Response.CreateCidadeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateCidadeMapper {

    @Mapping(target = "estado.id", source = "estadoId")
    Cidade toEntity(CreateCidadeRequestDTO dto);

    @Mapping(target = "estadoId", source = "estado.id")
    @Mapping(target = "nomeEstado", source = "estado.nome")
    CreateCidadeResponse toResponse(Cidade cidade);

}
