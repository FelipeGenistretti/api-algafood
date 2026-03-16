package com.jpa_exemplo.jpa_exemplo.controller;

import com.jpa_exemplo.jpa_exemplo.domain.exception.ProdutoNaoEncontradoException;
import com.jpa_exemplo.jpa_exemplo.domain.model.Produto;
import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import com.jpa_exemplo.jpa_exemplo.domain.repository.ProdutoRepositoryInterface;
import com.jpa_exemplo.jpa_exemplo.domain.service.CadastroProdutoService;
import com.jpa_exemplo.jpa_exemplo.domain.service.CadastroRestauranteService;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Produto.*;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.produto.CreateProdutoMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.produto.ListProdutoMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.produto.UpdateProdutoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class ProdutoController {

    @Autowired
    private ProdutoRepositoryInterface produtoRepository;

    @Autowired
    private ListProdutoMapper listProdutoMapper;

    @Autowired
    private CadastroProdutoService produtoService;

    @Autowired
    private CadastroRestauranteService cadastroService;

    @Autowired
    private CreateProdutoMapper criarProdutoMapper;

    @Autowired
    private UpdateProdutoMapper updateProdutoMapper;

    @GetMapping("/{restauranteId}/produtos")
    public ResponseEntity<List<ListProdutoResponseDTO>> listarTodos(@PathVariable Long restauranteId)
    {
        Restaurante restaurante = cadastroService.buscarOuFalhar(restauranteId);
        List<Produto> produtos = restaurante.listarProdutosRestaurante();
        if(produtos.isEmpty()){
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(listProdutoMapper.toCollectionResponse(produtos));
    }

    @GetMapping("/{restauranteId}/produtos/{produtoId}")
    public ResponseEntity<ListProdutoResponseDTO> buscarPorId(@PathVariable Long restauranteId,@PathVariable Long produtoId)
    {
        Produto produto = produtoRepository.findProdutoByIdAndRestauranteId(produtoId, restauranteId).orElseThrow(
                ()-> new ProdutoNaoEncontradoException("Produto não encontrado")
        );
        return ResponseEntity.status(200).body(listProdutoMapper.toResponse(produto));
    }

    @PostMapping("/{restauranteId}/produtos")
    public ResponseEntity<CreateProdutoResponseDTO> criarProduto(
            @PathVariable Long restauranteId,
            @RequestBody CreateProdutoRequestDTO dto)
    {
        Produto produto = criarProdutoMapper.toEntity(dto);

        Produto produtoSalvo = produtoService.criarProduto(restauranteId, produto);

        return ResponseEntity.status(201).body(criarProdutoMapper.toResponse(produtoSalvo));
    }

    @PutMapping("/{restauranteId}/produtos/{produtoId}")
    public ResponseEntity<UpdateProdutoResponseDTO> atualizar(
            @PathVariable Long restauranteId,
            @PathVariable Long produtoId,
            @RequestBody UpdateProdutoRequestDTO dto) {

        Produto produtoAtualizado = produtoService.updateProduto(dto, restauranteId, produtoId);

        UpdateProdutoResponseDTO response = updateProdutoMapper.toResponse(produtoAtualizado);

        return ResponseEntity.ok(response);
    }
}
