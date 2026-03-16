package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.pedido;

import com.jpa_exemplo.jpa_exemplo.core.validation.enums.StatusPedido;
import com.jpa_exemplo.jpa_exemplo.domain.model.Cidade;
import com.jpa_exemplo.jpa_exemplo.domain.model.Endereco;
import com.jpa_exemplo.jpa_exemplo.domain.model.FormaDePagamento;
import com.jpa_exemplo.jpa_exemplo.domain.model.ItemPedido;
import com.jpa_exemplo.jpa_exemplo.domain.model.Pedido;
import com.jpa_exemplo.jpa_exemplo.domain.model.Produto;
import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import com.jpa_exemplo.jpa_exemplo.domain.model.Usuario;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.ListPedidosResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.ResumoCidadeId;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.ResumoClienteDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.ResumoEnderecoEntregaCriarPedido;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.ResumoFormaPagamento;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.ResumoItensPedido;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.ResumoRestauranteDTO;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-15T15:44:00-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class ListPedidosMapperImpl implements ListPedidosMapper {

    @Override
    public ListPedidosResponseDTO toResponse(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }

        List<ResumoItensPedido> itens = null;
        Long id = null;
        BigDecimal subtotal = null;
        BigDecimal taxaFrete = null;
        BigDecimal valorTotal = null;
        StatusPedido status = null;
        OffsetDateTime dataCriacao = null;
        OffsetDateTime dataConfirmacao = null;
        OffsetDateTime dataEntrega = null;
        OffsetDateTime dataCancelamento = null;
        ResumoRestauranteDTO restaurante = null;
        ResumoClienteDTO cliente = null;
        ResumoFormaPagamento formaPagamento = null;
        ResumoEnderecoEntregaCriarPedido enderecoEntrega = null;

        itens = toItemDTOList( pedido.getItensPedido() );
        id = pedido.getId();
        subtotal = pedido.getSubtotal();
        taxaFrete = pedido.getTaxaFrete();
        valorTotal = pedido.getValorTotal();
        status = pedido.getStatus();
        dataCriacao = pedido.getDataCriacao();
        dataConfirmacao = pedido.getDataConfirmacao();
        dataEntrega = pedido.getDataEntrega();
        dataCancelamento = pedido.getDataCancelamento();
        restaurante = restauranteToResumoRestauranteDTO( pedido.getRestaurante() );
        cliente = usuarioToResumoClienteDTO( pedido.getCliente() );
        formaPagamento = formaDePagamentoToResumoFormaPagamento( pedido.getFormaPagamento() );
        enderecoEntrega = enderecoToResumoEnderecoEntregaCriarPedido( pedido.getEnderecoEntrega() );

        ListPedidosResponseDTO listPedidosResponseDTO = new ListPedidosResponseDTO( id, subtotal, taxaFrete, valorTotal, status, dataCriacao, dataConfirmacao, dataEntrega, dataCancelamento, restaurante, cliente, formaPagamento, enderecoEntrega, itens );

        return listPedidosResponseDTO;
    }

    @Override
    public List<ListPedidosResponseDTO> toCollectionResponse(List<Pedido> pedidos) {
        if ( pedidos == null ) {
            return null;
        }

        List<ListPedidosResponseDTO> list = new ArrayList<ListPedidosResponseDTO>( pedidos.size() );
        for ( Pedido pedido : pedidos ) {
            list.add( toResponse( pedido ) );
        }

        return list;
    }

    @Override
    public ResumoItensPedido toItemDTO(ItemPedido item) {
        if ( item == null ) {
            return null;
        }

        Long produtoId = null;
        String produtoNome = null;
        Integer quantidade = null;
        BigDecimal precoUnitario = null;
        BigDecimal precoTotal = null;
        String observacao = null;

        produtoId = itemProdutoId( item );
        produtoNome = itemProdutoNome( item );
        quantidade = item.getQuantidade();
        precoUnitario = item.getPrecoUnitario();
        precoTotal = item.getPrecoTotal();
        observacao = item.getObservacao();

        ResumoItensPedido resumoItensPedido = new ResumoItensPedido( produtoId, produtoNome, quantidade, precoUnitario, precoTotal, observacao );

        return resumoItensPedido;
    }

    @Override
    public List<ResumoItensPedido> toItemDTOList(List<ItemPedido> itensPedido) {
        if ( itensPedido == null ) {
            return null;
        }

        List<ResumoItensPedido> list = new ArrayList<ResumoItensPedido>( itensPedido.size() );
        for ( ItemPedido itemPedido : itensPedido ) {
            list.add( toItemDTO( itemPedido ) );
        }

        return list;
    }

    protected ResumoRestauranteDTO restauranteToResumoRestauranteDTO(Restaurante restaurante) {
        if ( restaurante == null ) {
            return null;
        }

        Long id = null;
        String nome = null;

        id = restaurante.getId();
        nome = restaurante.getNome();

        ResumoRestauranteDTO resumoRestauranteDTO = new ResumoRestauranteDTO( id, nome );

        return resumoRestauranteDTO;
    }

    protected ResumoClienteDTO usuarioToResumoClienteDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        String email = null;

        id = usuario.getId();
        nome = usuario.getNome();
        email = usuario.getEmail();

        ResumoClienteDTO resumoClienteDTO = new ResumoClienteDTO( id, nome, email );

        return resumoClienteDTO;
    }

    protected ResumoFormaPagamento formaDePagamentoToResumoFormaPagamento(FormaDePagamento formaDePagamento) {
        if ( formaDePagamento == null ) {
            return null;
        }

        Long id = null;
        String descricao = null;

        id = formaDePagamento.getId();
        descricao = formaDePagamento.getDescricao();

        ResumoFormaPagamento resumoFormaPagamento = new ResumoFormaPagamento( id, descricao );

        return resumoFormaPagamento;
    }

    protected ResumoCidadeId cidadeToResumoCidadeId(Cidade cidade) {
        if ( cidade == null ) {
            return null;
        }

        Long id = null;

        id = cidade.getId();

        ResumoCidadeId resumoCidadeId = new ResumoCidadeId( id );

        return resumoCidadeId;
    }

    protected ResumoEnderecoEntregaCriarPedido enderecoToResumoEnderecoEntregaCriarPedido(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        String cep = null;
        String logradouro = null;
        String numero = null;
        String complemento = null;
        String bairro = null;
        ResumoCidadeId cidade = null;

        cep = endereco.getCep();
        logradouro = endereco.getLogradouro();
        numero = endereco.getNumero();
        complemento = endereco.getComplemento();
        bairro = endereco.getBairro();
        cidade = cidadeToResumoCidadeId( endereco.getCidade() );

        ResumoEnderecoEntregaCriarPedido resumoEnderecoEntregaCriarPedido = new ResumoEnderecoEntregaCriarPedido( cep, logradouro, numero, complemento, bairro, cidade );

        return resumoEnderecoEntregaCriarPedido;
    }

    private Long itemProdutoId(ItemPedido itemPedido) {
        if ( itemPedido == null ) {
            return null;
        }
        Produto produto = itemPedido.getProduto();
        if ( produto == null ) {
            return null;
        }
        Long id = produto.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String itemProdutoNome(ItemPedido itemPedido) {
        if ( itemPedido == null ) {
            return null;
        }
        Produto produto = itemPedido.getProduto();
        if ( produto == null ) {
            return null;
        }
        String nome = produto.getNome();
        if ( nome == null ) {
            return null;
        }
        return nome;
    }
}
