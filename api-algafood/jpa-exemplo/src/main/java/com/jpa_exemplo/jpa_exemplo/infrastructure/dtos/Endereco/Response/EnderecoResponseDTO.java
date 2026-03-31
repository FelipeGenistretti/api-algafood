package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Endereco.Response;


import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cidade.Response.CreateCidadeResponse;


public record EnderecoResponseDTO(

      String cep,
      String logradouro,
      String numero,
      String complemento,
      String bairro,
      CidadeResumoResponseDTO cidade
) {

}
