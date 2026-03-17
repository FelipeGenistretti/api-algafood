package com.jpa_exemplo.jpa_exemplo.domain.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class VendaDiaria {

    private LocalDate data;
    private Long totalVendas;
    private BigDecimal totalFaturado;


}
