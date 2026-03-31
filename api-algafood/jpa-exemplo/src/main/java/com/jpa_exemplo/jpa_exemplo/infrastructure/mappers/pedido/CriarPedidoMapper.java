package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.pedido;

import com.jpa_exemplo.jpa_exemplo.domain.model.ItemPedido;
import com.jpa_exemplo.jpa_exemplo.domain.model.Pedido;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.CriarPedidoResquestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.ResumoPedidoSimples;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CriarPedidoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente.id", source = "clienteId")
    @Mapping(target = "restaurante.id", source = "restauranteId")
    @Mapping(target = "formaPagamento.id", source = "formaPagamentoId")

    @Mapping(target = "enderecoEntrega.cidade.id", source = "enderecoEntrega.cidade.id")
    @Mapping(target = "enderecoEntrega.cidade.estado", ignore = true)

    @Mapping(target = "itensPedido", source = "itens")
    Pedido toModel(CriarPedidoResquestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pedido", ignore = true)
    @Mapping(target = "produto.id", source = "produtoId")

    @Mapping(target = "precoUnitario", ignore = true)
    @Mapping(target = "precoTotal", ignore = true)

    ItemPedido toItemModel(ResumoPedidoSimples dto);
}
