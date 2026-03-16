package com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cozinha.Request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record UpdateCozinhaRequestDto(

        @NotBlank
        @Column(unique = true)
        String nome
) {
}
