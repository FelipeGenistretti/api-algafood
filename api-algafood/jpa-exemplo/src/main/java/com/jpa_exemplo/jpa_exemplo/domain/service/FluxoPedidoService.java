package com.jpa_exemplo.jpa_exemplo.domain.service;

import com.jpa_exemplo.jpa_exemplo.core.validation.enums.StatusPedido;
import com.jpa_exemplo.jpa_exemplo.domain.exception.NegocioException;
import com.jpa_exemplo.jpa_exemplo.domain.model.Pedido;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {

    @Autowired
    private CadastroPedidoService pedidoService;

    @Transactional
    public void confirmar(Long id){
        Pedido pedido = pedidoService.buscarOuFalhar(id);
        pedido.confirmar();
    }

    @Transactional
    public void cancelar(Long id){
        Pedido pedido = pedidoService.buscarOuFalhar(id);
        pedido.cancelar();
    }

    @Transactional
    public void entregar(Long id){
        Pedido pedido = pedidoService.buscarOuFalhar(id);
        pedido.entregar();
    }
}
