package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante;

import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Endereco.Response.EnderecoResponseDTO;

import java.math.BigDecimal;

public record CriarRestauranteResponse(Long id, String nome, BigDecimal taxaFrete, CriarRestauranteCozinhaResponse cozinha, Boolean ativo, EnderecoResponseDTO endereco) { }
