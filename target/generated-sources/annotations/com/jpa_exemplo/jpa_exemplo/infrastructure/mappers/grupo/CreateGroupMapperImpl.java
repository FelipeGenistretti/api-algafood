package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.grupo;

import com.jpa_exemplo.jpa_exemplo.domain.model.Grupo;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Grupo.CreateGroupRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Grupo.CreateGrupoResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-19T22:41:43-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class CreateGroupMapperImpl implements CreateGroupMapper {

    @Override
    public Grupo toEntity(CreateGroupRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Grupo grupo = new Grupo();

        grupo.setNome( dto.nome() );

        return grupo;
    }

    @Override
    public CreateGrupoResponseDTO toResponse(Grupo entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String nome = null;

        id = entity.getId();
        nome = entity.getNome();

        CreateGrupoResponseDTO createGrupoResponseDTO = new CreateGrupoResponseDTO( id, nome );

        return createGrupoResponseDTO;
    }
}
