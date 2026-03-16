package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.cidade;

import com.jpa_exemplo.jpa_exemplo.domain.model.Cidade;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cidade.Request.UpdateCidadeRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cidade.Response.UpdateCidadeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UpdateCidadeMapper {

    @Mapping(source = "estadoId", target = "estado.id")
    void updateEntity(UpdateCidadeRequestDTO dto, @MappingTarget Cidade cidade);

    @Mapping(source = "estado.id", target = "estadoId")
    @Mapping(source = "estado.nome", target = "nomeEstado")
    UpdateCidadeResponse toResponse(Cidade cidade);
}
