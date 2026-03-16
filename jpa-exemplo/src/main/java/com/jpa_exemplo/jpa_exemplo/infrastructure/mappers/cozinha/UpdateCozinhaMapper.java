package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.cozinha;

import com.jpa_exemplo.jpa_exemplo.domain.model.Cozinha;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cozinha.Request.UpdateCozinhaRequestDto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cozinha.Response.UpdateCozinhaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UpdateCozinhaMapper {

    void updateEntity(
            UpdateCozinhaRequestDto dto,
            @MappingTarget Cozinha cozinha
    );

    UpdateCozinhaResponse toModel(Cozinha cozinha);
}
