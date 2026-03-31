package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.fotoProduto;

import com.jpa_exemplo.jpa_exemplo.domain.model.FotoProduto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.FotoProduto.FotoProdutoResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FotoProdutoMapper {

    FotoProdutoResponseDTO toResponse(FotoProduto foto);

}
