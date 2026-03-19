package com.jpa_exemplo.jpa_exemplo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jpa_exemplo.jpa_exemplo.domain.model.dto.VendaDiaria;
import com.jpa_exemplo.jpa_exemplo.domain.model.dto.VendaRankingRestaurante;
import com.jpa_exemplo.jpa_exemplo.domain.repository.filter.VendaDiariaFilter;
import com.jpa_exemplo.jpa_exemplo.domain.repository.filter.VendaPorRestauranteFilter;
import com.jpa_exemplo.jpa_exemplo.domain.service.VendaPorRestauranteQueryService;
import com.jpa_exemplo.jpa_exemplo.domain.service.VendaQueryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path="/estatisticas")
public class EstatisticasController {

    @Autowired
    private VendaQueryService vendaQueryService;

    @Autowired
    private VendaPorRestauranteQueryService vendaPorRestauranteQueryService;

    @GetMapping("/vendas-diarias")
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
        
    }

    @GetMapping("vendas-por-restaurante")
    public List<VendaRankingRestaurante> consultarVendaPorRestaurante(VendaPorRestauranteFilter filtro, @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return vendaPorRestauranteQueryService.consultarVendaPorRestaurante(filtro, timeOffset);
    }

}
