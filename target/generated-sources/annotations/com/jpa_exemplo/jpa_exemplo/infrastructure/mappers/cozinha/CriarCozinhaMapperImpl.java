package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.cozinha;

import com.jpa_exemplo.jpa_exemplo.domain.model.Cozinha;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cozinha.Request.CriarCozinhaRequestDto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cozinha.Response.CriarCozinhaResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-16T20:32:58-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class CriarCozinhaMapperImpl implements CriarCozinhaMapper {

    @Override
    public Cozinha toEntity(CriarCozinhaRequestDto request) {
        if ( request == null ) {
            return null;
        }

        Cozinha cozinha = new Cozinha();

        cozinha.setNome( request.nome() );

        return cozinha;
    }

    @Override
    public CriarCozinhaResponse toModel(Cozinha cozinha) {
        if ( cozinha == null ) {
            return null;
        }

        Long id = null;
        String nome = null;

        id = cozinha.getId();
        nome = cozinha.getNome();

        CriarCozinhaResponse criarCozinhaResponse = new CriarCozinhaResponse( id, nome );

        return criarCozinhaResponse;
    }
}
