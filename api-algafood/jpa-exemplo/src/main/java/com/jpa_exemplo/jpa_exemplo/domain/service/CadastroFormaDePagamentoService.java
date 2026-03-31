package com.jpa_exemplo.jpa_exemplo.domain.service;


import com.jpa_exemplo.jpa_exemplo.domain.exception.EntidadeEmUsoException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.EntidadeNaoEncontradaException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.FormaDePagamentoException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.NegocioException;
import com.jpa_exemplo.jpa_exemplo.domain.model.FormaDePagamento;
import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import com.jpa_exemplo.jpa_exemplo.domain.repository.FormaDePagamentoInterface;
import com.jpa_exemplo.jpa_exemplo.domain.repository.RestauranteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CadastroFormaDePagamentoService {

    @Autowired
    private FormaDePagamentoInterface formaPagamentoRepository;

    @Transactional
    public FormaDePagamento inserir(FormaDePagamento formaPagamento) {

        if (formaPagamentoRepository.existsByDescricao(formaPagamento.getDescricao())) {
            throw new NegocioException("Já existe uma forma de pagamento com esta descrição");
        }

        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public FormaDePagamento atualizar(Long id, FormaDePagamento formaDePagamento) {

        return formaPagamentoRepository.save(formaDePagamento);
    }

    @Transactional
    public void deletar(Long id) {

        FormaDePagamento formaPagamento = buscarOuFalhar(id);

        try {
            formaPagamentoRepository.delete(formaPagamento);
            formaPagamentoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException("Forma de pagamento em uso.");
        }
    }

    public FormaDePagamento buscarOuFalhar(Long id) {
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new FormaDePagamentoException("Forma de pagamento não encontrada"));
    }
}
