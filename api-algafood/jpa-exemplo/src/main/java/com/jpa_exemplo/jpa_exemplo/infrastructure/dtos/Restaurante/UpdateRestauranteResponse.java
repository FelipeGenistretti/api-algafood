package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante;

import java.math.BigDecimal;

public record UpdateRestauranteResponse(Long id, String nome, BigDecimal taxaFrete, Long cozinhaId) {
}
