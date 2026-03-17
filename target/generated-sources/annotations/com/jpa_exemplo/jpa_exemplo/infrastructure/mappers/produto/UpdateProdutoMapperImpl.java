package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.produto;

import com.jpa_exemplo.jpa_exemplo.domain.model.Produto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Produto.UpdateProdutoRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Produto.UpdateProdutoResponseDTO;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-16T20:32:58-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class UpdateProdutoMapperImpl implements UpdateProdutoMapper {

    @Override
    public void updateEntity(UpdateProdutoRequestDTO dto, Produto entity) {
        if ( dto == null ) {
            return;
        }

        entity.setNome( dto.nome() );
        entity.setDescricao( dto.descricao() );
        entity.setPreco( dto.preco() );
        if ( dto.ativo() != null ) {
            entity.setAtivo( dto.ativo() );
        }
    }

    @Override
    public UpdateProdutoResponseDTO toResponse(Produto entity) {
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

        UpdateProdutoResponseDTO updateProdutoResponseDTO = new UpdateProdutoResponseDTO( id, nome, descricao, preco, ativo );

        return updateProdutoResponseDTO;
    }
}
