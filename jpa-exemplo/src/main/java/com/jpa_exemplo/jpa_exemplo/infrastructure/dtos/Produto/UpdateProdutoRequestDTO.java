package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Produto;

import java.math.BigDecimal;

public record UpdateProdutoRequestDTO(String nome, String descricao, BigDecimal preco, Boolean ativo) {
}
