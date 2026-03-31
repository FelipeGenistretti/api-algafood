package com.jpa_exemplo.jpa_exemplo.domain.repository.filter;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
                                                                                                               
@Data                                                                                                               
public class VendaPorRestauranteFilter {                                                                                                               
                                                                                                               
    private Long restauranteId;                                                                                                               
                                                                                                               
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)                                                                                                               
    private OffsetDateTime dataCriacaoFim;                                                                                                               
                                                                                                               
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)                                                                                                               
    private OffsetDateTime dataCriacaoInicio;                                                                                                               
                                                                                                               
    private BigDecimal faturamentoMin;                                                                                                               
                                                                                                               
    private Long quantidadeMinima;                                                                                                               
                                                                                                               
    private Integer limite;                                                                                                               
                                                                                                               
}
