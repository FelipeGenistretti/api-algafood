package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.cozinha;

import com.jpa_exemplo.jpa_exemplo.domain.model.Cozinha;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cozinha.Request.CriarCozinhaRequestDto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cozinha.Response.CriarCozinhaResponse;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CriarCozinhaMapper {

    Cozinha toEntity(CriarCozinhaRequestDto request);

    CriarCozinhaResponse toModel(Cozinha cozinha);
    List<CriarCozinhaResponse> toCollectionResponse(List<Cozinha> cozinhas);

}
