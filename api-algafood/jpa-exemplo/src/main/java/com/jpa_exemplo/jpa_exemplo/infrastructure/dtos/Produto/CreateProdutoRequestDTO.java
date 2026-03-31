package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Produto;

import java.math.BigDecimal;

public record CreateProdutoRequestDTO(String nome, String descricao, BigDecimal preco, boolean ativo) {
}
