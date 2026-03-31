
package com.jpa_exemplo.jpa_exemplo.core.validation.enums;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {

    CRIADO("Criado"),
    CONFIRMADO("Confirmado", CRIADO),
    ENTREGUE("Entregue", CONFIRMADO),
    CANCELADO("Cancelado", CRIADO);

    private String descricao;
    private List<StatusPedido> statusAnteriores;

    StatusPedido(String descricao, StatusPedido... statusAnteriores) {
        this.descricao = descricao;
        this.statusAnteriores = Arrays.asList(statusAnteriores);
    }

    public String getDescricao() {
        return this.descricao;
    }

    public boolean podeSerConfirmado() {
        return this == CRIADO;
    }

    public boolean podeSerCancelado() {
        return this == CRIADO || this == CONFIRMADO;
    }

    public boolean naoPodeAlterarPara(StatusPedido novoStatus){
        return !novoStatus.statusAnteriores.contains(this);
    }

}