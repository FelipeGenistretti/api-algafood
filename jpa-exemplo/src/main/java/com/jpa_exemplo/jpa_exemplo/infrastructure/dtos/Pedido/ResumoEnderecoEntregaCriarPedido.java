package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido;

public record ResumoEnderecoEntregaCriarPedido(String cep, String logradouro, String numero, String complemento, String bairro, ResumoCidadeId cidade) {
}
