package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante;

public record ResumoEnderecoRequestDTO(String cep, String logradouro, String numero, String complemento, String bairro, ResumoCidadeIdRequestDTO cidade) {
}
