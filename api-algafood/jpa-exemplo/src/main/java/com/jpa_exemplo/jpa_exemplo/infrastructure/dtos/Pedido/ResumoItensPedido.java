package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido;

import java.math.BigDecimal;

public record ResumoItensPedido(Long produtoId, String produtoNome, Integer quantidade, BigDecimal precoUnitario, BigDecimal precoTotal, String observacao) {
}
