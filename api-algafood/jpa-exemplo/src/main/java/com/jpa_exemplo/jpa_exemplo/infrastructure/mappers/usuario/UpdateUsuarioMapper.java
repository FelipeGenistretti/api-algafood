package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.usuario;

import com.jpa_exemplo.jpa_exemplo.domain.model.Usuario;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Usuario.UpdadeUsuarioResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Usuario.UpdateUsuarioRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UpdateUsuarioMapper
{
    Usuario updateEntity(UpdateUsuarioRequestDTO dto, @MappingTarget Usuario usuario);

    UpdadeUsuarioResponseDTO toResponse(Usuario usuario);
}
