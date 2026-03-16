package com.jpa_exemplo.jpa_exemplo.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa_exemplo.jpa_exemplo.domain.exception.*;
import com.jpa_exemplo.jpa_exemplo.domain.model.*;
import com.jpa_exemplo.jpa_exemplo.domain.repository.CidadeRepositoryInterface;
import com.jpa_exemplo.jpa_exemplo.domain.repository.CozinhaRepository;
import com.jpa_exemplo.jpa_exemplo.domain.repository.FormaDePagamentoInterface;
import com.jpa_exemplo.jpa_exemplo.domain.repository.RestauranteRepository;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.AtualizarRestauranteRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.restaurante.UpdateRestauranteMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.repository.Spec.RestauranteSpecs;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CidadeRepositoryInterface cidadeRepository;

    @Autowired
    private FormaDePagamentoInterface formaPagamentoRepository;

    @Autowired
    private UpdateRestauranteMapper updateRestauranteMapper;

    @Autowired
    private CadastroFormaDePagamentoService formaPagamentoService;

    @Autowired
    private CadastroUsuarioService usuarioService;

    @Transactional
    public Restaurante inserirRestaurante(Restaurante restaurante, Long cozinhaId) {

        if (cozinhaId == null) {
            throw new NegocioException("Cozinha é obrigatória");
        }

        Specification<Restaurante> spec =
                RestauranteSpecs.nomeIgualIgnoreCase(restaurante.getNome());

        if (restauranteRepository.count(spec) > 0) {
            throw new NegocioException("Já existe um restaurante com este nome");
        }

        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new CozinhaNaoEncontradaException("Cozinha não encontrada"));

        if (restaurante.getFormasPagamento().isEmpty()) {
            restaurante.desativar();
        }

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.ativar();
    }

    @Transactional
    public void inativar(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.desativar();
    }

    @Transactional
    public Restaurante atualizarRestaurante(Long restauranteId, AtualizarRestauranteRequestDTO dto) {

        Restaurante restaurante = buscarOuFalhar(restauranteId);

        updateRestauranteMapper.updateEntity(dto, restaurante);

        Long cozinhaId = (dto.cozinha() != null) ? dto.cozinha().id() : null;
        if (cozinhaId == null) {
            throw new NegocioException("Cozinha é obrigatória");
        }

        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new CozinhaNaoEncontradaException("Cozinha não encontrada"));

        restaurante.setCozinha(cozinha);

        Long cidadeId = (dto.endereco() != null && dto.endereco().cidade() != null)
                ? dto.endereco().cidade().id()
                : null;

        if (cidadeId != null) {
            Cidade cidade = cidadeRepository.findById(cidadeId)
                    .orElseThrow(() -> new CidadeNaoEncontradaException("Cidade não encontrada"));

            if (restaurante.getEndereco() == null) {
                restaurante.setEndereco(new Endereco());
            }
            restaurante.getEndereco().setCidade(cidade);
        }

        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public Restaurante atualizarParcial(Long id, Map<String, Object> campos) {

        Restaurante restauranteAtual = buscarOuFalhar(id);
        merge(campos, restauranteAtual);

        return restauranteRepository.save(restauranteAtual);
    }

    private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);

        camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            if (field == null) return;

            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

    @Transactional
    public void deletarRestaurante(Long restauranteId) {
        try {
            restauranteRepository.deleteById(restauranteId);
        } catch (EmptyResultDataAccessException e) {
            throw new RestauranteNaoEncontradoException("Restaurante não encontrado");
        }
    }

    public Restaurante buscarOuFalhar(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new RestauranteNaoEncontradoException("Restaurante não encontrado"));
    }

    @Transactional
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {

        Restaurante restaurante = buscarOuFalhar(restauranteId);

        FormaDePagamento formaPagamento = formaPagamentoRepository.findById(formaPagamentoId)
                .orElseThrow(() -> new FormaDePagamentoException("Forma de pagamento não encontrada"));

        restaurante.ativar();

        restaurante.adicionarFormaPagamento(formaPagamento);
    }

    @Transactional
    public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId)
    {
        Restaurante restaurante = this.buscarOuFalhar(restauranteId);
        FormaDePagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        restaurante.removerFormaPagamento(formaPagamento);

    }

    @Transactional
    public void adicionarEndereco(Long restauranteId, Endereco endereco) {

        Restaurante restaurante = this.buscarOuFalhar(restauranteId);

        if (endereco.getCidade() != null && endereco.getCidade().getId() != null) {

            Long cidadeId = endereco.getCidade().getId();

            Cidade cidade = cidadeRepository.findById(cidadeId)
                    .orElseThrow(() ->
                            new CidadeNaoEncontradaException("Não existe cidade com esse id")
                    );

            endereco.setCidade(cidade);
        }

        restaurante.setEndereco(endereco);
    }

    @Transactional
    public void fecharRestaurante(Long restauranteId)
    {
        Restaurante restaurante = this.buscarOuFalhar(restauranteId);
        restaurante.fecharRestaurante();

    }

    @Transactional
    public void abrirRestaurante(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.abrirRestaurante();
    }

    @Transactional
    public void associarResponsavel(Long restauranteId,Long usuarioId){
        Restaurante restaurante = this.buscarOuFalhar(restauranteId);
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);

        restaurante.adicionarResponsavel(usuario);

    }

    @Transactional
    public void desassociarResponsavel(Long restauranteId, Long usuarioId){
        Restaurante restaurante = this.buscarOuFalhar(restauranteId);
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);

        restaurante.removerResponsavel(usuario);
    }

    @Transactional
    public void ativar(List<Long> restauranteIds) {
        restauranteIds.forEach(this::ativar);
    }

    @Transactional
    public void desativar(List<Long> restauranteIds) {
        restauranteIds.forEach(this::inativar);
    }


}


