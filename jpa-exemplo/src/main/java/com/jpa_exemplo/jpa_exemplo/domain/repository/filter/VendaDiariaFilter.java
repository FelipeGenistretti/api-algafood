package com.jpa_exemplo.jpa_exemplo.domain.repository.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class VendaDiariaFilter {
    private Long restauranteId;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoInicio;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoFim; 
}
