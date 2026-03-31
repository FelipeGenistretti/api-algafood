package com.jpa_exemplo.jpa_exemplo.domain.service;

import com.jpa_exemplo.jpa_exemplo.domain.exception.*;
import com.jpa_exemplo.jpa_exemplo.domain.model.Cidade;
import com.jpa_exemplo.jpa_exemplo.domain.model.Estado;
import com.jpa_exemplo.jpa_exemplo.domain.repository.CidadeRepositoryInterface;
import com.jpa_exemplo.jpa_exemplo.domain.repository.EstadoRepository;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Cidade.Request.UpdateCidadeRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.repository.Spec.CidadeSpecs;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroCidadeService {

    @Autowired
    private CidadeRepositoryInterface cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService estadosService;

    @Transactional
    public Cidade salvar(Cidade cidade) {

        if (cidade.getEstado() == null || cidade.getEstado().getId() == null) {
            throw new NegocioException("Estado é obrigatório");
        }

        Specification<Cidade> spec =
                CidadeSpecs.nomeIgualIgnoreCase(cidade.getNome());

        if (cidade.getId() != null) {
            spec = spec.and(CidadeSpecs.idDiferente(cidade.getId()));
        }

        if (cidadeRepository.count(spec) > 0) {
            throw new NegocioException("Já existe uma cidade com este nome");
        }

        Estado estado = estadoRepository.findById(cidade.getEstado().getId())
                .orElseThrow(() -> new EstadoNaoEncontradoException("Estado não encontrado"));

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    @Transactional
    public Cidade atualizarCidade(Long id, UpdateCidadeRequestDTO dto) {

        Cidade cidade = buscarOuFalhar(id);

        Estado estado = estadosService.buscarOuFalhar(dto.estadoId());

        cidade.setNome(dto.nome());
        cidade.setEstado(estado);

        return cidade;
    }


    @Transactional
    public void deletarCidade(Long id) {
        Cidade cidade = cidadeRepository.findById(id)
                .orElseThrow(() -> new CidadeNaoEncontradaException("Cidade não encontrada"));

        try {
            cidadeRepository.delete(cidade);
            cidadeRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException("Cidade já está em uso e não pode ser removida");
        }
    }

    public Cidade buscarOuFalhar(Long id){
         return cidadeRepository.findById(id).orElseThrow(()->new CidadeNaoEncontradaException("Cidade não encontrada"));

    }
}

