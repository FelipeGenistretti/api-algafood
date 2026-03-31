package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante;

import com.fasterxml.jackson.annotation.JsonView;
import com.jpa_exemplo.jpa_exemplo.domain.model.view.RestauranteView;

public record ListCozinhaRestauranteResponseDTO(
    @JsonView(RestauranteView.Resumo.class)
    Long id, 

    @JsonView(RestauranteView.Resumo.class)
    String nome
) {
}
