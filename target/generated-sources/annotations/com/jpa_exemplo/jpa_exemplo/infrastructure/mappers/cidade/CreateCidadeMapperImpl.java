package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.cidade;

import com.jpa_exemplo.jpa_exemplo.domain.model.Cidade;
import com.jpa_exemplo.jpa_exemplo.domain.model.Estado;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cidade.Request.CreateCidadeRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cidade.Response.CreateCidadeResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-19T22:41:43-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class CreateCidadeMapperImpl implements CreateCidadeMapper {

    @Override
    public Cidade toEntity(CreateCidadeRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Cidade cidade = new Cidade();

        cidade.setEstado( createCidadeRequestDTOToEstado( dto ) );
        cidade.setNome( dto.nome() );

        return cidade;
    }

    @Override
    public CreateCidadeResponse toResponse(Cidade cidade) {
        if ( cidade == null ) {
            return null;
        }

        Long estadoId = null;
        String nomeEstado = null;
        Long id = null;
        String nome = null;

        estadoId = cidadeEstadoId( cidade );
        nomeEstado = cidadeEstadoNome( cidade );
        id = cidade.getId();
        nome = cidade.getNome();

        CreateCidadeResponse createCidadeResponse = new CreateCidadeResponse( id, nome, estadoId, nomeEstado );

        return createCidadeResponse;
    }

    protected Estado createCidadeRequestDTOToEstado(CreateCidadeRequestDTO createCidadeRequestDTO) {
        if ( createCidadeRequestDTO == null ) {
            return null;
        }

        Estado estado = new Estado();

        estado.setId( createCidadeRequestDTO.estadoId() );

        return estado;
    }

    private Long cidadeEstadoId(Cidade cidade) {
        if ( cidade == null ) {
            return null;
        }
        Estado estado = cidade.getEstado();
        if ( estado == null ) {
            return null;
        }
        Long id = estado.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String cidadeEstadoNome(Cidade cidade) {
        if ( cidade == null ) {
            return null;
        }
        Estado estado = cidade.getEstado();
        if ( estado == null ) {
            return null;
        }
        String nome = estado.getNome();
        if ( nome == null ) {
            return null;
        }
        return nome;
    }
}
