package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.produto;

import com.jpa_exemplo.jpa_exemplo.domain.model.Produto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Produto.ListProdutoResponseDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-16T20:32:58-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class ListProdutoMapperImpl implements ListProdutoMapper {

    @Override
    public ListProdutoResponseDTO toResponse(Produto entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        String descricao = null;
        BigDecimal preco = null;
        boolean ativo = false;

        id = entity.getId();
        nome = entity.getNome();
        descricao = entity.getDescricao();
        preco = entity.getPreco();
        ativo = entity.isAtivo();

        ListProdutoResponseDTO listProdutoResponseDTO = new ListProdutoResponseDTO( id, nome, descricao, preco, ativo );

        return listProdutoResponseDTO;
    }

    @Override
    public List<ListProdutoResponseDTO> toCollectionResponse(List<Produto> entidades) {
        if ( entidades == null ) {
            return null;
        }

        List<ListProdutoResponseDTO> list = new ArrayList<ListProdutoResponseDTO>( entidades.size() );
        for ( Produto produto : entidades ) {
            list.add( toResponse( produto ) );
        }

        return list;
    }
}
