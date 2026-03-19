package com.jpa_exemplo.jpa_exemplo.domain.service;

import java.util.List;

import com.jpa_exemplo.jpa_exemplo.domain.model.dto.VendaRankingRestaurante;
import com.jpa_exemplo.jpa_exemplo.domain.repository.filter.VendaPorRestauranteFilter;

public interface VendaPorRestauranteQueryService {

    List<VendaRankingRestaurante> consultarVendaPorRestaurante(VendaPorRestauranteFilter filter, String offset);
    

}
