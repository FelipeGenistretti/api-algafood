package com.jpa_exemplo.jpa_exemplo.domain.service;

import com.jpa_exemplo.jpa_exemplo.domain.exception.FormaDePagamentoException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.NegocioException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.RestauranteNaoEncontradoException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.UsuarioNaoEncontradoException;
import com.jpa_exemplo.jpa_exemplo.domain.model.*;
import com.jpa_exemplo.jpa_exemplo.domain.repository.*;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.CriarPedidoResquestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.pedido.CriarPedidoMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CadastroPedidoService {

    @Autowired
    private CriarPedidoMapper criarPedidoMapper;

    @Autowired
    private UsuarioRepositoryInterface usuarioRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ProdutoRepositoryInterface produtoRepository;

    @Autowired
    private FormaDePagamentoInterface formaPagamentoRepository;

    @Autowired
    private PedidoRepositoryInterface pedidoRepository;


    @Transactional
    public Pedido criarPedido(CriarPedidoResquestDTO dto) {
        Pedido pedido = criarPedidoMapper.toModel(dto);

        Usuario cliente = usuarioRepository.findById(pedido.getCliente().getId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        Restaurante restaurante = restauranteRepository.findById(pedido.getRestaurante().getId())
                .orElseThrow(() -> new RestauranteNaoEncontradoException("Restaurante não encontrado"));

        FormaDePagamento formaPagamento = formaPagamentoRepository.findById(pedido.getFormaPagamento().getId())
                .orElseThrow(() -> new FormaDePagamentoException("Forma de pagamento não encontrada"));

        if (pedido.getItensPedido() == null || pedido.getItensPedido().isEmpty()) {
            throw new NegocioException("A lista de itens deste pedido está vazia");
        }

        if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
            throw new NegocioException("O restaurante não aceita a forma de pagamento informado");
        }

        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);

        for (ItemPedido item : pedido.getItensPedido()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new NegocioException("Produto não encontrado"));

            if (!produto.getRestaurante().getId().equals(restaurante.getId())) {
                throw new NegocioException("Um dos produtos não pertence ao restaurante informado");
            }

            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
            item.setPrecoTotal(produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())));
        }

        pedido.atribuirPedidoAosItens();
        pedido.definirFrete();
        pedido.calcularTotal();

        return pedidoRepository.save(pedido);
    }

    public Pedido buscarOuFalhar(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new NegocioException("Pedido não encontrado"));
    }

}
