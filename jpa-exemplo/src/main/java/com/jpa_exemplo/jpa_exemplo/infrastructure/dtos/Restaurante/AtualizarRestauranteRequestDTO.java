package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante;

import java.math.BigDecimal;

public record AtualizarRestauranteRequestDTO(String nome, BigDecimal taxaFrete, ResumoCozinhaRequestDTO cozinha, ResumoEnderecoRequestDTO endereco) {
}
