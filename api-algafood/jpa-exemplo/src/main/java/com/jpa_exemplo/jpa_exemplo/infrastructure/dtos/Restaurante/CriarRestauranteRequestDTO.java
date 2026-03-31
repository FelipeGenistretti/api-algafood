package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante;

import com.jpa_exemplo.jpa_exemplo.domain.model.Cozinha;
import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;

import java.math.BigDecimal;

public record CriarRestauranteRequestDTO(String nome, BigDecimal taxaFrete, Long cozinhaId) {}
