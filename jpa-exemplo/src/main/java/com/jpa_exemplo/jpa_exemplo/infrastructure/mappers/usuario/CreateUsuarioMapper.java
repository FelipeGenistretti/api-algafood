package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.usuario;

import com.jpa_exemplo.jpa_exemplo.domain.model.Usuario;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Usuario.CreateUsuarioRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Usuario.CreateUsuarioResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateUsuarioMapper {

    Usuario toEntity(CreateUsuarioRequestDTO dto);
    CreateUsuarioResponseDTO toResponse(Usuario entity);

}
