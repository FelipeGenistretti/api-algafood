package com.jpa_exemplo.jpa_exemplo.domain.service;

import java.util.List;

import com.jpa_exemplo.jpa_exemplo.domain.model.dto.VendaDiaria;
import com.jpa_exemplo.jpa_exemplo.domain.repository.filter.VendaDiariaFilter;

public interface VendaQueryService {
    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
