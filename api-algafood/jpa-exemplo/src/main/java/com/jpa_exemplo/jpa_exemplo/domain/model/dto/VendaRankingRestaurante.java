package com.jpa_exemplo.jpa_exemplo.domain.model.dto;

import java.math.BigDecimal;
import java.sql.Date;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VendaRankingRestaurante {
    private String nomeRestaurante;
    private Long quantidade;
    private Date data;
    private BigDecimal totalFaturado;
}
