package com.jpa_exemplo.jpa_exemplo.controller;

import com.jpa_exemplo.jpa_exemplo.domain.exception.EntidadeNaoEncontradaException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.EstadoNaoEncontradoException;
import com.jpa_exemplo.jpa_exemplo.domain.model.Estado;
import com.jpa_exemplo.jpa_exemplo.domain.repository.CozinhaRepository;
import com.jpa_exemplo.jpa_exemplo.domain.repository.EstadoRepository;
import com.jpa_exemplo.jpa_exemplo.domain.service.CadastroEstadoService;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cozinha.Request.UpdateCozinhaRequestDto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.estado.Request.CriarEstadoRequestDto;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.estado.Request.UpdateEstadoRequest;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.estado.Response.CriarEstadoResponse;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.estado.Response.UpdateEstadoResponse;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.estado.CriarEstadoMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.estado.UpdateEstadoMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private CadastroEstadoService estadoService;

    @Autowired
    private EstadoRepository estadoRepository;


    @Autowired
    private CriarEstadoMapper criarEstadoMapper;

    @Autowired
    private UpdateEstadoMapper updateEstadoMapper;

    @GetMapping
    ResponseEntity<List<Estado>> listar(){
        List<Estado> estados = estadoRepository.findAll();
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/{estadoId}")
    ResponseEntity<Estado> estadoPorId(@PathVariable Long estadoId){
        Estado estado = estadoRepository.findById(estadoId)
                .orElseThrow(()-> new EstadoNaoEncontradoException("Estado nao encontrado"));
        return ResponseEntity.ok(estado);
    }

    @PostMapping
    public ResponseEntity<CriarEstadoResponse> inserirEstado(@Valid @RequestBody CriarEstadoRequestDto dto){
        Estado estado = criarEstadoMapper.toEntity(dto);
        estado = estadoService.inserirEstado(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                criarEstadoMapper.toResponse(estado)
        );
    }

    @PutMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UpdateEstadoResponse> atualizarEstado(
            @PathVariable Long estadoId,
            @RequestBody UpdateEstadoRequest dto
            ){
        try{
            Estado estado = estadoService.buscarOuFalhar(estadoId);

            updateEstadoMapper.updateEntity(dto, estado);

             estado = estadoService.atualizarEstado(estadoId, estado);

            return ResponseEntity.ok(updateEstadoMapper.toResponse(estado));
        } catch (EstadoNaoEncontradoException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<Void> deletarEstado(@PathVariable Long estadoId){
        try{
            estadoService.deletarEstado(estadoId);
            return ResponseEntity.noContent().build();
        }catch (EstadoNaoEncontradoException e){
            return ResponseEntity.notFound().build();
        }
    }
}
