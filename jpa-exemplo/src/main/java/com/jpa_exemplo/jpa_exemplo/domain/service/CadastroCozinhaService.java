package com.jpa_exemplo.jpa_exemplo.domain.service;

import com.jpa_exemplo.jpa_exemplo.domain.exception.*;
import com.jpa_exemplo.jpa_exemplo.domain.model.Cozinha;
import com.jpa_exemplo.jpa_exemplo.domain.repository.CozinhaRepository;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cozinha.Request.UpdateCozinhaRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {

        if (cozinhaRepository.existsByNome(cozinha.getNome())) {
            throw new NegocioException("Já existe uma cozinha com este nome");
        }

        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public Cozinha atualizar(Cozinha cozinha) {
        if (cozinhaRepository.existsByNomeAndIdNot(cozinha.getNome(), cozinha.getId())) {
            throw new NegocioException("Já existe uma cozinha com este nome");
        }
        return cozinhaRepository.save(cozinha);
    }



    @Transactional
    public void remover(Long cozinhaId) {

        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() ->
                        new CozinhaNaoEncontradaException(cozinhaId)
                );

        try {
            cozinhaRepository.delete(cozinha);
            cozinhaRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException("Cozinha em uso");
        }
    }

    public Cozinha buscarOuFalhar(Long cozinhaId)
    {
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(()-> new CozinhaNaoEncontradaException("cozinha não encontrada"));
        return cozinha;
    }

}
