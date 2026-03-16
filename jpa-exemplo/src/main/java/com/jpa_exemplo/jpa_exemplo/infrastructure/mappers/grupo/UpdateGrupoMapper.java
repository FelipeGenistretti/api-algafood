package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.grupo;

import com.jpa_exemplo.jpa_exemplo.domain.model.Grupo;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Grupo.UpdateGrupoRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Grupo.UpdateGrupoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UpdateGrupoMapper
{

    Grupo updateEntity(UpdateGrupoRequestDTO dto, @MappingTarget Grupo grupo);

    UpdateGrupoResponseDTO toResponse(Grupo grupo);


}
