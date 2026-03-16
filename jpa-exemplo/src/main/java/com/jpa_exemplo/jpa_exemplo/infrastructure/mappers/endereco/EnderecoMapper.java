package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.endereco;

import com.jpa_exemplo.jpa_exemplo.domain.model.Endereco;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Endereco.Request.EnderecoRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Endereco.Response.EnderecoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    @Mapping(source = "cidadeId", target = "cidade.id")
    Endereco toEntity(EnderecoRequestDTO dto);

    @Mapping(source = "cidade.estado.nome", target = "cidade.estado")
    EnderecoResponseDTO toResponse(Endereco entity);

    List<EnderecoResponseDTO> toCollectionResponse(List<Endereco> endereco);

    @Mapping(source = "cidadeId", target = "cidade.id")
    void updateEntity(EnderecoRequestDTO dto, @MappingTarget Endereco endereco);
}
