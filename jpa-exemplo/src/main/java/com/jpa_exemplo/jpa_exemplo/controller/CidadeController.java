package com.jpa_exemplo.jpa_exemplo.controller;

import com.jpa_exemplo.jpa_exemplo.ExceptionHandler.Problema;
import com.jpa_exemplo.jpa_exemplo.domain.exception.CidadeNaoEncontradaException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.EntidadeNaoEncontradaException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.NegocioException;
import com.jpa_exemplo.jpa_exemplo.domain.model.Cidade;
import com.jpa_exemplo.jpa_exemplo.domain.repository.CidadeRepositoryInterface;
import com.jpa_exemplo.jpa_exemplo.domain.service.CadastroCidadeService;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cidade.Request.CreateCidadeRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cidade.Request.UpdateCidadeRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cidade.Response.CreateCidadeResponse;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cidade.Response.UpdateCidadeResponse;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.cidade.CreateCidadeMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.cidade.UpdateCidadeMapper;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CadastroCidadeService cidadeService;

    @Autowired
    private CidadeRepositoryInterface cidadeRepository;

    @Autowired
    private CreateCidadeMapper createCidadeMapper;

    @Autowired
    private UpdateCidadeMapper updateCidadeMapper;


    @GetMapping
    public ResponseEntity<List<Cidade>> todas(){
        List<Cidade> cidades = cidadeRepository.findAll();
        return ResponseEntity.ok(cidades);
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> cidadePorId(@PathVariable Long cidadeId){
        Cidade cidade = cidadeRepository.findById(cidadeId)
                .orElseThrow(()->new CidadeNaoEncontradaException("cidade nao encontrada"));
        return ResponseEntity.ok(cidade);
    }

    @PostMapping
    public ResponseEntity<CreateCidadeResponse> inserirCidade(@Valid @RequestBody CreateCidadeRequestDTO dto){
        try{
            Cidade cidade = createCidadeMapper.toEntity(dto);
            cidadeService.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(createCidadeMapper.toResponse(cidade));
        } catch (NegocioException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<UpdateCidadeResponse> atualizarCidade(
            @PathVariable Long cidadeId,
            @Valid @RequestBody UpdateCidadeRequestDTO dto) {

        Cidade cidade = cidadeService.atualizarCidade(cidadeId, dto);

        return ResponseEntity.ok(
                updateCidadeMapper.toResponse(cidade)
        );
    }


    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Void> deletarCidade(@PathVariable Long cidadeId){
            cidadeService.deletarCidade(cidadeId);
            return ResponseEntity.noContent().build();

    }




}
