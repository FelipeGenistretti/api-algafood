package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.cozinha;

import com.jpa_exemplo.jpa_exemplo.domain.model.Cozinha;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cozinha.Request.UpdateCozinhaRequestDto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cozinha.Response.UpdateCozinhaResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-19T22:41:43-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class UpdateCozinhaMapperImpl implements UpdateCozinhaMapper {

    @Override
    public void updateEntity(UpdateCozinhaRequestDto dto, Cozinha cozinha) {
        if ( dto == null ) {
            return;
        }

        cozinha.setNome( dto.nome() );
    }

    @Override
    public UpdateCozinhaResponse toModel(Cozinha cozinha) {
        if ( cozinha == null ) {
            return null;
        }

        Long id = null;
        String nome = null;

        id = cozinha.getId();
        nome = cozinha.getNome();

        UpdateCozinhaResponse updateCozinhaResponse = new UpdateCozinhaResponse( id, nome );

        return updateCozinhaResponse;
    }
}
