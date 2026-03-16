package com.jpa_exemplo.jpa_exemplo.controller;

import com.jpa_exemplo.jpa_exemplo.domain.model.ItemPedido;
import com.jpa_exemplo.jpa_exemplo.domain.model.Pedido;
import com.jpa_exemplo.jpa_exemplo.domain.repository.PedidoRepositoryInterface;
import com.jpa_exemplo.jpa_exemplo.domain.service.CadastroPedidoService;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.CriarPedidoResquestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Pedido.ListPedidosResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.pedido.CriarPedidoMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.pedido.ListPedidosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepositoryInterface pedidoRepository;

    @Autowired
    private CadastroPedidoService pedidoService;

    @Autowired
    private ListPedidosMapper listPedidosMapper;

    @Autowired
    private CriarPedidoMapper criarPedidoMapper;

    @GetMapping
    public ResponseEntity<List<ListPedidosResponseDTO>> listarTodos() {
        List<Pedido> pedidos = pedidoRepository.buscarTodosComItens();

        for (Pedido pedido : pedidos) {
            System.out.println("PEDIDO ID: " + pedido.getId());
            System.out.println("ITENS SIZE: " + pedido.getItensPedido().size());
        }

        return ResponseEntity.ok(listPedidosMapper.toCollectionResponse(pedidos));
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<ListPedidosResponseDTO> buscarPorId(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
        return ResponseEntity.ok(listPedidosMapper.toResponse(pedido));
    }

    @PostMapping
    public ResponseEntity<ListPedidosResponseDTO> criarPedido(@RequestBody CriarPedidoResquestDTO dto) {
        Pedido pedidoCriado = pedidoService.criarPedido(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(listPedidosMapper.toResponse(pedidoCriado));
    }
}