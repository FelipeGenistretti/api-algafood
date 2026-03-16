package com.jpa_exemplo.jpa_exemplo.domain.model;

import com.jpa_exemplo.jpa_exemplo.core.validation.enums.StatusPedido;
import com.jpa_exemplo.jpa_exemplo.domain.exception.NegocioException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    public Long id;

    public BigDecimal subtotal;

    public BigDecimal taxaFrete;

    public BigDecimal valorTotal;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    public OffsetDateTime dataCriacao;

    @Column
    public OffsetDateTime dataConfirmacao;

    @Column
    public OffsetDateTime dataCancelamento;

    @Column
    public OffsetDateTime dataEntrega;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itensPedido = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private Usuario cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    private FormaDePagamento formaPagamento;

    @Embedded
    private Endereco enderecoEntrega;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CRIADO;

    public void calcularTotal() {
        this.subtotal = getItensPedido().stream()
                .map(item -> item.getPrecoTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = this.subtotal.add(this.taxaFrete);
    }

    public void definirFrete() {
        setTaxaFrete(getRestaurante().getTaxaFrete());
    }

    public void atribuirPedidoAosItens() {
        getItensPedido().forEach(item -> item.setPedido(this));
    }

    public void confirmar(){
        setStatus(StatusPedido.CONFIRMADO);
        setDataConfirmacao(OffsetDateTime.now());
    }

    public void entregar(){
        setStatus(StatusPedido.ENTREGUE);
        setDataEntrega(OffsetDateTime.now());
    }

    public void cancelar(){
        setStatus(StatusPedido.CANCELADO);
        setDataCancelamento(OffsetDateTime.now());
    }

    private void setStatus(StatusPedido novoStatus){
        if (getStatus().naoPodeAlterarPara(novoStatus)){
            throw new NegocioException(
                    String.format("Status do pedido %d não pode ser alterado de %s para %s",  getId(), getStatus().getDescricao(), novoStatus.getDescricao())
            );
        }
        this.status = novoStatus;
    }
}