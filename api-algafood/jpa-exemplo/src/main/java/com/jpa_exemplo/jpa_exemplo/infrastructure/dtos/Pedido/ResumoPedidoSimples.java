package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido;

import java.math.BigDecimal;

public record ResumoPedidoSimples(Long produtoId, Integer quantidade, String observacao) {
}
