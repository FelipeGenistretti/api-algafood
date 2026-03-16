package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.pedido;

import com.jpa_exemplo.jpa_exemplo.domain.model.ItemPedido;
import com.jpa_exemplo.jpa_exemplo.domain.model.Pedido;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.ListPedidosResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.ResumoItensPedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ListPedidosMapper {

    @Mapping(source = "itensPedido", target = "itens")
    ListPedidosResponseDTO toResponse(Pedido pedido);

    List<ListPedidosResponseDTO> toCollectionResponse(List<Pedido> pedidos);

    @Mapping(target = "produtoId", source = "produto.id")
    @Mapping(target = "produtoNome", source = "produto.nome")
    ResumoItensPedido toItemDTO(ItemPedido item);

    List<ResumoItensPedido> toItemDTOList(List<ItemPedido> itensPedido);
}