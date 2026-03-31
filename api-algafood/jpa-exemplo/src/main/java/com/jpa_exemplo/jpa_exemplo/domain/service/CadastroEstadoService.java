package com.jpa_exemplo.jpa_exemplo.domain.service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.jpa_exemplo.jpa_exemplo.domain.exception.EntidadeEmUsoException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.EntidadeNaoEncontradaException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.EstadoNaoEncontradoException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.NegocioException;
import com.jpa_exemplo.jpa_exemplo.domain.model.Estado;
import com.jpa_exemplo.jpa_exemplo.domain.repository.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Transactional
    public Estado inserirEstado(Estado estado){
        if(estadoRepository.existsByNome(estado.getNome())){
            throw new NegocioException("Já existe um estado com este nome");
        }

        return estadoRepository.save(estado);
    }

    @Transactional
    public Estado atualizarEstado(Long id, Estado novoEstado){
        if(estadoRepository.existsByNome(novoEstado.getNome())){
            throw new NegocioException("Já existe um estado com este nome");
        }

        return estadoRepository.save(novoEstado);
    }

    @Transactional
    public void deletarEstado(Long id) {
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new EstadoNaoEncontradoException("Estado não encontrado"));

        try {
            estadoRepository.delete(estado);
            estadoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EstadoNaoEncontradoException(id);
        }
    }


    public Estado buscarOuFalhar(Long id)
    {
        Estado estado = estadoRepository.findById(id).orElseThrow(()->new EstadoNaoEncontradoException("Estado não encontrado"));
        return estado;
    }


}
