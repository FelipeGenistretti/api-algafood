package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.fotoProduto;

import com.jpa_exemplo.jpa_exemplo.domain.model.FotoProduto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.FotoProduto.FotoProdutoResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-20T18:53:40-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class FotoProdutoMapperImpl implements FotoProdutoMapper {

    @Override
    public FotoProdutoResponseDTO toResponse(FotoProduto foto) {
        if ( foto == null ) {
            return null;
        }

        String nomeArquivo = null;
        String descricao = null;
        String contentType = null;
        Long tamanho = null;

        nomeArquivo = foto.getNomeArquivo();
        descricao = foto.getDescricao();
        contentType = foto.getContentType();
        tamanho = foto.getTamanho();

        FotoProdutoResponseDTO fotoProdutoResponseDTO = new FotoProdutoResponseDTO( nomeArquivo, descricao, contentType, tamanho );

        return fotoProdutoResponseDTO;
    }
}
