package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.restaurante;

import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.CriarRestauranteRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.CriarRestauranteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CriarRestauranteMapper {

    @Mapping(source = "endereco.cidade.estado.nome", target = "endereco.cidade.estado")
    CriarRestauranteResponse toResponse(Restaurante restaurante);

    Restaurante toEntity(CriarRestauranteRequestDTO request);

    void updateEntity(CriarRestauranteRequestDTO dto, @MappingTarget Restaurante restaurante);
}

