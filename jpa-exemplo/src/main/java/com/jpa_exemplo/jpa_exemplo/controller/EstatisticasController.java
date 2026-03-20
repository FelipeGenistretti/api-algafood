package com.jpa_exemplo.jpa_exemplo.controller;

import com.jpa_exemplo.jpa_exemplo.domain.service.VendaReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private VendaReportService vendaReportService;

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
    }

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultarVendasDiariasPdf(
            VendaDiariaFilter filtro,
            @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {

        byte[] bytesPdf = vendaReportService.emitirVendasDiarias(filtro, timeOffset);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesPdf);
    }

    @GetMapping("vendas-por-restaurante")
    public List<VendaRankingRestaurante> consultarVendaPorRestaurante(VendaPorRestauranteFilter filtro, @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return vendaPorRestauranteQueryService.consultarVendaPorRestaurante(filtro, timeOffset);
    }

}
