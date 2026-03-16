package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.usuario;

import com.jpa_exemplo.jpa_exemplo.domain.model.Usuario;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Usuario.ListUsuariosResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ListUsuariosMapper {
    Usuario toEntity(ListUsuariosResponseDTO dto);

    ListUsuariosResponseDTO toResponse(Usuario entidade);

    List<ListUsuariosResponseDTO> toCollectionResponse(List<Usuario> entidades);
}
