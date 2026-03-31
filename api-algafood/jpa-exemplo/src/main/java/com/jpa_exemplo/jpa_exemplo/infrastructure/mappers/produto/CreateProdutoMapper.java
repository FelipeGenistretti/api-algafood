package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.produto;

import com.jpa_exemplo.jpa_exemplo.domain.model.Produto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Produto.CreateProdutoRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Produto.CreateProdutoResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateProdutoMapper {
    Produto toEntity(CreateProdutoRequestDTO dto);
    CreateProdutoResponseDTO toResponse(Produto entity);
}
