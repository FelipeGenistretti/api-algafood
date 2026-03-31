package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.grupo;

import com.jpa_exemplo.jpa_exemplo.domain.model.Grupo;
import com.jpa_exemplo.jpa_exemplo.domain.model.Permissao;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Grupo.GrupoResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Grupo.ListGroupResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Grupo.ListGroupsPermissaoResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ListGroupsMapper {
    GrupoResponseDTO toResponse(Grupo grupo);
    List<GrupoResponseDTO> toCollectionResponse(List<Grupo> entidades);
    List<ListGroupsPermissaoResponseDTO> toCollectionResponsePermitions(List<Permissao> entidades);

}
