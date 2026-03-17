package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.cidade;

import com.jpa_exemplo.jpa_exemplo.domain.model.Cidade;
import com.jpa_exemplo.jpa_exemplo.domain.model.Estado;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cidade.Request.UpdateCidadeRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cidade.Response.UpdateCidadeResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-16T20:32:58-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class UpdateCidadeMapperImpl implements UpdateCidadeMapper {

    @Override
    public void updateEntity(UpdateCidadeRequestDTO dto, Cidade cidade) {
        if ( dto == null ) {
            return;
        }

        if ( cidade.getEstado() == null ) {
            cidade.setEstado( new Estado() );
        }
        updateCidadeRequestDTOToEstado( dto, cidade.getEstado() );
        cidade.setNome( dto.nome() );
    }

    @Override
    public UpdateCidadeResponse toResponse(Cidade cidade) {
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

        UpdateCidadeResponse updateCidadeResponse = new UpdateCidadeResponse( id, nome, estadoId, nomeEstado );

        return updateCidadeResponse;
    }

    protected void updateCidadeRequestDTOToEstado(UpdateCidadeRequestDTO updateCidadeRequestDTO, Estado mappingTarget) {
        if ( updateCidadeRequestDTO == null ) {
            return;
        }

        mappingTarget.setId( updateCidadeRequestDTO.estadoId() );
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
