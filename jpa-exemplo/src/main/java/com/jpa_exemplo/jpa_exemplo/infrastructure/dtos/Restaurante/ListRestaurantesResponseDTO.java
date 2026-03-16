package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonView;
import com.jpa_exemplo.jpa_exemplo.domain.model.view.RestauranteView;

public record ListRestaurantesResponseDTO(

        @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
        Long id,


        @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
        String nome,
        
        @JsonView(RestauranteView.Resumo.class)
        BigDecimal taxaFrete,

        @JsonView(RestauranteView.Resumo.class)
        ListCozinhaRestauranteResponseDTO cozinha,
        
        OffsetDateTime dataCadastro,
        OffsetDateTime dataAtualizacao,
        boolean ativo,
        boolean aberto,

        ListEnderecoRestauranteResponseDTO endereco
) {}
