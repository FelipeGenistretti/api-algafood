package com.jpa_exemplo.jpa_exemplo.domain.service;

import com.jpa_exemplo.jpa_exemplo.domain.exception.NegocioException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.ProdutoNaoEncontradoException;
import com.jpa_exemplo.jpa_exemplo.domain.model.Produto;
import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import com.jpa_exemplo.jpa_exemplo.domain.repository.ProdutoRepository;
import com.jpa_exemplo.jpa_exemplo.domain.repository.RestauranteRepository;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Produto.UpdateProdutoRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.produto.UpdateProdutoMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroRestauranteService restauranteService;

    @Autowired
    private RestauranteRepository restauranteRepository;


    @Autowired
    private UpdateProdutoMapper updateProdutoMapper;

    @Transactional
    public Produto criarProduto(Long restauranteId, Produto produto)
    {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        boolean produtoExists = produtoRepository.existsByNome(produto.getNome());
        if(produtoExists){
            throw new NegocioException("Já existe um produto com este nome");
        }

        restaurante.adicionarProduto(produto);

        restaurante.ativarProduto(produto);

        restauranteRepository.save(restaurante);
        restauranteRepository.flush();

        return produto;
    }


    @Transactional
    public Produto updateProduto(UpdateProdutoRequestDTO dto, Long restauranteId, Long produtoId) {

        Produto produto = produtoRepository
                .findProdutoByIdAndRestauranteId(produtoId, restauranteId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));


        updateProdutoMapper.updateEntity(dto, produto);

        if (dto.ativo() != null) {
            if (dto.ativo() && !produto.isAtivo()) {
                produto.ativar();
            } else if (!dto.ativo() && produto.isAtivo()) {
                produto.desativar();
            }
        }

        return produto;
    }

    public Produto buscarOuFalhar(Long id)
    {
        return produtoRepository.findById(id).orElseThrow(()-> new ProdutoNaoEncontradoException("Produto não encontrado"));
    }
}
