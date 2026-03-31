package com.jpa_exemplo.jpa_exemplo.controller;

import com.jpa_exemplo.jpa_exemplo.domain.exception.EntidadeNaoEncontradaException;
import com.jpa_exemplo.jpa_exemplo.domain.model.Endereco;
import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import com.jpa_exemplo.jpa_exemplo.domain.repository.RestauranteRepository;
import com.jpa_exemplo.jpa_exemplo.domain.service.CadastroRestauranteService;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Endereco.Request.EnderecoRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.FormaDePagamento.Response.FormaDePagamentoResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.AtualizarRestauranteRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.CriarRestauranteRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.CriarRestauranteResponse;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.ListRestaurantesResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.endereco.EnderecoMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.formaPagamento.FormaPagamentoMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.restaurante.CriarRestauranteMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.restaurante.ListRestauranteMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.restaurante.UpdateRestauranteMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.repository.Spec.RestauranteSpecs;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value="/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaDePagamentoController {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CriarRestauranteMapper restauranteMapper;

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Autowired
    private ListRestauranteMapper listRestauranteMapper;

    @Autowired
    private UpdateRestauranteMapper updateRestauranteMapper;

    @Autowired
    private FormaPagamentoMapper formaPagamentoMapper;


    @GetMapping
    public ResponseEntity<Set<FormaDePagamentoResponseDTO>> todos(@PathVariable Long restauranteId) {
        Restaurante restaurante  = cadastroRestauranteService.buscarOuFalhar(restauranteId);
        return ResponseEntity.ok(formaPagamentoMapper.toColletionResponse(restaurante.getFormasPagamento()));
    }

    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId)
    {
        cadastroRestauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId)
    {
        cadastroRestauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }




}
