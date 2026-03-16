package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.restaurante;

import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.AtualizarRestauranteRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.UpdateRestauranteRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.UpdateRestauranteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UpdateRestauranteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cozinha", ignore = true)
    @Mapping(target = "endereco.cidade", ignore = true)
    void updateEntity(AtualizarRestauranteRequestDTO request,
                      @MappingTarget Restaurante restaurante);
}