package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.grupo;

import com.jpa_exemplo.jpa_exemplo.domain.model.Grupo;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Grupo.UpdateGrupoRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Grupo.UpdateGrupoResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-04T22:14:29-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class UpdateGrupoMapperImpl implements UpdateGrupoMapper {

    @Override
    public Grupo updateEntity(UpdateGrupoRequestDTO dto, Grupo grupo) {
        if ( dto == null ) {
            return grupo;
        }

        grupo.setNome( dto.nome() );

        return grupo;
    }

    @Override
    public UpdateGrupoResponseDTO toResponse(Grupo grupo) {
        if ( grupo == null ) {
            return null;
        }

        Long id = null;
        String nome = null;

        id = grupo.getId();
        nome = grupo.getNome();

        UpdateGrupoResponseDTO updateGrupoResponseDTO = new UpdateGrupoResponseDTO( id, nome );

        return updateGrupoResponseDTO;
    }
}
