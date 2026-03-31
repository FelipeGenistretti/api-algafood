package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido;

import java.math.BigDecimal;
import java.util.List;

public record CriarPedidoResquestDTO(
        Long clienteId,
        Long restauranteId,
        Long formaPagamentoId,
        ResumoEnderecoEntrega enderecoEntrega,
        List<ResumoPedidoSimples> itens
) {}