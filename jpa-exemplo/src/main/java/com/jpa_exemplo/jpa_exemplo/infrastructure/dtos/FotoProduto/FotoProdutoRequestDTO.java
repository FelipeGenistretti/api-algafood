package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.FotoProduto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FotoProdutoRequestDTO(

    @NotNull
    MultipartFile arquivo,
    
    @NotBlank
    String descricao
) {

}
