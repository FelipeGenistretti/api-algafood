package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ListRestaurantesResponseDTO(
        Long id,
        String nome,
        OffsetDateTime dataCadastro,
        OffsetDateTime dataAtualizacao,
        BigDecimal taxaFrete,
        boolean ativo,
        boolean aberto,
        ListCozinhaRestauranteResponseDTO cozinha,
        ListEnderecoRestauranteResponseDTO endereco
) {}
