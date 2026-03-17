package com.jpa_exemplo.jpa_exemplo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jpa_exemplo.jpa_exemplo.domain.model.dto.VendaDiaria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path="/estatisticas")
public class EstatisticasController {

    @Autowired
    private Venda

    public List<VendaDiaria> consultarVendasDIarias() {
        
    }

}
