package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.grupo;

import com.jpa_exemplo.jpa_exemplo.domain.model.Grupo;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Grupo.CreateGroupRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Grupo.CreateGrupoResponseDTO;
import org.mapstruct.Mapper;

import javax.swing.*;

@Mapper(componentModel = "spring")
public interface CreateGroupMapper {
     Grupo toEntity(CreateGroupRequestDTO dto);
    CreateGrupoResponseDTO toResponse(Grupo entity);
}
