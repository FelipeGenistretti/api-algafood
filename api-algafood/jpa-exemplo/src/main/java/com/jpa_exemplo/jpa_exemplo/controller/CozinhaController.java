package com.jpa_exemplo.jpa_exemplo.controller;

import com.jpa_exemplo.jpa_exemplo.domain.exception.CozinhaNaoEncontradaException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.EntidadeEmUsoException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.EntidadeNaoEncontradaException;
import com.jpa_exemplo.jpa_exemplo.domain.model.Cozinha;
import com.jpa_exemplo.jpa_exemplo.domain.repository.CozinhaRepository;
import com.jpa_exemplo.jpa_exemplo.domain.service.CadastroCozinhaService;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cozinha.Request.CriarCozinhaRequestDto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cozinha.Request.UpdateCozinhaRequestDto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cozinha.Response.CriarCozinhaResponse;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cozinha.Response.UpdateCozinhaResponse;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.cozinha.CriarCozinhaMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.cozinha.UpdateCozinhaMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.repository.Spec.CozinhaSpecs;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CriarCozinhaMapper criarCozinhaMapper;

    @Autowired
    private UpdateCozinhaMapper updateCozinhaMapper;

    @GetMapping
    public ResponseEntity<Page<CriarCozinhaResponse>> listar(Pageable pageable) {

        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

        List<CriarCozinhaResponse> cozinhasResponse =
                criarCozinhaMapper.toCollectionResponse(cozinhasPage.getContent());

        Page<CriarCozinhaResponse> cozinhasResponsePage =
                new PageImpl<>(cozinhasResponse, pageable, cozinhasPage.getTotalElements());

        return ResponseEntity.ok(cozinhasResponsePage);
    }

    @GetMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.OK)
    public Cozinha buscar(@PathVariable Long cozinhaId) {

        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

        return cozinha;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CriarCozinhaResponse> salvar(@RequestBody @Valid CriarCozinhaRequestDto dto) {

        Cozinha cozinha = criarCozinhaMapper.toEntity(dto);

        cadastroCozinhaService.salvar(cozinha);

        return ResponseEntity.status(HttpStatus.CREATED).body(criarCozinhaMapper.toModel(cozinha));

    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<UpdateCozinhaResponse> atualizar(
            @PathVariable Long cozinhaId,
            @RequestBody @Valid UpdateCozinhaRequestDto dto) {

        try {
            Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

            updateCozinhaMapper.updateEntity(dto, cozinha);

            cozinha = cadastroCozinhaService.atualizar(cozinha);

            return ResponseEntity.ok(
                    updateCozinhaMapper.toModel(cozinha)
            );

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/{cozinhaId}")
    public void remover(@PathVariable Long cozinhaId) {
            cadastroCozinhaService.remover(cozinhaId);
    }

    @GetMapping("/por-nome")
    public ResponseEntity<Cozinha> listarPorNome(@RequestParam("nome") String nome){
        Cozinha cozinha = cozinhaRepository.findByNome(nome)
                .orElseThrow(() -> new CozinhaNaoEncontradaException("Cozinha não encontrada"));

        return ResponseEntity.ok(cozinha);
    }

    @GetMapping("/por-nome-containing")
    public ResponseEntity<List<Cozinha>> listarPorNomeContaining(@RequestParam("nome") String nome){
        List<Cozinha> cozinha = cozinhaRepository.findByNomeContaining(nome);

        if(cozinha.isEmpty()){
            throw new CozinhaNaoEncontradaException("Nenhuma cozinha foi encontrada");
        }

        return ResponseEntity.ok(cozinha);
    }

    @GetMapping("/por-nome-like")
    public ResponseEntity<List<Cozinha>> porNomeLike(String nome){
        List<Cozinha> cozinhas = cozinhaRepository.findAll(CozinhaSpecs.cozinhaPorNomeLike(nome));

        if(cozinhas.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cozinhas);
    }


}


