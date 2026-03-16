package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.produto;

import com.jpa_exemplo.jpa_exemplo.domain.model.Produto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Produto.UpdateProdutoRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Produto.UpdateProdutoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UpdateProdutoMapper {
    void updateEntity(UpdateProdutoRequestDTO dto, @MappingTarget Produto entity);
    UpdateProdutoResponseDTO toResponse(Produto entity);
}
