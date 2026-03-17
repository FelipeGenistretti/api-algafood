package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.pedido;

import com.jpa_exemplo.jpa_exemplo.domain.model.Cidade;
import com.jpa_exemplo.jpa_exemplo.domain.model.Endereco;
import com.jpa_exemplo.jpa_exemplo.domain.model.FormaDePagamento;
import com.jpa_exemplo.jpa_exemplo.domain.model.ItemPedido;
import com.jpa_exemplo.jpa_exemplo.domain.model.Pedido;
import com.jpa_exemplo.jpa_exemplo.domain.model.Produto;
import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import com.jpa_exemplo.jpa_exemplo.domain.model.Usuario;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.CriarPedidoResquestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.ResumoCidadeDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.ResumoEnderecoEntrega;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.ResumoPedidoSimples;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-16T20:32:58-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class CriarPedidoMapperImpl implements CriarPedidoMapper {

    @Override
    public Pedido toModel(CriarPedidoResquestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Pedido pedido = new Pedido();

        pedido.setCliente( criarPedidoResquestDTOToUsuario( dto ) );
        pedido.setRestaurante( criarPedidoResquestDTOToRestaurante( dto ) );
        pedido.setFormaPagamento( criarPedidoResquestDTOToFormaDePagamento( dto ) );
        pedido.setEnderecoEntrega( resumoEnderecoEntregaToEndereco( dto.enderecoEntrega() ) );
        pedido.setItensPedido( resumoPedidoSimplesListToItemPedidoList( dto.itens() ) );

        return pedido;
    }

    @Override
    public ItemPedido toItemModel(ResumoPedidoSimples dto) {
        if ( dto == null ) {
            return null;
        }

        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setProduto( resumoPedidoSimplesToProduto( dto ) );
        itemPedido.setQuantidade( dto.quantidade() );
        itemPedido.setObservacao( dto.observacao() );

        return itemPedido;
    }

    protected Usuario criarPedidoResquestDTOToUsuario(CriarPedidoResquestDTO criarPedidoResquestDTO) {
        if ( criarPedidoResquestDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( criarPedidoResquestDTO.clienteId() );

        return usuario;
    }

    protected Restaurante criarPedidoResquestDTOToRestaurante(CriarPedidoResquestDTO criarPedidoResquestDTO) {
        if ( criarPedidoResquestDTO == null ) {
            return null;
        }

        Restaurante restaurante = new Restaurante();

        restaurante.setId( criarPedidoResquestDTO.restauranteId() );

        return restaurante;
    }

    protected FormaDePagamento criarPedidoResquestDTOToFormaDePagamento(CriarPedidoResquestDTO criarPedidoResquestDTO) {
        if ( criarPedidoResquestDTO == null ) {
            return null;
        }

        FormaDePagamento formaDePagamento = new FormaDePagamento();

        formaDePagamento.setId( criarPedidoResquestDTO.formaPagamentoId() );

        return formaDePagamento;
    }

    protected Cidade resumoCidadeDTOToCidade(ResumoCidadeDTO resumoCidadeDTO) {
        if ( resumoCidadeDTO == null ) {
            return null;
        }

        Cidade cidade = new Cidade();

        cidade.setId( resumoCidadeDTO.id() );
        cidade.setNome( resumoCidadeDTO.nome() );

        return cidade;
    }

    protected Endereco resumoEnderecoEntregaToEndereco(ResumoEnderecoEntrega resumoEnderecoEntrega) {
        if ( resumoEnderecoEntrega == null ) {
            return null;
        }

        Endereco endereco = new Endereco();

        endereco.setCidade( resumoCidadeDTOToCidade( resumoEnderecoEntrega.cidade() ) );
        endereco.setCep( resumoEnderecoEntrega.cep() );
        endereco.setLogradouro( resumoEnderecoEntrega.logradouro() );
        endereco.setNumero( resumoEnderecoEntrega.numero() );
        endereco.setComplemento( resumoEnderecoEntrega.complemento() );
        endereco.setBairro( resumoEnderecoEntrega.bairro() );

        return endereco;
    }

    protected List<ItemPedido> resumoPedidoSimplesListToItemPedidoList(List<ResumoPedidoSimples> list) {
        if ( list == null ) {
            return null;
        }

        List<ItemPedido> list1 = new ArrayList<ItemPedido>( list.size() );
        for ( ResumoPedidoSimples resumoPedidoSimples : list ) {
            list1.add( toItemModel( resumoPedidoSimples ) );
        }

        return list1;
    }

    protected Produto resumoPedidoSimplesToProduto(ResumoPedidoSimples resumoPedidoSimples) {
        if ( resumoPedidoSimples == null ) {
            return null;
        }

        Produto produto = new Produto();

        produto.setId( resumoPedidoSimples.produtoId() );

        return produto;
    }
}
