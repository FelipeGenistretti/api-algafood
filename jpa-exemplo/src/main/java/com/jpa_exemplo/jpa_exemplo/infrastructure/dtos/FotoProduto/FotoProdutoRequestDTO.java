package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.FotoProduto;

import com.jpa_exemplo.jpa_exemplo.core.validation.FileContentType;
import com.jpa_exemplo.jpa_exemplo.core.validation.FileSize;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import javax.print.attribute.standard.Media;

public record FotoProdutoRequestDTO(

    @NotNull
    @FileSize(max = "500KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    MultipartFile arquivo,
    
    @NotBlank
    String descricao
) {

}
