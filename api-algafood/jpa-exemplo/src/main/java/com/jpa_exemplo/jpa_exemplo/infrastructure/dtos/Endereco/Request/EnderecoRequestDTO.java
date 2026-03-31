package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Endereco.Request;

public record EnderecoRequestDTO(
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        Long cidadeId
) {
}
