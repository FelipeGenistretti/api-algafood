package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.FormaDePagamento.Response;

import java.util.List;

public record FormaDePagamentoResponseDTO(
        Long id, String descricao, List<RestauranteResumoResponse> restaurantes
) {
}
