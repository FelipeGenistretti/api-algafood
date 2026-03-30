package com.jpa_exemplo.jpa_exemplo.controller;

import com.jpa_exemplo.jpa_exemplo.domain.model.FormaDePagamento;
import com.jpa_exemplo.jpa_exemplo.domain.repository.FormaDePagamentoInterface;
import com.jpa_exemplo.jpa_exemplo.domain.service.CadastroFormaDePagamentoService;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.FormaDePagamento.Request.FormaDePagamentoRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.FormaDePagamento.Response.FormaDePagamentoResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.formaPagamento.FormaPagamentoMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/forma-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaDePagamentoInterface formaPagamentoRepository;

    @Autowired
    private CadastroFormaDePagamentoService formaPagamentoService;

    @Autowired
    private FormaPagamentoMapper formaPagamentoMapper;

    @GetMapping
    public ResponseEntity<Set<FormaDePagamentoResponseDTO>> todasFormasPagamento() {

        List<FormaDePagamento> formasPagamentos = formaPagamentoRepository.findAll();

        Set<FormaDePagamentoResponseDTO> response =
                formaPagamentoMapper.toColletionResponse(formasPagamentos);

        return ResponseEntity.ok()
                            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                            .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaDePagamentoResponseDTO> formaPagamentoPorId(@PathVariable Long id) {
        FormaDePagamento formaDePagamento = formaPagamentoService.buscarOuFalhar(id);
        FormaDePagamentoResponseDTO response = formaPagamentoMapper.toResponse(formaDePagamento);
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic()).body(response);
    }

    @PostMapping
    public ResponseEntity<FormaDePagamentoResponseDTO> criarFormaPagamento(
            @RequestBody @Valid FormaDePagamentoRequestDTO dto) {

        FormaDePagamento formaPagamento = formaPagamentoMapper.toEntity(dto);
        formaPagamento = formaPagamentoService.inserir(formaPagamento);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(formaPagamentoMapper.toResponse(formaPagamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFormaPagamento(@PathVariable Long id) {
        formaPagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaDePagamentoResponseDTO> atualizarFormaPagamento(
            @PathVariable Long id,
            @RequestBody @Valid FormaDePagamentoRequestDTO dto) {

        FormaDePagamento formaDePagamento = formaPagamentoService.buscarOuFalhar(id);
        formaPagamentoMapper.updateEntity(dto, formaDePagamento);
        formaDePagamento = formaPagamentoService.atualizar(id, formaDePagamento);

        return ResponseEntity.ok(formaPagamentoMapper.toResponse(formaDePagamento));
    }
}