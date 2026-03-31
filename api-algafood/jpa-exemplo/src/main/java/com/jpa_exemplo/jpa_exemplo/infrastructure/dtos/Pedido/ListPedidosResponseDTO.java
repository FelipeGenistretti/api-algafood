package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido;

import com.jpa_exemplo.jpa_exemplo.core.validation.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public record ListPedidosResponseDTO(Long id,
                                     BigDecimal subtotal,
                                     BigDecimal taxaFrete,
                                     BigDecimal valorTotal,
                                     StatusPedido status,
                                     OffsetDateTime dataCriacao,
                                     OffsetDateTime dataConfirmacao,
                                     OffsetDateTime dataEntrega,
                                     OffsetDateTime dataCancelamento,
                                     ResumoRestauranteDTO restaurante,
                                     ResumoClienteDTO cliente,
                                     ResumoFormaPagamento formaPagamento,
                                     ResumoEnderecoEntregaCriarPedido enderecoEntrega,
                                     List<ResumoItensPedido> itens
                                     ) {
}
