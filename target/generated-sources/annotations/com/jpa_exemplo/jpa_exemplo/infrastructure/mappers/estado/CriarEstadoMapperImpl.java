package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.estado;

import com.jpa_exemplo.jpa_exemplo.domain.model.Estado;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.estado.Request.CriarEstadoRequestDto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.estado.Response.CriarEstadoResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-04T22:14:29-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class CriarEstadoMapperImpl implements CriarEstadoMapper {

    @Override
    public Estado toEntity(CriarEstadoRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Estado estado = new Estado();

        estado.setNome( dto.nome() );

        return estado;
    }

    @Override
    public CriarEstadoResponse toResponse(Estado estado) {
        if ( estado == null ) {
            return null;
        }

        Long id = null;
        String nome = null;

        id = estado.getId();
        nome = estado.getNome();

        CriarEstadoResponse criarEstadoResponse = new CriarEstadoResponse( id, nome );

        return criarEstadoResponse;
    }
}
