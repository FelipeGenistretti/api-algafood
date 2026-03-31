package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cidade.Response;

public record CreateCidadeResponse(Long id, String nome, Long estadoId, String nomeEstado) {
}
