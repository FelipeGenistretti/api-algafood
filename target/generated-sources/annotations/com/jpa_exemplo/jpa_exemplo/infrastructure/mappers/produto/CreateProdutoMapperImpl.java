package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.produto;

import com.jpa_exemplo.jpa_exemplo.domain.model.Produto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Produto.CreateProdutoRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Produto.CreateProdutoResponseDTO;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-16T20:32:58-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class CreateProdutoMapperImpl implements CreateProdutoMapper {

    @Override
    public Produto toEntity(CreateProdutoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Produto produto = new Produto();

        produto.setNome( dto.nome() );
        produto.setDescricao( dto.descricao() );
        produto.setPreco( dto.preco() );
        produto.setAtivo( dto.ativo() );

        return produto;
    }

    @Override
    public CreateProdutoResponseDTO toResponse(Produto entity) {
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

        CreateProdutoResponseDTO createProdutoResponseDTO = new CreateProdutoResponseDTO( id, nome, descricao, preco, ativo );

        return createProdutoResponseDTO;
    }
}
