package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.produto;

import com.jpa_exemplo.jpa_exemplo.domain.model.Produto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Produto.ListProdutoResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ListProdutoMapper {
    ListProdutoResponseDTO toResponse(Produto entity);
    List<ListProdutoResponseDTO> toCollectionResponse(List<Produto> entidades);
}
