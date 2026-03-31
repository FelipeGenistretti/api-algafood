package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante;

import java.math.BigDecimal;
import java.math.BigInteger;

public record UpdateRestauranteRequestDTO(String nome, BigDecimal taxaFrete, Long cozinhaId) {
}
