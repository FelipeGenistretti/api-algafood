package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.restaurante;

import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.ListRestaurantesResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ListRestauranteMapper {
    ListRestaurantesResponseDTO toResponse(Restaurante restaurante);
    List<ListRestaurantesResponseDTO> toCollectionResponse(List<Restaurante> restaurantes);
}
